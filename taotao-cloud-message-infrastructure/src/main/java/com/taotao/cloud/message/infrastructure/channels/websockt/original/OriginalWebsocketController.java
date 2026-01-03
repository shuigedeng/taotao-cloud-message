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

package com.taotao.cloud.message.infrastructure.channels.websockt.original;

import com.taotao.boot.websocket.original.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OriginalWebsocketController
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
@RestController("/original")
public class OriginalWebsocketController {

    @Autowired
    private WebsocketService websocketService;

    @PostMapping("/push")
    public ResponseEntity<String> pushToWeb() {
        websocketService.sendMessageById("", "", "sadfasdf");
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

    @PostMapping("/pushAll")
    public ResponseEntity<String> pushAll() {
        websocketService.sendMessageAll("", "sadfasdf");
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}
