package cn.liondance.liondanceapi.business.entity;

import cn.liondance.liondanceapi.enums.ExecutionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author sunwei
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LionDanceBookChapter {
    @Id
    private String          id;
    private String          name;
    private String          subName;
    private String          author;
    private String          source;
    @Lob
    private String          paragraphs;
    @Lob
    private String          paragraphsPinyin;
    private String          bookId;
    private String          tag;
    private long            sort;
    @CreatedDate
    private LocalDateTime   createdDate;
    @Enumerated(value = EnumType.STRING)
    private ExecutionStatus executionStatus;

}
