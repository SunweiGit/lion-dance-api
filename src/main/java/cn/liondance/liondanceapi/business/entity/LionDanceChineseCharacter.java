package cn.liondance.liondanceapi.business.entity;

import cn.liondance.liondanceapi.enums.ExecutionStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author sunwei
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LionDanceChineseCharacter {
    @Id
    @ApiModelProperty(name = "id", notes = "主键", example = "e9049b08-8fbe-11ec-a113-fe79607838af")
    private String          id;
    @ApiModelProperty(name = "sort", notes = "排序", example = "一")
    private Long            sort;
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
    @Lob
    @ApiModelProperty(name = "explanation", notes = "解释", example = "1")
    private String          explanation;
    @Lob
    @ApiModelProperty(name = "more", notes = "更多", example = "1")
    private String          more;
    private String          tag;
    @Lob
    private String          words;
    private int             wordCount;
    @Enumerated(value = EnumType.STRING)
    private ExecutionStatus executionStatus;

}
