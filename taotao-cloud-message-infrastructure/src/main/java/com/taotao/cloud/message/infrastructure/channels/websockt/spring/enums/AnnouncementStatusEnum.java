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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.enums;

import lombok.*;
import lombok.Getter;

/**
 * 公告状态
 *
 * @author Hccake 2020/12/17
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum AnnouncementStatusEnum {

    /**
     * 关闭的
     */
    DISABLED(0),

    /**
     * 开启的
     */
    ENABLED(1),

    /**
     * 待发布
     */
    UNPUBLISHED(2);

    private final int value;
}
