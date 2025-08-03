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

import com.taotao.boot.websocket.ballcat.notify.model.domain.NotifyInfo;
import com.taotao.cloud.message.biz.ballcat.notify.enums.NotifyChannelEnum;
import java.util.List;

/**
 * 通知发布者
 *
 * @author Hccake 2020/12/21
 * @version 1.0
 */
public interface NotifyPusher {

    /**
     * 当前发布者对应的推送渠道
     * @see NotifyChannelEnum
     * @return 推送方式对应的标识
     */
    Integer notifyChannel();

    /**
     * 推送通知
     * @param notifyInfo 通知信息
     * @param userList 用户列表
     */
    void push(NotifyInfo notifyInfo, List<SysUser> userList);
}
