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

package com.taotao.cloud.message.infrastructure.channels.websockt.stomp.domain;

import cn.herodotus.engine.data.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * <p>Description: 基础发送者实体 </p>
 *
 * @author : gengwei.zheng
 * @since : 2022/12/16 22:30
 */
@MappedSuperclass
public abstract class BaseSenderEntity extends BaseEntity {

    @Schema(name = "发送人ID")
    @Column(name = "sender_id", length = 64)
    private String senderId;

    @Schema(name = "发送人名称", title = "冗余信息，增加该字段减少重复查询")
    @Column(name = "sender_name", length = 50)
    private String senderName;

    @Schema(name = "发送人头像")
    @Column(name = "sender_avatar", length = 1000)
    private String senderAvatar;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * 设置
     *
     * @param senderId senderId
     * @return 无返回值
     * @since 2022.03
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * 设置
     *
     * @param senderName senderName
     * @return 无返回值
     * @since 2022.03
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getSenderAvatar() {
        return senderAvatar;
    }

    /**
     * 设置
     *
     * @param senderAvatar senderAvatar
     * @return 无返回值
     * @since 2022.03
     */
    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }
}
