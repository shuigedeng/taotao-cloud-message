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

package com.taotao.cloud.message.infrastructure.channels.websockt.stomp.listener;

import cn.herodotus.engine.message.core.domain.DialogueMessage;
import cn.herodotus.engine.message.core.event.LocalSendDialogueMessageEvent;
import cn.herodotus.engine.supplier.message.entity.DialogueDetail;
import cn.herodotus.engine.supplier.message.service.DialogueDetailService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 对话消息监听 </p>
 * <p>
 * 解耦模块之间的依赖关系
 *
 * @author : gengwei.zheng
 * @date : 2023/3/11 18:49
 */
@Component
public class DialogueMessageListener implements ApplicationListener<LocalSendDialogueMessageEvent> {

    private final DialogueDetailService dialogueDetailService;

    public DialogueMessageListener(DialogueDetailService dialogueDetailService) {
        this.dialogueDetailService = dialogueDetailService;
    }

    @Override
    public void onApplicationEvent(LocalSendDialogueMessageEvent event) {
        if (ObjectUtils.isNotEmpty(event)) {
            DialogueMessage dialogueMessage = event.getData();
            if (ObjectUtils.isNotEmpty(dialogueMessage)) {
                DialogueDetail dialogueDetail =
                        convertDialogueMessageToDialogueDetail(dialogueMessage);
                dialogueDetailService.save(dialogueDetail);
            }
        }
    }

    private DialogueDetail convertDialogueMessageToDialogueDetail(DialogueMessage dialogueMessage) {
        DialogueDetail dialogueDetail = new DialogueDetail();
        dialogueDetail.setDetailId(dialogueMessage.getDetailId());
        dialogueDetail.setReceiverId(dialogueMessage.getReceiverId());
        dialogueDetail.setReceiverName(dialogueMessage.getReceiverName());
        dialogueDetail.setReceiverAvatar(dialogueMessage.getReceiverAvatar());
        dialogueDetail.setContent(dialogueMessage.getContent());
        dialogueDetail.setDialogueId(dialogueMessage.getDialogueId());
        dialogueDetail.setSenderId(dialogueMessage.getSenderId());
        dialogueDetail.setSenderName(dialogueMessage.getSenderName());
        dialogueDetail.setSenderAvatar(dialogueMessage.getSenderAvatar());
        return dialogueDetail;
    }
}
