package com.taotao.cloud.message.infrastructure.channels.sse;

import com.taotao.cloud.sys.infrastructure.channels.sse.MessageDTO;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Chater {
	private String userName;
	private SseEmitter sseEmitter;
	private Queue<com.taotao.cloud.sys.infrastructure.channels.sse.MessageDTO<?>> msgList = new ConcurrentLinkedQueue<>();

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
