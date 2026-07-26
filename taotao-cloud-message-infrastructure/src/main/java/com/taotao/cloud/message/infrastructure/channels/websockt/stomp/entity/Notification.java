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
import cn.herodotus.engine.message.core.enums.NotificationCategory;
import cn.herodotus.engine.supplier.message.domain.BaseSenderEntity;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 通知队列 </p>
 *
 * @author : gengwei.zheng
 * @since : 2022/12/7 18:19
 */
@Schema(name = "通知队列")
@Entity
@Table(
        name = "msg_notification",
        indexes = {
            @Index(name = "msg_notification_id_idx", columnList = "queue_id"),
            @Index(name = "msg_notification_sid_idx", columnList = "user_id")
        })
@Cacheable
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE,
        region = MessageConstants.REGION_MESSAGE_NOTIFICATION)
public class Notification extends BaseSenderEntity {

    @Schema(name = "队列ID")
    @Id
    @UuidGenerator
    @Column(name = "queue_id", length = 64)
    private String queueId;

    @Schema(name = "是否已经读取", title = "false 未读，true 已读")
    @Column(name = "is_read")
    private Boolean read = false;

    @Schema(name = "用户ID")
    @Column(name = "user_id", length = 64)
    private String userId;

    @Schema(name = "公告内容")
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Schema(name = "通知类别", title = "1. 公告，2.私信")
    @Column(name = "category")
    @Enumerated(EnumType.ORDINAL)
    private NotificationCategory category = NotificationCategory.ANNOUNCEMENT;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getQueueId() {
        return queueId;
    }

    /**
     * 设置
     *
     * @param queueId queueId
     * @return 无返回值
     * @since 2022.03
     */
    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    /**
     * 获取
     *
     * @return 是否成功
     * @since 2022.03
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * 设置
     *
     * @param read read
     * @return 无返回值
     * @since 2022.03
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置
     *
     * @param userId userId
     * @return 无返回值
     * @since 2022.03
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return NotificationCategory
     * @since 2022.03
     */
    public NotificationCategory getCategory() {
        return category;
    }

    /**
     * 设置
     *
     * @param category category
     * @return 无返回值
     * @since 2022.03
     */
    public void setCategory(NotificationCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("queueId", queueId)
                .add("read", read)
                .add("userId", userId)
                .add("content", content)
                .add("category", category)
                .toString();
    }
}
