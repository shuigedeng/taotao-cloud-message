package com.taotao.cloud.message.application.shared.monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Properties;


@Setter
@Getter
@ToString
public class RedisCacheInfoDTO {

    private Properties info;
    private Object dbSize;
    private List<CommonStatusDTO> commandStats;

    @Setter
@Getter
@ToString
    public static class CommonStatusDTO {
        private String name;
        private String value;
    }

}
