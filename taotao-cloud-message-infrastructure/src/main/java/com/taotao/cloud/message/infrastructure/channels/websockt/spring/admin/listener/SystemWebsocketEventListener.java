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

import com.taotao.boot.websocket.spring.admin.message.DictChangeMessage;
import com.taotao.boot.websocket.spring.common.distribute.MessageDO;
import com.taotao.boot.websocket.spring.common.distribute.MessageDistributor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * SystemWebsocketEventListener
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
public class SystemWebsocketEventListener {

    private final MessageDistributor messageDistributor;

    public SystemWebsocketEventListener( MessageDistributor messageDistributor ) {
        this.messageDistributor = messageDistributor;
    }

    /**
     * 字典修改事件监听
     *
     * @param event the `DictChangeEvent`
     */
    @Async
    @EventListener(DictChangeEvent.class)
    public void onDictChangeEvent( DictChangeEvent event ) {
        // 构建字典修改的消息体
        DictChangeMessage dictChangeMessage = new DictChangeMessage();
        dictChangeMessage.setDictCode(event.getDictCode());
        String msg = JacksonUtils.toJson(dictChangeMessage);

        // 广播修改信息
        MessageDO messageDO = new MessageDO().setMessageText(msg).setNeedBroadcast(true);
        messageDistributor.distribute(messageDO);
    }
}
