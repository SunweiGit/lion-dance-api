package cn.liondance.liondanceapi.business.entity;

import cn.liondance.liondanceapi.enums.ExecutionStatus;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * @author sunwei
 */
@Data
@ApiModel
public class LionDanceObject implements Serializable {
    private String     id;
    private String     cnName;
    private String     image;
    private String     cnVoice;
    private String     enName;
    private String     enVoice;
    private String     src;
    private Integer    sort;
    private JSONObject tag;

    @Enumerated(value = EnumType.STRING)
    private ExecutionStatus executionStatus;

}
