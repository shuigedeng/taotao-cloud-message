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

package com.taotao.cloud.message.infrastructure.channels.sse;

/**
 * MessageDTO
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
public class MessageDTO<T> {

    private String fromUserName;
    private String targetUserName;
    private T message;
    private String messageType;

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName( String fromUserName ) {
        this.fromUserName = fromUserName;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage( T message ) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType( String messageType ) {
        this.messageType = messageType;
    }

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName( String targetUserName ) {
        this.targetUserName = targetUserName;
    }

    public static enum Type {
        TYPE_NEW("0000"),
        TYPE_TEXT("1000"),
        TYPE_BYTE("1001");
        private String messageType;

        Type( String messageType ) {
            this.messageType = messageType;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType( String messageType ) {
            this.messageType = messageType;
        }
    }
}
