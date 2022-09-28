package cn.liondance.liondanceapi.business.dto;

import cn.liondance.liondanceapi.business.model.TagDTO;
import cn.liondance.liondanceapi.enums.ExecutionStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link cn.liondance.liondanceapi.business.entity.LionDanceChineseCharacter} entity
 */
@Data
public class LionDanceChineseCharacterDto implements Serializable {
    private final String          id;
    private final String          word;
    private final List<TagDTO>    pinyin;
    private final List<TagDTO>    pronunciation;
    private final String          stroke;
    private final String          radical;
    private final String          explanation;
    private final String          more;
    private final List<TagDTO>    tag;
    private final List<TagDTO>    words;
    private final int             wordCount;
    private final ExecutionStatus executionStatus;

}