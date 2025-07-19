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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.hccake.ballcat.common.core.constant.GlobalConstants;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.ballcat.notify.enums.AnnouncementStatusEnum;
import com.hccake.ballcat.notify.model.entity.Announcement;
import com.hccake.ballcat.notify.model.qo.AnnouncementQO;
import com.hccake.ballcat.notify.model.vo.AnnouncementPageVO;
import com.hccake.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.hccake.extend.mybatis.plus.mapper.ExtendMapper;
import com.hccake.extend.mybatis.plus.toolkit.WrappersX;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 公告信息
 *
 * @author hccake 2020-12-15 17:01:15
 */
public interface AnnouncementMapper extends ExtendMapper<Announcement> {

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param qo 查询对象
     * @return 分页结果数据 PageResult
     */
    default PageResult<AnnouncementPageVO> queryPage(PageParam pageParam, AnnouncementQO qo) {
        IPage<Announcement> page = this.prodPage(pageParam);
        LambdaQueryWrapperX<Announcement> wrapperX =
                WrappersX.lambdaAliasQueryX(Announcement.class)
                        .likeIfPresent(Announcement::getTitle, qo.getTitle())
                        .inIfPresent(Announcement::getStatus, (Object[]) qo.getStatus())
                        .eqIfPresent(
                                Announcement::getRecipientFilterType, qo.getRecipientFilterType())
                        .eq(Announcement::getDeleted, GlobalConstants.NOT_DELETED_FLAG);
        IPage<AnnouncementPageVO> voPage = this.selectByPage(page, wrapperX);
        return new PageResult<>(voPage.getRecords(), voPage.getTotal());
    }

    /**
     * 分页查询通知
     * @param page 分页封装对象
     * @param wrapper 条件构造器
     * @return 分页封装对象
     */
    IPage<AnnouncementPageVO> selectByPage(
            IPage<Announcement> page, @Param(Constants.WRAPPER) Wrapper<Announcement> wrapper);

    /**
     * 更新公共（限制只能更新未发布的公共）
     * @param announcement 公共信息
     * @return 更新是否成功
     */
    default boolean updateIfUnpublished(Announcement announcement) {
        int flag =
                this.update(
                        announcement,
                        Wrappers.<Announcement>lambdaUpdate()
                                .eq(Announcement::getId, announcement.getId())
                                .eq(
                                        Announcement::getStatus,
                                        AnnouncementStatusEnum.UNPUBLISHED.getValue()));
        return SqlHelper.retBool(flag);
    }

    /**
     * 根据参数获取当前用户拉取过，或者未拉取过的有效的公告信息
     * @param userId 用户ID
     * @param pulled 当前用户是否拉取过
     * @return 公告信息列表
     */
    List<Announcement> listUserAnnouncements(
            @Param("userId") Integer userId, @Param("pulled") boolean pulled);
}
