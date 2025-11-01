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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.handler;

import com.taotao.boot.websocket.ballcat.notify.model.domain.NotifyInfo;
import com.taotao.cloud.message.biz.ballcat.common.websocket.distribute.MessageDO;
import com.taotao.cloud.message.biz.ballcat.common.websocket.distribute.MessageDistributor;
import com.taotao.cloud.message.biz.ballcat.common.websocket.message.JsonWebSocketMessage;
import com.taotao.cloud.sys.infrastructure.channels.websockt.spring.handler.NotifyInfoHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公告通知
 *
 * @author huyuanzhi
 * @param <T>event消息对象
 * @param <M> websocket发送消息对象
 */
public abstract class AbstractNotifyInfoHandler<
                T extends NotifyInfo, M extends JsonWebSocketMessage>
        implements NotifyInfoHandler<T> {

    @Autowired private MessageDistributor messageDistributor;

    protected final Class<T> clz;

    @SuppressWarnings("unchecked")
    protected AbstractNotifyInfoHandler() {
        Type superClass = getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) superClass;
        clz = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public void handle(List<SysUser> userList, T notifyInfo) {
        M message = createMessage(notifyInfo);
        String msg = JsonUtils.toJson(message);
        List<Object> sessionKeys = userList.stream().map(SysUser::getUserId).toList();
        persistMessage(userList, notifyInfo);
        MessageDO messageDO =
                new MessageDO()
                        .setMessageText(msg)
                        .setSessionKeys(sessionKeys)
                        .setNeedBroadcast(CollUtil.isEmpty(sessionKeys));
        messageDistributor.distribute(messageDO);
    }

    @Override
    public Class<T> getNotifyClass() {
        return this.clz;
    }

    /**
     * 持久化通知
     * @param userList 通知用户列表
     * @param notifyInfo 消息内容
     */
    protected abstract void persistMessage(List<SysUser> userList, T notifyInfo);

    /**
     * 产生推送消息
     * @param notifyInfo 消息内容
     * @return 分发消息
     */
    protected abstract M createMessage(T notifyInfo);
}
