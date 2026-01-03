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

package com.taotao.cloud.message.infrastructure.channels.netty;

import com.taotao.cloud.sys.infrastructure.channels.netty.DataContent;
import com.taotao.cloud.sys.infrastructure.channels.netty.PushMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PushMsgController
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
@RestController
@RequestMapping("/push")
public class PushMsgController {

    /**
     * 同时为了方便操作，我们还可以提取出 Controller
     */
    @Autowired
    PushMsgService pushMsgService;

    @RequestMapping("/pushOne")
    public void pushOne( DataContent dataContent ) {
        pushMsgService.pushMsgToOne(dataContent);
    }

    @RequestMapping("/pushAll")
    public void pushAll( DataContent dataContent ) {
        pushMsgService.pushMsgToAll(dataContent);
    }
}
