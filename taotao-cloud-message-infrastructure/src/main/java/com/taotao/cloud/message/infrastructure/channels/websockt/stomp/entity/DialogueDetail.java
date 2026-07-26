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
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 私信对话详情 </p>
 *
 * @author : gengwei.zheng
 * @since : 2022/12/7 11:03
 */
@Schema(name = "私信对话详情")
@Entity
@Table(
        name = "msg_dialogue_detail",
        indexes = {
            @Index(name = "msg_dialogue_detail_id_idx", columnList = "detail_id"),
            @Index(name = "msg_dialogue_detail_sid_idx", columnList = "sender_id"),
            @Index(name = "msg_dialogue_detail_rid_idx", columnList = "receiver_id"),
            @Index(name = "msg_dialogue_detail_did_idx", columnList = "dialogue_id")
        })
@Cacheable
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE,
        region = MessageConstants.REGION_MESSAGE_DIALOGUE_DETAIL)
public class DialogueDetail extends BaseSenderEntity {

    @Schema(name = "对话详情ID")
    @Id
    @UuidGenerator
    @Column(name = "detail_id", length = 64)
    private String detailId;

    @Schema(name = "接收人ID")
    @Column(name = "receiver_id", length = 64)
    private String receiverId;

    @Schema(name = "接收人名称", title = "冗余信息，增加该字段减少重复查询")
    @Column(name = "receiver_name", length = 50)
    private String receiverName;

    @Schema(name = "发送人头像")
    @Column(name = "receiver_avatar", length = 1000)
    private String receiverAvatar;

    @Schema(name = "公告内容")
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Schema(name = "对话ID")
    @Column(name = "dialogue_id", length = 64)
    private String dialogueId;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getDetailId() {
        return detailId;
    }

    /**
     * 设置
     *
     * @param detailId detailId
     * @return 无返回值
     * @since 2022.03
     */
    public void setDetailId(String detailId) {
        this.detailId = detailId;
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
     * @return 无返回值
     * @since 2022.03
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置
     *
     * @param receiverName receiverName
     * @return 无返回值
     * @since 2022.03
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getReceiverAvatar() {
        return receiverAvatar;
    }

    /**
     * 设置
     *
     * @param receiverAvatar receiverAvatar
     * @return 无返回值
     * @since 2022.03
     */
    public void setReceiverAvatar(String receiverAvatar) {
        this.receiverAvatar = receiverAvatar;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     *
     * @param content content
     * @return 无返回值
     * @since 2022.03
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getDialogueId() {
        return dialogueId;
    }

    /**
     * 设置
     *
     * @param dialogueId dialogueId
     * @return 无返回值
     * @since 2022.03
     */
    public void setDialogueId(String dialogueId) {
        this.dialogueId = dialogueId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("detailId", detailId)
                .add("receiverId", receiverId)
                .add("receiverName", receiverName)
                .add("receiverAvatar", receiverAvatar)
                .add("content", content)
                .add("dialogueId", dialogueId)
                .toString();
    }
}
