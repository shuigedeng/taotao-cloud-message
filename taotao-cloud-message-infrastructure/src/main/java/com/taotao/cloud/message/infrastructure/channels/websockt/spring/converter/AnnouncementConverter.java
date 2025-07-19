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

import com.hccake.ballcat.notify.model.dto.AnnouncementDTO;
import com.hccake.ballcat.notify.model.entity.Announcement;
import com.hccake.ballcat.notify.model.vo.AnnouncementPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Hccake 2020/12/16
 * @version 1.0
 */
@Mapper
public interface AnnouncementConverter {

    AnnouncementConverter INSTANCE = Mappers.getMapper(AnnouncementConverter.class);

    /**
     * PO 转 PageVO
     * @param announcement 公告表
     * @return AnnouncementPageVO 公告表PageVO
     */
    AnnouncementPageVO poToPageVo(Announcement announcement);

    /**
     * AnnouncementDTO 转 Announcement实体
     * @param dto AnnouncementDTO
     * @return Announcement
     */
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "createBy", ignore = true)
    Announcement dtoToPo(AnnouncementDTO dto);
}
