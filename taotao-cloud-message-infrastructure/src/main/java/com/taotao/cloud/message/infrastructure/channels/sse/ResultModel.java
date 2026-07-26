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

/**
 * @author cff
 */
public class ResultModel {

    private String errorCode;
    private String message;
    private Object data;

    public ResultModel() {}

    public ResultModel(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ResultModel(String errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }


    /**
     * geterrorCode 方法
     *
     * @return 字符串
     * @since 2022.03
     */

    public String geterrorCode() {
        return errorCode;
    }


    /**
     * seterrorCode 方法
     *
     * @param errorCode 错误编码
     * @return 无返回值
     * @since 2022.03
     */

    public void seterrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getMessage() {
        return message;
    }

    /**
    /**
     * 设置
     *
     * @param message message
     * @return 无返回值
     * @since 2022.03
     */

    public void setMessage(String message) {
        this.message = message;
    }

    /**
    /**
     * 获取
     *
     * @return Object
     * @since 2022.03
     */

    public Object getData() {
        return data;
    }

    /**
    /**
     * 设置
     *
     * @param data data
     * @return 无返回值
     * @since 2022.03
     */

    public void setData(Object data) {
        this.data = data;
    }


    /**
     * ok 方法
     *
     * @return 结果Model
     * @since 2022.03
     */

    public static ResultModel ok() {
        return new ResultModel("0000", "成功");
    }


    /**
     * ok 方法
     *
     * @param data 数据
     * @return 结果Model
     * @since 2022.03
     */

    public static ResultModel ok(Object data) {
        return new ResultModel("0000", "成功", data);
    }


    /**
     * 错误
     *
     * @return 结果Model
     * @since 2022.03
     */

    public static ResultModel error() {
        return new ResultModel("1111", "失败");
    }


    /**
     * 错误
     *
     * @param msg 消息
     * @return 结果Model
     * @since 2022.03
     */

    public static ResultModel error(String msg) {
        return new ResultModel("1111", "失败", msg);
    }


    /**
     * 错误
     *
     * @param msg 消息
     * @param data 数据
     * @return 结果Model
     * @since 2022.03
     */

    public static ResultModel error(String msg, Object data) {
        return new ResultModel("1111", msg, data);
    }
}
