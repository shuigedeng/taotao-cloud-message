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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.admin.listener;

import com.taotao.boot.websocket.spring.common.distribute.MessageDO;
import com.taotao.cloud.message.biz.ballcat.common.websocket.distribute.MessageDistributor;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * NotifyWebsocketEventListener
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
public class NotifyWebsocketEventListener {

    private final MessageDistributor messageDistributor;

    private final NotifyInfoDelegateHandler<? super NotifyInfo> notifyInfoDelegateHandler;

    /**
     * 公告关闭事件监听
     *
     * @param event the AnnouncementCloseEvent
     */
    @Async
    @EventListener(AnnouncementCloseEvent.class)
    public void onAnnouncementCloseEvent( AnnouncementCloseEvent event ) {
        // 构建公告关闭的消息体
        AnnouncementCloseMessage message = new AnnouncementCloseMessage();
        message.setId(event.getId());
        String msg = JacksonUtils.toJson(message);

        // 广播公告关闭信息
        MessageDO messageDO = new MessageDO().setMessageText(msg).setNeedBroadcast(true);
        messageDistributor.distribute(messageDO);
    }

    /**
     * 站内通知推送事件
     *
     * @param event the StationNotifyPushEvent
     */
    @Async
    @EventListener(StationNotifyPushEvent.class)
    public void onAnnouncementPublishEvent( StationNotifyPushEvent event ) {
        NotifyInfo notifyInfo = event.getNotifyInfo();
        List<SysUser> userList = event.getUserList();
        notifyInfoDelegateHandler.handle(userList, notifyInfo);
    }
}
