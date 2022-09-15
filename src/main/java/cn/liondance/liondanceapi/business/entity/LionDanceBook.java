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
public class LionDanceBook {
    @Id
    private String          id;
    private String          name;
    @Lob
    private String          summary;
    private Long            sort;
    private String          author;
    private String          tag;
    private String          status;
    @CreatedDate
    private LocalDateTime   createdDate;
    @Enumerated(value = EnumType.STRING)
    private ExecutionStatus executionStatus;

}
