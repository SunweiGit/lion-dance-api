package cn.liondance.liondanceapi.business.entity;

import cn.liondance.liondanceapi.enums.ExecutionStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author sunwei
 */
@Data
@Builder
@ApiModel(value = "汉字model")
public class LionDanceChineseCharacter {
    @ApiModelProperty(name = "id", notes = "主键", example = "e9049b08-8fbe-11ec-a113-fe79607838af")
    private String          id;
    @ApiModelProperty(name = "sort", notes = "排序", example = "一")
    private Integer         sort;
    @ApiModelProperty(name = "word", notes = "文字", example = "一")
    private String          word;
    @ApiModelProperty(name = "pinyin", notes = "拼音", example = "[\"yī\"]")
    private String          pinyin;
    @ApiModelProperty(name = "pronunciation", notes = "发音", example = "[\"yi1\"]")
    private String          pronunciation;
    @ApiModelProperty(name = "stroke", notes = "笔画", example = "1")
    private String          stroke;
    @ApiModelProperty(name = "radical", notes = "部首", example = "1")
    private String          radical;
    @ApiModelProperty(name = "explanation", notes = "解释", example = "1")
    private String          explanation;
    @ApiModelProperty(name = "more", notes = "更多", example = "1")
    private String          more;
    private String          tag;
    @Enumerated(value = EnumType.STRING)
    private ExecutionStatus executionStatus;

}
