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

package com.taotao.cloud.message.infrastructure.channels.websockt.stomp.annotation;

import cn.herodotus.engine.supplier.message.configuration.SupplierMessageConfiguration;
import java.lang.annotation.*;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: 开启 Supplier Message </p>
 *
 * @author : gengwei.zheng
 * @date : 2021/11/8 11:36
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SupplierMessageConfiguration.class)
public @interface EnableHerodotusSupplierMessage {}
