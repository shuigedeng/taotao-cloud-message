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

import com.taotao.cloud.sys.infrastructure.channels.netty.HeartBeatHandler;
import com.taotao.cloud.sys.infrastructure.channels.netty.ServerListenerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * ServerHandler
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
public class ServerHandler extends ChannelInitializer<SocketChannel> {

    /**
     * 初始化通道以及配置对应管道的处理器
     */
    @Override
    protected void initChannel( SocketChannel channel ) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());

        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        // ===========================增加心跳支持==============================

        /**
         * 针对客户端，如果在1分钟时间内没有向服务端发送读写心跳（ALL），则主动断开连接
         * 如果有读空闲和写空闲，则不做任何处理
         */
        pipeline.addLast(new IdleStateHandler(8, 10, 12));
        // 自定义的空闲状态检测的handler
        pipeline.addLast(new HeartBeatHandler());

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        // 自定义的handler
        pipeline.addLast(new ServerListenerHandler());
    }
}
