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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.event;

import com.taotao.cloud.message.biz.ballcat.notify.model.domain.NotifyInfo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 通知发布事件
 *
 * @author Hccake 2020/12/17
 * @version 1.0
 */
@Getter
public class NotifyPublishEvent extends ApplicationEvent {

    /**
     * 通知信息
     */
    private final NotifyInfo notifyInfo;

    public NotifyPublishEvent(NotifyInfo notifyInfo) {
        super(notifyInfo);
        this.notifyInfo = notifyInfo;
    }
}
