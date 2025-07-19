/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.push;

import com.taotao.cloud.message.biz.ballcat.notify.model.domain.NotifyInfo;
import com.taotao.cloud.message.biz.ballcat.notify.recipient.RecipientHandler;
import com.taotao.cloud.sys.infrastructure.channels.websockt.spring.push.NotifyPusher;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutoolcore.collection.CollUtil;
import org.springframework.stereotype.Component;

/**
 * 通知消息推送执行器
 *
 * @author Hccake 2020/12/21
 * @version 1.0
 */
@Slf4j
@Component
public class NotifyPushExecutor {

    private final RecipientHandler recipientHandler;

    private final Map<
                    Integer,
                    com.taotao.cloud.sys.infrastructure.channels.websockt.spring.push.NotifyPusher>
            notifyPusherMap = new LinkedHashMap<>();

    public NotifyPushExecutor(
            RecipientHandler recipientHandler,
            List<com.taotao.cloud.sys.infrastructure.channels.websockt.spring.push.NotifyPusher>
                    notifyPusherList) {
        this.recipientHandler = recipientHandler;
        if (CollUtil.isNotEmpty(notifyPusherList)) {
            for (com.taotao.cloud.sys.infrastructure.channels.websockt.spring.push.NotifyPusher
                    notifyPusher : notifyPusherList) {
                this.addNotifyPusher(notifyPusher);
            }
        }
    }

    /**
     * 添加通知推送者
     * @param notifyPusher 通知推送者
     */
    public void addNotifyPusher(
            com.taotao.cloud.sys.infrastructure.channels.websockt.spring.push.NotifyPusher
                    notifyPusher) {
        this.notifyPusherMap.put(notifyPusher.notifyChannel(), notifyPusher);
    }

    /**
     * 执行通知推送
     * @param notifyInfo 通知信息
     *
     */
    public void push(NotifyInfo notifyInfo) {
        // 获取通知接收人
        Integer recipientFilterType = notifyInfo.getRecipientFilterType();
        List<Object> recipientFilterCondition = notifyInfo.getRecipientFilterCondition();
        List<SysUser> userList =
                recipientHandler.query(recipientFilterType, recipientFilterCondition);

        // 执行推送
        // TODO 返回推送失败渠道信息，以便重试
        for (Integer notifyChannel : notifyInfo.getReceiveMode()) {
            try {
                NotifyPusher notifyPusher = notifyPusherMap.get(notifyChannel);

                if (notifyPusher == null) {
                    log.error(
                            "Unknown notify channel：[{}]，notifyInfo title：[{}]",
                            notifyChannel,
                            notifyInfo.getTitle());
                } else {
                    notifyPusher.push(notifyInfo, userList);
                }
            } catch (Exception e) {
                log.error(
                        "push notify error in channel：[{}]，notifyInfo title：[{}]",
                        notifyChannel,
                        notifyInfo.getTitle(),
                        e);
            }
        }
    }
}
