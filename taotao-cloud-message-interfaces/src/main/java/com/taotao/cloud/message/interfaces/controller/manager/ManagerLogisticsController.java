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

package com.taotao.cloud.message.interfaces.controller.manager;

import com.taotao.boot.webagg.controller.BusinessController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端-物流公司管理API
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/11/13 09:58
 */
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/sys/manager/logistics")
@Tag(name = "管理端-物流公司管理API", description = "管理端-物流公司管理API")
public class ManagerLogisticsController extends BusinessController {

//    private final ILogisticsService logisticsService;
//
//    @Operation(summary = "根据id查询物流公司信息", description = "根据id查询物流公司信息")
//    @Parameters({
//            @Parameter(name = "id", description = "物流公司id", required = true, example = "1111", in = ParameterIn.PATH)
//    })
//    @RequestLogger
//    @PreAuthorize("hasAuthority('express:company:info:id')")
//    @GetMapping("/info/id/{id:[0-9]*}")
//    public Result<LogisticsVO> findExpressCompanyById(@PathVariable(value = "id") Long id) {
//        LogisticsConfig logisticsConfig = logisticsService.findLogisticsById(id);
//        return Result.success(LogisticsConvert.INSTANCE.convert(logisticsConfig));
//    }
}
