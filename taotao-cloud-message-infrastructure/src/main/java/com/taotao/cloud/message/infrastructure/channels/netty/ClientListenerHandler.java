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

import com.taotao.cloud.sys.infrastructure.channels.netty.ExceptionUtil;
import com.taotao.cloud.sys.infrastructure.channels.netty.MessageBean;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * ClientListenerHandler
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
@Slf4j
@ChannelHandler.Sharable
public class ClientListenerHandler extends SimpleChannelInboundHandler<MessageBean> {

    /**
     * 服务端上线的时候调用
     */
    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception {
        log.info("{}连上了服务器", ctx.channel().remoteAddress());
    }

    /**
     * 服务端掉线的时候调用
     */
    @Override
    public void channelInactive( ChannelHandlerContext ctx ) throws Exception {
        log.info("{}断开了服务器", ctx.channel().remoteAddress());
        ctx.fireChannelInactive();
    }

    /**
     * 读取服务端消息
     */
    @Override
    protected void channelRead0(
            ChannelHandlerContext channelHandlerContext, MessageBean messageBean ) throws Exception {
        log.info("来自服务端的消息:{}", new String(messageBean.getContent(), CharsetUtil.UTF_8));
        channelHandlerContext.channel().close();
    }

    /**
     * 异常发生时候调用
     */
    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception {
        log.error("{}连接出异常了", ctx.channel().remoteAddress());
        log.error(ExceptionUtil.printStackTrace((Exception) cause));
        ctx.close();
    }
}
