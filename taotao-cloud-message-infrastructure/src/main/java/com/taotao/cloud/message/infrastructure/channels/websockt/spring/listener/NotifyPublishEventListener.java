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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.listener;

import com.taotao.cloud.message.biz.ballcat.notify.event.NotifyPublishEvent;
import com.taotao.cloud.message.biz.ballcat.notify.model.domain.NotifyInfo;
import com.taotao.cloud.message.biz.ballcat.notify.push.NotifyPushExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 通知发布事件监听器
 *
 * @author Hccake 2020/12/17
 * @version 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotifyPublishEventListener {

    private final NotifyPushExecutor notifyPushExecutor;

    /**
     * 通知发布事件
     * @param event the NotifyPublishEvent
     */
    @Async
    @EventListener(NotifyPublishEvent.class)
    public void onNotifyPublishEvent(NotifyPublishEvent event) {
        NotifyInfo notifyInfo = event.getNotifyInfo();
        // 推送通知
        notifyPushExecutor.push(notifyInfo);
    }
}
