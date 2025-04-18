package com.taotao.cloud.message.infrastructure.channels.netty;

import com.taotao.cloud.sys.infrastructure.channels.netty.ChatMsg;
import com.taotao.cloud.sys.infrastructure.channels.netty.DataContent;
import com.taotao.cloud.sys.infrastructure.channels.netty.PushMsgService;
import com.taotao.cloud.sys.infrastructure.channels.netty.UserConnectPool;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PushMsgServiceImpl implements PushMsgService {

	@Override
	public void pushMsgToOne(DataContent dataContent) {
		ChatMsg chatMsg = dataContent.getChatMsg();
		Channel channel = UserConnectPool.getChannel(chatMsg.getReceiverId());
		if (Objects.isNull(channel)) {
			throw new RuntimeException("未连接socket服务器");
		}

		channel.writeAndFlush(
			new TextWebSocketFrame(
				JsonUtils.objectToJson(chatMsg)
			)
		);
	}

	@Override
	public void pushMsgToAll(DataContent dataContent) {
		ChatMsg chatMsg = dataContent.getChatMsg();
		Channel channel = UserConnectPool.getChannel(chatMsg.getReceiverId());
		UserConnectPool.getChannelGroup().writeAndFlush(
			new TextWebSocketFrame(
				JsonUtils.objectToJson(chatMsg)
			)
		);
	}
}
