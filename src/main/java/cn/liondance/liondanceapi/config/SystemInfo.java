package cn.liondance.liondanceapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "system-info")
public class SystemInfo {
    private String  apiVersion;
    private String  appVersion;
    private String  aesKey;
    private Boolean isEncrypt;

}
