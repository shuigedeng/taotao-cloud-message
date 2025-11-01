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

package com.taotao.cloud.message.infrastructure.channels.websockt.spring.push;

import com.hccake.ballcat.common.util.HtmlUtils;
import com.hccake.ballcat.notify.enums.NotifyChannelEnum;
import com.hccake.ballcat.notify.model.domain.NotifyInfo;
import com.hccake.ballcat.system.model.entity.SysUser;
import com.taotao.cloud.sys.infrastructure.channels.websockt.spring.push.NotifyPusher;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 短信通知发布
 *
 * @author Hccake 2020/12/21
 * @version 1.0
 */
@Component
public class SmsNotifyPusher implements NotifyPusher {

    /**
     * 当前发布者对应的接收方式
     * @see NotifyChannelEnum
     * @return 推送方式
     */
    @Override
    public Integer notifyChannel() {
        return NotifyChannelEnum.SMS.getValue();
    }

    @Override
    public void push(NotifyInfo notifyInfo, List<SysUser> userList) {
        List<String> phoneList =
                userList.stream().map(SysUser::getPhone).filter(StrUtil::isNotBlank).toList();
        // 短信文本去除 html 标签
        String content = HtmlUtils.toText(notifyInfo.getContent());
        // TODO 对接短信发送平台
        LogUtils.info("短信推送");
    }
}
