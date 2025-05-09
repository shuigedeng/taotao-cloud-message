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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/sysLogLogin")
@Tag(name = "工具管理端-登录记录管理", description = "工具管理端-登录记录管理")
public class SysLogLoginController extends BusinessController {

//    private final SysLogLoginService sysLogLoginService;
//
//    /**
//     * 分页查询
//     *
//     * @param page 分页对象
//     * @param sysLogLogin
//     * @return Response
//     */
//    @GetMapping("/page")
//    // @ApiOperation(value = "分页查询", notes = "分页查询")
//    public Result<Page> page(Page page, SysLogLogin sysLogLogin) {
//        page.setOrders(Arrays.asList(OrderItem.desc("login_time")));
//        return Result.success(sysLogLoginService.page(page, Wrappers.query(sysLogLogin)));
//    }
//
//    /**
//     * 通过id查询
//     *
//     * @param id
//     * @return Response
//     */
//    @GetMapping("/{id}")
//    //@ApiOperation(value = "通过id查询", notes = "通过id查询")
//    public Result<SysLogLogin> getById(@PathVariable Integer id) {
//        return Result.success(sysLogLoginService.getById(id));
//    }
//
//    /**
//     * 新增
//     *
//     * @param sysLogLogin
//     * @return Response
//     */
//    // @Anonymous
//    // @SysLog("新增")
//    @PostMapping
//    // @ApiOperation(value = "新增", notes = "新增")
//    public Result<Boolean> save(@RequestBody SysLogLogin sysLogLogin) {
//        return Result.success(sysLogLoginService.save(sysLogLogin));
//    }
//
//    /**
//     * 修改
//     *
//     * @param sysLogLogin
//     * @return Response
//     */
//    // @SysLog("编辑")
//    @PutMapping
//    // @ApiOperation(value = "修改", notes = "修改")
//    @PreAuthorize("@authorize.hasPermission('sys_editor_sysLogLogin')")
//    public Result<Boolean> update(@RequestBody SysLogLogin sysLogLogin) {
//        return Result.success(sysLogLoginService.updateById(sysLogLogin));
//    }
//
//    /**
//     * 通过id删除
//     *
//     * @param id
//     * @return Response
//     */
//    // @SysLog("通过id删除")
//    @DeleteMapping("/{id}")
//    // @ApiOperation(value = "通过id删除", notes = "通过id删除")
//    @PreAuthorize("@authorize.hasPermission('sys_delete_sysLogLogin')")
//    public Result<Boolean> delete(@PathVariable Integer id) {
//        return Result.success(sysLogLoginService.removeById(id));
//    }
}
