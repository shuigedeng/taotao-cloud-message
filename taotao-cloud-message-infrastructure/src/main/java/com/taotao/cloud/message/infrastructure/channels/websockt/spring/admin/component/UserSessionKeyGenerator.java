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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.admin.component;

import com.taotao.boot.websocket.spring.admin.constant.AdminWebSocketConstants;
import com.taotao.boot.websocket.spring.common.session.SessionKeyGenerator;
import com.taotao.cloud.sys.infrastructure.channels.websockt.spring.admin.component.UserAttributeHandshakeInterceptor;
import org.springframework.web.socket.WebSocketSession;

/**
 * <p>
 * 用户 WebSocketSession 唯一标识生成器
 * </p>
 *
 * 此类主要使用当前 session 对应用户的唯一标识做为 session 的唯一标识 方便系统快速通过用户获取对应 session
 *
 * @author Hccake 2021/1/5
 * @version 1.0
 */
public class UserSessionKeyGenerator implements SessionKeyGenerator {

    /**
     * 获取当前session的唯一标识，用户的唯一标识已经通过
     * @see UserAttributeHandshakeInterceptor 存储在当前 session 的属性中
     * @param webSocketSession 当前session
     * @return session唯一标识
     */
    @Override
    public Object sessionKey(WebSocketSession webSocketSession) {
        return webSocketSession.getAttributes().get(AdminWebSocketConstants.USER_KEY_ATTR_NAME);
    }
}
