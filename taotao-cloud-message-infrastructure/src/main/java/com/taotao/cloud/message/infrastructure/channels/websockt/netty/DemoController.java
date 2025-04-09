package com.taotao.cloud.message.infrastructure.channels.websockt.netty;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.ChatService;
import com.taotao.cloud.sys.infrastructure.channels.websockt.netty.NettyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

	@Autowired
	private ChatService chatService;

	@PostMapping("/push")
	public ResponseEntity<String> pushToWeb(@RequestBody com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat chat) {
		chat.setCreateTime(LocalDateTime.now());
		chatService.save(chat);
		chatService.sendInfo(chat);

		return ResponseEntity.ok("MSG SEND SUCCESS");
	}

	@GetMapping("/close")
	public String close(String userId) {
		com.taotao.cloud.sys.infrastructure.channels.websockt.netty.NettyWebSocket.close(userId);
		return "ok";
	}

	@GetMapping("/getOnlineUser")
	public Map getOnlineUser() {
		return NettyWebSocket.getOnlineUser();
	}

	@GetMapping("/getMessage")
	public ResponseEntity<List<com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat>> getMessage(String userId) {
		QueryWrapper<com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat> queryWrapper = new QueryWrapper();
		List<com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat> list = chatService.
			list(queryWrapper.lambda().eq(com.taotao.cloud.sys.infrastructure.channels.websockt.netty.Chat::getTargetUserId, userId).or().eq(Chat::getUserId, userId));
		return ResponseEntity.ok(list);
	}

}
