/*
 * Copyright (c) 2020-2030 ZHENGGENGWEI(码匠君)<herodotus@aliyun.com>
 *
 * Dante Engine licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * <http://www.apache.org/licenses/LICENSE-2.0>
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Dante Engine 采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：
 *
 * 1.请不要删除和修改根目录下的LICENSE文件。
 * 2.请不要删除和修改 Dante Cloud 源码头部的版权声明。
 * 3.请保留源码和相关描述文件的项目出处，作者声明等。
 * 4.分发源码时候，请注明软件出处 <https://gitee.com/herodotus/dante-engine>
 * 5.在修改包名，模块名称，项目代码等时，请注明软件出处 <https://gitee.com/herodotus/dante-engine>
 * 6.若您的项目无法满足以上几点，可申请商业授权
 */

package com.taotao.cloud.message.infrastructure.channels.websockt.stomp.controller;

import cn.herodotus.engine.assistant.core.domain.Result;
import cn.herodotus.engine.data.core.service.WriteableService;
import cn.herodotus.engine.message.core.enums.NotificationCategory;
import cn.herodotus.engine.rest.core.controller.BaseWriteableRestController;
import cn.herodotus.engine.supplier.message.entity.Notification;
import cn.herodotus.engine.supplier.message.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>Description: 系统通知 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/12/17 14:19
 */
@RestController
@RequestMapping("/message/notification")
@Tags({
        @Tag(name = "消息管理接口"),
        @Tag(name = "通知管理接口")
})
public class NotificationController extends BaseWriteableRestController<Notification, String> {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public WriteableService<Notification, String> getWriteableService() {
        return notificationService;
    }

    @Operation(summary = "条件查询通知信息分页数据", description = "根据输入的字段条件查询通知信息",
            responses = {@ApiResponse(description = "详情列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)))})
    @Parameters({
            @Parameter(name = "pageNumber", required = true, description = "当前页码", schema = @Schema(type = "integer")),
            @Parameter(name = "pageSize", required = true, description = "每页显示数量", schema = @Schema(type = "integer")),
            @Parameter(name = "userId", required = true, description = "用户ID"),
            @Parameter(name = "category", description = "通知类别", schema = @Schema(type = "integer")),
            @Parameter(name = "read", description = "是否已读", schema = @Schema(type = "boolean")),
    })
    @GetMapping("/condition")
    public Result<Map<String, Object>> findByCondition(@NotNull @RequestParam(value = "pageNumber") Integer pageNumber,
                                                       @NotNull @RequestParam(value = "pageSize") Integer pageSize,
                                                       @NotNull @RequestParam(value = "userId") String userId,
                                                       @RequestParam(value = "category", required = false) Integer category,
                                                       @RequestParam(value = "read", required = false) Boolean read) {
        NotificationCategory notificationCategory = NotificationCategory.get(category);
        if (ObjectUtils.isNotEmpty(notificationCategory)) {
            if (notificationCategory == NotificationCategory.ANNOUNCEMENT) {
                notificationService.pullAnnouncements(userId);
            }
        }

        Page<Notification> pages = notificationService.findByCondition(pageNumber, pageSize, userId, notificationCategory, read);
        return result(pages);
    }

    @Operation(summary = "全部通知已读", description = "根据用户ID设置该用户的全部通知为已读",
            responses = {@ApiResponse(description = "影响数据条目数", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "userId", description = "用户ID")
    })
    @PutMapping("/all-read")
    public Result<Integer> setAllRead(@RequestParam("userId") String userId) {
        Integer result = notificationService.setAllRead(userId);
        return Result.success("操作成功", result);
    }
}
