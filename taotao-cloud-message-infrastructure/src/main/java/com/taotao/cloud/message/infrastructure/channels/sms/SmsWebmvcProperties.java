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

package com.taotao.cloud.message.infrastructure.channels.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信Web配置
 *
 * @author shuigedeng
 */
@ConfigurationProperties(prefix = SmsWebmvcProperties.PREFIX)
public class SmsWebmvcProperties {

    public static final String PREFIX = "taotao.cloud.sms.web";

    /** 默认基础路径 */
    public static final String DEFAULT_BASE_PATH = "/sms";

    /** 是否启用web端点 */
    private boolean enable = false;

    /** 基础路径 */
    private String basePath = DEFAULT_BASE_PATH;

    /** 是否启用验证码发送web端点 */
    private boolean enableSend = true;

    /** 是否启用验证码查询web端点 */
    private boolean enableGet = true;

    /** 是否启用验证码验证web端点 */
    private boolean enableVerify = true;

    /** 是否启用通知发送web端点 */
    private boolean enableNotice = true;

    /**
     * 判断
     *
     * @return 是否成功
     * @since 2022.03
     */

    public boolean isEnable() {
        return enable;
    }

    /**
     * 设置
     *
     * @param enable enable
     * @return 无返回值
     * @since 2022.03
     */

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getBasePath() {
        return basePath;
    }

    /**
     * 设置
     *
     * @param basePath basePath
     * @return 无返回值
     * @since 2022.03
     */

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**
     * 判断
     *
     * @return 是否成功
     * @since 2022.03
     */

    public boolean isEnableSend() {
        return enableSend;
    }

    /**
     * 设置
     *
     * @param enableSend enableSend
     * @return 无返回值
     * @since 2022.03
     */

    public void setEnableSend(boolean enableSend) {
        this.enableSend = enableSend;
    }

    /**
     * 判断
     *
     * @return 是否成功
     * @since 2022.03
     */

    public boolean isEnableGet() {
        return enableGet;
    }

    /**
     * 设置
     *
     * @param enableGet enableGet
     * @return 无返回值
     * @since 2022.03
     */

    public void setEnableGet(boolean enableGet) {
        this.enableGet = enableGet;
    }

    /**
     * 判断
     *
     * @return 是否成功
     * @since 2022.03
     */

    public boolean isEnableVerify() {
        return enableVerify;
    }

    /**
     * 设置
     *
     * @param enableVerify enableVerify
     * @return 无返回值
     * @since 2022.03
     */

    public void setEnableVerify(boolean enableVerify) {
        this.enableVerify = enableVerify;
    }

    /**
     * 判断
     *
     * @return 是否成功
     * @since 2022.03
     */

    public boolean isEnableNotice() {
        return enableNotice;
    }

    /**
     * 设置
     *
     * @param enableNotice enableNotice
     * @return 无返回值
     * @since 2022.03
     */

    public void setEnableNotice(boolean enableNotice) {
        this.enableNotice = enableNotice;
    }
}
