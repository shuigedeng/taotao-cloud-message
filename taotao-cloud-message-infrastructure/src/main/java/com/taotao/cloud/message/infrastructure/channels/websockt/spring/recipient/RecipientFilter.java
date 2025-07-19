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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.recipient;

import com.hccake.ballcat.notify.enums.NotifyRecipientFilterTypeEnum;
import com.hccake.ballcat.system.model.entity.SysUser;
import java.util.List;

/**
 * 接收者筛选器
 *
 * @author Hccake 2020/12/21
 * @version 1.0
 */
public interface RecipientFilter {

    /**
     * 当前筛选器对应的筛选类型
     * @see NotifyRecipientFilterTypeEnum
     * @return 筛选类型对应的标识
     */
    Integer filterType();

    /**
     * 接收者筛选
     * @param filterCondition 筛选条件
     * @return 接收者集合
     */
    List<SysUser> filter(List<Object> filterCondition);

    /**
     * 获取当前用户的过滤属性
     * @param sysUser 系统用户
     * @return 该用户所对应筛选条件的属性
     */
    Object getFilterAttr(SysUser sysUser);

    /**
     * 是否匹配当前用户
     * @param filterAttr 筛选属性
     * @param filterCondition 筛选条件
     * @return boolean true: 是否匹配
     */
    boolean match(Object filterAttr, List<Object> filterCondition);
}
