package cn.liondance.liondanceapi.business.vo;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author sunwei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LionDanceBookVO {
  private String id;
  private String name;
  private JSONArray summary;
  private String author;
  private JSONArray tag;
  private String status;
  private LocalDateTime createdDate;
}
