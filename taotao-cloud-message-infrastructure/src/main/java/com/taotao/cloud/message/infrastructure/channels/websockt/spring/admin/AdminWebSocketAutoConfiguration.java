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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.admin;

import com.taotao.cloud.message.biz.ballcat.admin.websocket.component.UserAttributeHandshakeInterceptor;
import com.taotao.cloud.message.biz.ballcat.admin.websocket.component.UserSessionKeyGenerator;
import com.taotao.cloud.message.biz.ballcat.common.websocket.session.SessionKeyGenerator;
import com.taotao.cloud.sys.infrastructure.channels.websockt.spring.admin.NotifyWebsocketEventListenerConfiguration;
import com.taotao.cloud.sys.infrastructure.channels.websockt.spring.admin.SystemWebsocketEventListenerConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @author Hccake 2021/1/5
 * @version 1.0
 */
@Import({
    SystemWebsocketEventListenerConfiguration.class,
    NotifyWebsocketEventListenerConfiguration.class
})
@Configuration
@RequiredArgsConstructor
public class AdminWebSocketAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(UserAttributeHandshakeInterceptor.class)
    public HandshakeInterceptor authenticationHandshakeInterceptor() {
        return new UserAttributeHandshakeInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(SessionKeyGenerator.class)
    public SessionKeyGenerator userSessionKeyGenerator() {
        return new UserSessionKeyGenerator();
    }
}
