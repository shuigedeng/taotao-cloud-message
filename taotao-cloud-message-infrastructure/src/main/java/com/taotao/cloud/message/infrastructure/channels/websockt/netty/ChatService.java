package com.taotao.cloud.message.infrastructure.channels.websockt.netty;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat;

public interface ChatService extends IService<com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat> {


	void sendInfo(Chat chat);
}
