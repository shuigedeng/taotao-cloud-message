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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.controller;

import com.taotao.boot.data.mybatis.pagehelper.PageParam;
import com.taotao.boot.security.spring.utils.SecurityUtils;
import com.taotao.cloud.message.biz.ballcat.notify.model.qo.UserAnnouncementQO;
import com.taotao.cloud.message.biz.ballcat.notify.model.vo.UserAnnouncementPageVO;
import com.taotao.cloud.message.biz.ballcat.notify.service.UserAnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户公告表
 *
 * @author hccake 2020-12-25 08:04:53
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notify/user-announcement")
@Tag(name = "用户公告表管理")
public class UserAnnouncementController {

    private final UserAnnouncementService userAnnouncementService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param userAnnouncementQO 用户公告表查询对象
     * @return R 通用返回体
     */
    @GetMapping("/page")
    @PreAuthorize("@per.hasPermission('notify:userannouncement:read')")
    @Operation(summary = "分页查询", description = "分页查询")
    public R<PageResult<UserAnnouncementPageVO>> getUserAnnouncementPage(
            @Validated PageParam pageParam, UserAnnouncementQO userAnnouncementQO) {
        return R.ok(userAnnouncementService.queryPage(pageParam, userAnnouncementQO));
    }

    @PatchMapping("/read/{announcementId}")
    @PreAuthorize("@per.hasPermission('notify:userannouncement:read')")
    @Operation(summary = "用户公告已读上报", description = "用户公告已读上报")
    public R<Void> readAnnouncement(@PathVariable("announcementId") Long announcementId) {
        Integer userId = SecurityUtils.getUser().getUserId();
        userAnnouncementService.readAnnouncement(userId, announcementId);
        return R.ok();
    }
}
