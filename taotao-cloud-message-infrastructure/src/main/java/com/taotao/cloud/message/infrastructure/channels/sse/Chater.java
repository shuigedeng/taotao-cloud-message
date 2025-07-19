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

import com.taotao.cloud.sys.infrastructure.channels.sse.MessageDTO;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class Chater {
    private String userName;
    private SseEmitter sseEmitter;
    private Queue<com.taotao.cloud.sys.infrastructure.channels.sse.MessageDTO<?>> msgList =
            new ConcurrentLinkedQueue<>();

    public void addMsg(com.taotao.cloud.sys.infrastructure.channels.sse.MessageDTO<?> msg) {
        msgList.add(msg);
        while (!msgList.isEmpty()) {
            MessageDTO<?> msgItem = msgList.poll();
            try {
                sseEmitter.send(msgItem);
            } catch (IOException e) {
                LogUtils.error(e);
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SseEmitter getSseEmitter() {
        return sseEmitter;
    }

    public void setSseEmitter(SseEmitter sseEmitter) {
        this.sseEmitter = sseEmitter;
    }
}
