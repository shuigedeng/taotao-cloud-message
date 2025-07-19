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

import com.hccake.ballcat.system.model.entity.SysUser;
import com.taotao.cloud.sys.infrastructure.channels.websockt.spring.recipient.RecipientFilter;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Hccake 2020/12/21
 * @version 1.0
 */
@Slf4j
@Component
public class RecipientHandler {

    private final Map<
                    Integer,
                    com.taotao.cloud.sys.infrastructure.channels.websockt.spring.recipient
                            .RecipientFilter>
            recipientFilterMap = new LinkedHashMap<>();

    public RecipientHandler(
            List<
                            com.taotao.cloud.sys.infrastructure.channels.websockt.spring.recipient
                                    .RecipientFilter>
                    recipientFilterList) {
        for (com.taotao.cloud.sys.infrastructure.channels.websockt.spring.recipient.RecipientFilter
                recipientFilter : recipientFilterList) {
            this.recipientFilterMap.put(recipientFilter.filterType(), recipientFilter);
        }
    }

    public List<SysUser> query(Integer filterType, List<Object> filterCondition) {
        com.taotao.cloud.sys.infrastructure.channels.websockt.spring.recipient.RecipientFilter
                recipientFilter = recipientFilterMap.get(filterType);
        if (recipientFilter == null) {
            log.error(
                    "Unknown recipient filter：[{}]，filterCondition：{}",
                    filterType,
                    filterCondition);
            return new ArrayList<>();
        }
        return recipientFilter.filter(filterCondition);
    }

    /**
     * 判断当前是否匹配
     * @param recipientFilterType 筛选类型
     * @param filterAttr 筛选属性
     * @param recipientFilterCondition 筛选条件
     * @return boolean true：匹配
     */
    public boolean match(
            Integer recipientFilterType, Object filterAttr, List<Object> recipientFilterCondition) {
        com.taotao.cloud.sys.infrastructure.channels.websockt.spring.recipient.RecipientFilter
                recipientFilter = recipientFilterMap.get(recipientFilterType);
        return recipientFilter != null
                && recipientFilter.match(filterAttr, recipientFilterCondition);
    }

    /**
     * 获取当前用户的各个筛选器对应的属性
     * @param sysUser 系统用户
     * @return 属性Map key：filterType value: attr
     */
    public Map<Integer, Object> getFilterAttrs(SysUser sysUser) {
        Map<Integer, Object> map = new HashMap<>(recipientFilterMap.size());
        for (RecipientFilter recipientFilter : recipientFilterMap.values()) {
            Object obj = recipientFilter.getFilterAttr(sysUser);
            map.put(recipientFilter.filterType(), obj);
        }
        return map;
    }
}
