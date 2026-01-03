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

import lombok.Data;
import lombok.experimental.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * HoleNettyProperties
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
@ConfigurationProperties(prefix = "netty")
@Data
@Configuration
public class HoleNettyProperties {

    /**
     * boss线程数量 默认为cpu线程数*2
     */
    private Integer boss;

    /**
     * worker线程数量 默认为cpu线程数*2
     */
    private Integer worker;

    /**
     * 连接超时时间 默认为30s
     */
    private Integer timeout = 30000;

    /**
     * 服务器主端口 默认9000
     */
    private Integer port = 9000;

    private String host = "127.0.0.1";
}
