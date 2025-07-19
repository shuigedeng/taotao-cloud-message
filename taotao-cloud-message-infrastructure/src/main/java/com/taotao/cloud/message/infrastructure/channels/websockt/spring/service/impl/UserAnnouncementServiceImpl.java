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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.service.impl;

import com.taotao.boot.data.mybatis.pagehelper.PageParam;
import com.taotao.cloud.message.biz.ballcat.notify.enums.UserAnnouncementStateEnum;
import com.taotao.cloud.message.biz.ballcat.notify.mapper.UserAnnouncementMapper;
import com.taotao.cloud.message.biz.ballcat.notify.model.entity.UserAnnouncement;
import com.taotao.cloud.message.biz.ballcat.notify.model.qo.UserAnnouncementQO;
import com.taotao.cloud.message.biz.ballcat.notify.model.vo.UserAnnouncementPageVO;
import com.taotao.cloud.message.biz.ballcat.notify.service.UserAnnouncementService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 * 用户公告表
 *
 * @author hccake 2020-12-25 08:04:53
 */
@Service
public class UserAnnouncementServiceImpl
        extends ExtendServiceImpl<UserAnnouncementMapper, UserAnnouncement>
        implements UserAnnouncementService {

    /**
     * 根据QueryObject查询分页数据
     * @param pageParam 分页参数
     * @param qo 查询参数对象
     * @return PageResult<UserAnnouncementVO> 分页数据
     */
    @Override
    public PageResult<UserAnnouncementPageVO> queryPage(
            PageParam pageParam, UserAnnouncementQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    /**
     * 根据用户ID和公告id初始化一个新的用户公告关联对象
     * @param userId 用户ID
     * @param announcementId 公告ID
     * @return UserAnnouncement
     */
    @Override
    public UserAnnouncement prodUserAnnouncement(Integer userId, Long announcementId) {
        UserAnnouncement userAnnouncement = new UserAnnouncement();
        userAnnouncement.setUserId(userId);
        userAnnouncement.setAnnouncementId(announcementId);
        userAnnouncement.setCreateTime(LocalDateTime.now());
        userAnnouncement.setState(UserAnnouncementStateEnum.UNREAD.getValue());
        return userAnnouncement;
    }

    /**
     * 对用户公告进行已读标记
     * @param userId 用户id
     * @param announcementId 公告id
     */
    @Override
    public void readAnnouncement(Integer userId, Long announcementId) {
        baseMapper.updateToReadState(userId, announcementId);
    }
}
