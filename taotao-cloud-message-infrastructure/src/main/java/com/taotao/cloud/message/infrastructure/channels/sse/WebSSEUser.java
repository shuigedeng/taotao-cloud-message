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

package com.taotao.cloud.message.infrastructure.channels.sse;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WebSSEUser {
    private static Map<String, Chater> userChaterMap = new ConcurrentHashMap<>();

    public static void add(String userName, Chater chater) {
        userChaterMap.put(userName, chater);
    }

    /**
     * 根据昵称拿Chater
     *
     * @param nickName
     * @return
     */
    public static Chater getChater(String userName) {
        return userChaterMap.get(userName);
    }

    /**
     * 移除失效的Chater
     *
     * @param Chater
     */
    public static void removeUser(String userName) {
        userChaterMap.remove(userName);
    }

    public static Set<String> getUserList() {
        return userChaterMap.keySet();
    }
}
