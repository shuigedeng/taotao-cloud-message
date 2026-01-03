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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.ChatMapper;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.ChatService;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.NettyWebSocket;

import java.util.List;

/**
 * ChatServiceImpl
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

    @Override
    public void sendInfo( Chat chat ) {
        QueryWrapper<Chat> queryWrapper = new QueryWrapper<>();
        List<Chat> chats =
                this.baseMapper.selectList(
                        queryWrapper
                                .lambda()
                                .eq(Chat::getTargetUserId, chat.getTargetUserId())
                                .or()
                                .eq(Chat::getUserId, chat.getTargetUserId())
                                .or()
                                .eq(Chat::getTargetUserId, chat.getUserId())
                                .or()
                                .eq(Chat::getUserId, chat.getUserId()));

        NettyWebSocket.sendInfo(chat, chats);
    }
}
