package cn.liondance.liondanceapi.advice;


import cn.liondance.liondanceapi.config.SystemInfo;
import cn.liondance.liondanceapi.utils.LionDanceCipherCode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author sunwei
 */
@ControllerAdvice
@AllArgsConstructor
@EnableConfigurationProperties({SystemInfo.class})
public class LionDanceResponseBodyAdvice implements ResponseBodyAdvice {

    private SystemInfo systemInfo;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (systemInfo.getIsEncrypt()) {
            return LionDanceCipherCode.encryptAES(body.toString());
        } else {
            return body;
        }
    }
}
