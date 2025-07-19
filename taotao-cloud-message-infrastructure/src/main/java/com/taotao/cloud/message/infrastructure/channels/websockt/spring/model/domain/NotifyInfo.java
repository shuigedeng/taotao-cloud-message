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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.model.domain;

import com.hccake.ballcat.notify.enums.NotifyChannelEnum;
import com.hccake.ballcat.notify.enums.NotifyRecipientFilterTypeEnum;
import java.util.List;

/**
 * @author Hccake 2020/12/23
 * @version 1.0
 */
public interface NotifyInfo {

    /**
     * 标题
     * @return String 当前通知标题
     */
    String getTitle();

    /**
     * 内容
     * @return String 当前通知内容
     */
    String getContent();

    /**
     * 接收人筛选方式
     * @see NotifyRecipientFilterTypeEnum
     * @return Integer 接收人筛选方式
     */
    Integer getRecipientFilterType();

    /**
     * 对应接收人筛选方式的条件信息
     * @return List<Object>
     */
    List<Object> getRecipientFilterCondition();

    /**
     * 接收方式，值与通知渠道一一对应
     * @see NotifyChannelEnum
     * @return List<Integer>
     */
    List<Integer> getReceiveMode();
}
