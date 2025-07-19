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

import com.hccake.ballcat.notify.model.entity.UserAnnouncement;
import com.hccake.ballcat.notify.model.vo.UserAnnouncementPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户公告表
 *
 * @author hccake 2021-03-22 20:16:12
 */
@Mapper
public interface UserAnnouncementConverter {

    UserAnnouncementConverter INSTANCE = Mappers.getMapper(UserAnnouncementConverter.class);

    /**
     * PO 转 PageVO
     * @param userAnnouncement 用户公告表
     * @return UserAnnouncementPageVO 用户公告表PageVO
     */
    UserAnnouncementPageVO poToPageVo(UserAnnouncement userAnnouncement);
}
