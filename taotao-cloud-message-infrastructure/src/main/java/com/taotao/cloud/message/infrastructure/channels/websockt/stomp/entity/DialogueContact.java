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

package com.taotao.cloud.message.infrastructure.channels.websockt.stomp.entity;

import cn.herodotus.engine.message.core.constants.MessageConstants;
import cn.herodotus.engine.supplier.message.domain.BaseSenderEntity;
import com.google.common.base.MoreObjects;
import com.taotao.cloud.sys.infrastructure.channels.websockt.stomp.entity.Dialogue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 私信联系 </p>
 * <p>
 * 私信双相关系存储。
 *
 * @author : gengwei.zheng
 * @since : 2022/12/7 11:03
 */
@Schema(name = "私信联系")
@Entity
@Table(
        name = "msg_dialogue_contact",
        indexes = {
            @Index(name = "msg_dialogue_contact_id_idx", columnList = "contact_id"),
            @Index(name = "msg_dialogue_contact_sid_idx", columnList = "sender_id"),
        })
@Cacheable
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE,
        region = MessageConstants.REGION_MESSAGE_DIALOGUE_CONTACT)
public class DialogueContact extends BaseSenderEntity {

    @Schema(name = "联系ID")
    @Id
    @UuidGenerator
    @Column(name = "contact_id", length = 64)
    private String contactId;

    @Schema(name = "接收人ID")
    @Column(name = "receiver_id", length = 64)
    private String receiverId;

    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE,
            region = MessageConstants.REGION_MESSAGE_DIALOGUE)
    @Schema(title = "对话ID")
    @ManyToOne
    @JoinColumn(name = "dialogue_id", nullable = false)
    private com.taotao.cloud.sys.infrastructure.channels.websockt.stomp.entity.Dialogue dialogue;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * 设置
     *
     * @param contactId contactId
     * @since 2022.03
     */
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * 设置
     *
     * @param receiverId receiverId
     * @since 2022.03
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public com.taotao.cloud.sys.infrastructure.channels.websockt.stomp.entity.Dialogue
            getDialogue() {
        return dialogue;
    }

    /**
     * 设置
     *
     * @param dialogue dialogue
     * @since 2022.03
     */
    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("contactId", contactId)
                .add("receiverId", receiverId)
                .add("dialogue", dialogue)
                .toString();
    }
}
