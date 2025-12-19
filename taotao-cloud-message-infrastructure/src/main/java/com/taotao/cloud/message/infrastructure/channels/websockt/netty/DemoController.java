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

package com.taotao.cloud.message.infrastructure.channels.websockt.netty;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.ChatService;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.NettyWebSocket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
@RestController
public class DemoController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/push")
    public ResponseEntity<String> pushToWeb(
            @RequestBody com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat chat ) {
        chat.setCreateTime(LocalDateTime.now());
        chatService.save(chat);
        chatService.sendInfo(chat);

        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

    @GetMapping("/close")
    public String close( String userId ) {
        com.taotao.cloud.sys.infrastructure.channels.websockt.netty.NettyWebSocket.close(userId);
        return "ok";
    }

    @GetMapping("/getOnlineUser")
    public Map getOnlineUser() {
        return NettyWebSocket.getOnlineUser();
    }

    @GetMapping("/getMessage")
    public ResponseEntity<List<com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat>>
    getMessage( String userId ) {
        QueryWrapper<com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat>
                queryWrapper = new QueryWrapper();
        List<com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat> list =
                chatService.list(
                        queryWrapper
                                .lambda()
                                .eq(
                                        com.taotao.cloud.sys.infrastructure.channels.websockt.netty
                                                .Chat
                                                ::getTargetUserId,
                                        userId)
                                .or()
                                .eq(Chat::getUserId, userId));
        return ResponseEntity.ok(list);
    }
}
