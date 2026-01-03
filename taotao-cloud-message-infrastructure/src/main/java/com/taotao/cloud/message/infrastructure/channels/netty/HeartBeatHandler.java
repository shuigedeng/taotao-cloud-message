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

import com.taotao.cloud.sys.infrastructure.channels.netty.UserConnectPool;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * HeartBeatHandler
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    public void userEventTriggered( ChannelHandlerContext ctx, Object evt ) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt; // 强制类型转化
            if (event.state() == IdleState.READER_IDLE) {
                LogUtils.info("进入读空闲......");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                LogUtils.info("进入写空闲......");
            } else if (event.state() == IdleState.ALL_IDLE) {
                LogUtils.info(
                        "channel 关闭之前：users 的数量为："
                                + com.taotao.cloud.sys.infrastructure.channels.netty.UserConnectPool
                                .getChannelGroup()
                                .size());
                Channel channel = ctx.channel();
                // 资源释放
                channel.close();
                LogUtils.info(
                        "channel 关闭之后：users 的数量为：" + UserConnectPool.getChannelGroup().size());
            }
        }
    }
}
