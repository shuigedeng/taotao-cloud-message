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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.handler.impl;

import com.taotao.cloud.message.biz.ballcat.notify.handler.AbstractNotifyInfoHandler;
import com.taotao.cloud.message.biz.ballcat.notify.model.domain.AnnouncementNotifyInfo;
import com.taotao.cloud.message.biz.ballcat.notify.model.entity.UserAnnouncement;
import com.taotao.cloud.message.biz.ballcat.notify.service.UserAnnouncementService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 公告通知消息处理器
 *
 * @author huyuanzhi
 */
@Component
@RequiredArgsConstructor
public class AnnouncementNotifyInfoHandler
        extends AbstractNotifyInfoHandler<AnnouncementNotifyInfo, AnnouncementPushMessage> {

    private final UserAnnouncementService userAnnouncementService;

    @Override
    protected void persistMessage(
            List<SysUser> userList, AnnouncementNotifyInfo announcementNotifyInfo) {
        List<UserAnnouncement> userAnnouncements = new ArrayList<>(userList.size());
        // 向指定用户推送
        for (SysUser sysUser : userList) {
            Integer userId = sysUser.getUserId();
            UserAnnouncement userAnnouncement =
                    userAnnouncementService.prodUserAnnouncement(
                            userId, announcementNotifyInfo.getId());
            userAnnouncements.add(userAnnouncement);
        }
        userAnnouncementService.saveBatch(userAnnouncements);
    }

    @Override
    protected AnnouncementPushMessage createMessage(AnnouncementNotifyInfo announcementNotifyInfo) {
        AnnouncementPushMessage message = new AnnouncementPushMessage();
        message.setId(announcementNotifyInfo.getId());
        message.setTitle(announcementNotifyInfo.getTitle());
        message.setContent(announcementNotifyInfo.getContent());
        message.setImmortal(announcementNotifyInfo.getImmortal());
        message.setDeadline(announcementNotifyInfo.getDeadline());
        return message;
    }
}
