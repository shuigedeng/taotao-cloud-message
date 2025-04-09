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

package com.taotao.cloud.message.api.feign.response;

import com.taotao.cloud.message.api.enums.AuthUserGender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>社会化登录用户 </p>
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
public class SocialUserApiResponse  {

	private Long id;

	@Schema(title = "社会用户ID")
	private String socialId;

	/**
	 * JustAuth中的关键词 以下内容了解后，将会使你更容易地上手JustAuth。
	 * <p>
	 * source JustAuth支持的第三方平台，比如：GITHUB、GITEE等 uuid 一般为第三方平台的用户ID。以下几个平台需特别注意： 钉钉、抖音：uuid 为用户的
	 * unionid 微信公众平台登录、京东、酷家乐、美团：uuid 为用户的 openId 微信开放平台登录、QQ：uuid 为用户的 openId，平台支持获取unionid，
	 * unionid 在 AuthToken 中（如果支持），在登录完成后，可以通过 response.getData().getToken().getUnionId() 获取
	 * Google：uuid 为用户的 sub，sub为Google的所有账户体系中用户唯一的身份标识符，详见：OpenID Connect (opens new window)
	 * 注：建议通过uuid + source的方式唯一确定一个用户，这样可以解决用户身份归属的问题。因为 单个用户ID 在某一平台中是唯一的，但不能保证在所有平台中都是唯一的。
	 */
	@Schema(title = "用户第三方系统的唯一id", description = "在调用方集成该组件时，可以用uuid + source唯一确定一个用")
	private String uuid;

	@Schema(title = "用户名")
	private String userName;

	@Schema(title = "用户昵称")
	private String nickName;

	@Schema(title = "用户头像")
	private String avatar;

	@Schema(title = "用户网址")
	private String blog;

	@Schema(title = "所在公司")
	private String company;

	@Schema(title = "位置")
	private String location;

	@Schema(title = "用户邮箱")
	private String email;

	@Schema(title = "用户邮箱")
	private String remark;
	/**
	 * 性别
	 */
	@Schema(title = "性别")
	private AuthUserGender gender;

	@Schema(title = "第三方用户来源")
	private String source;

	@Schema(title = "用户的授权令牌")
	private String accessToken;

	@Schema(title = "第三方用户的授权令牌的有效期", description = "部分平台可能没有")
	private Integer expireIn;

	@Schema(title = "刷新令牌", description = "部分平台可能没有")
	private String refreshToken;

	@Schema(title = "第三方用户的刷新令牌的有效期", description = "部分平台可能没有")
	private Integer refreshTokenExpireIn;

	@Schema(title = "第三方用户授予的权限", description = "部分平台可能没有")
	private String scope;

	@Schema(title = "个别平台的授权信息", description = "部分平台可能没有")
	private String tokenType;

	@Schema(title = "第三方用户的 ID", description = "部分平台可能没有")
	private String uid;

	@Schema(title = "第三方用户的 open id", description = "部分平台可能没有")
	private String openId;

	@Schema(title = "个别平台的授权信息", description = "部分平台可能没有")
	private String accessCode;

	@Schema(title = "第三方用户的 union id", description = "部分平台可能没有")
	private String unionId;

	@Schema(title = "小程序Appid", description = "部分平台可能没有")
	private String appId;

	@Schema(title = "手机号码", description = "部分平台可能没有")
	private String phoneNumber;

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public AuthUserGender getGender() {
		return gender;
	}

	public void setGender(AuthUserGender gender) {
		this.gender = gender;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(Integer expireIn) {
		this.expireIn = expireIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Integer getRefreshTokenExpireIn() {
		return refreshTokenExpireIn;
	}

	public void setRefreshTokenExpireIn(Integer refreshTokenExpireIn) {
		this.refreshTokenExpireIn = refreshTokenExpireIn;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
