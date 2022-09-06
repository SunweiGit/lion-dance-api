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
public class LionDancePoetry {
  @Id private String id;
  @Lob private String paragraphs;
  private String title;
  private String author;
  private String chapter;
  private String section;
  private String rhythmic;
  @Lob private String notes;
  private String tag;
  private String dynasty;
  private     long            sort;
  @Enumerated(value = EnumType.STRING)
  private ExecutionStatus executionStatus;

  @CreatedDate private LocalDateTime createdDate;
}
