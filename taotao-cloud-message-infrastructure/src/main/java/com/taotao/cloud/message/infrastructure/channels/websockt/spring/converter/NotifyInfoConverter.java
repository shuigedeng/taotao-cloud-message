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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.converter;

import com.hccake.ballcat.notify.model.domain.AnnouncementNotifyInfo;
import com.hccake.ballcat.notify.model.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Hccake 2020/12/23
 * @version 1.0
 */
@Mapper
public interface NotifyInfoConverter {

    NotifyInfoConverter INSTANCE = Mappers.getMapper(NotifyInfoConverter.class);

    /**
     * 公告转通知实体
     * @param announcement 公告信息
     * @return 通知信息
     */
    AnnouncementNotifyInfo fromAnnouncement(Announcement announcement);
}
