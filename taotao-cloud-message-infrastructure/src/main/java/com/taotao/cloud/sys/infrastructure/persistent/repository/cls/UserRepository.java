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

package com.taotao.cloud.message.infrastructure.persistent.repository.cls;

import com.taotao.boot.webagg.repository.BaseClassSuperRepository;
import com.taotao.cloud.message.infrastructure.persistent.persistence.system.UserPO;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/**
 * CompanyMapper
 *
 * @author shuigedeng
 * @version 2023.01
 * @since 2023-02-10 17:00:04
 */
@Repository
public class UserRepository extends BaseClassSuperRepository<UserPO, Long> {

    public UserRepository(EntityManager em) {
        super(UserPO.class, em);
    }
}
