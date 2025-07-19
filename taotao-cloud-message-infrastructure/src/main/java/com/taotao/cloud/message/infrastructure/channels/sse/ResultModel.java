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

    public String geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultModel ok() {
        return new ResultModel("0000", "成功");
    }

    public static ResultModel ok(Object data) {
        return new ResultModel("0000", "成功", data);
    }

    public static ResultModel error() {
        return new ResultModel("1111", "失败");
    }

    public static ResultModel error(String msg) {
        return new ResultModel("1111", "失败", msg);
    }

    public static ResultModel error(String msg, Object data) {
        return new ResultModel("1111", msg, data);
    }
}
