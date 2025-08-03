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

package com.taotao.cloud.message.infrastructure.channels.netty;

import com.taotao.cloud.sys.infrastructure.channels.netty.ChatMsg;
import com.taotao.cloud.sys.infrastructure.channels.netty.DataContent;
import com.taotao.cloud.sys.infrastructure.channels.netty.PushMsgService;
import com.taotao.cloud.sys.infrastructure.channels.netty.UserConnectPool;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class PushMsgServiceImpl implements PushMsgService {

    @Override
    public void pushMsgToOne(DataContent dataContent) {
        ChatMsg chatMsg = dataContent.getChatMsg();
        Channel channel = UserConnectPool.getChannel(chatMsg.getReceiverId());
        if (Objects.isNull(channel)) {
            throw new RuntimeException("未连接socket服务器");
        }

        channel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(chatMsg)));
    }

    @Override
    public void pushMsgToAll(DataContent dataContent) {
        ChatMsg chatMsg = dataContent.getChatMsg();
        Channel channel = UserConnectPool.getChannel(chatMsg.getReceiverId());
        UserConnectPool.getChannelGroup()
                .writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(chatMsg)));
    }
}
