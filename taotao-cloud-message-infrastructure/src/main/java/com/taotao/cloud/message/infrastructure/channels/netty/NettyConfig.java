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

import com.taotao.cloud.sys.infrastructure.channels.netty.HoleNettyProperties;
import com.taotao.cloud.sys.infrastructure.channels.netty.ServerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * NettyConfig
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
@Configuration
@EnableConfigurationProperties
public class NettyConfig {

    @Autowired
    HoleNettyProperties holeNettyProperties;

    /**
     * boss 线程池 负责客户端连接
     */
    @Bean
    public NioEventLoopGroup boosGroup() {
        return new NioEventLoopGroup(holeNettyProperties.getBoss());
    }

    /**
     * worker线程池 负责业务处理
     */
    @Bean
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(holeNettyProperties.getWorker());
    }

    /**
     * 服务器启动器
     */
    @Bean
    public ServerBootstrap serverBootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup(), workerGroup()) // 指定使用的线程组
                .channel(NioServerSocketChannel.class) // 指定使用的通道
                .option(
                        ChannelOption.CONNECT_TIMEOUT_MILLIS,
                        holeNettyProperties.getTimeout()) // 指定连接超时时间
                .childHandler(new ServerHandler()); // 指定worker处理器
        return serverBootstrap;
    }
}
