package cn.liondance.liondanceapi.business.entity;

import cn.liondance.liondanceapi.enums.ExecutionStatus;
import cn.liondance.liondanceapi.enums.RouteType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * @author sunwei
 */
@Data
@Builder
@Entity
@ApiModel(value = "推荐model")
@AllArgsConstructor
@NoArgsConstructor
public class LionDanceRecommend {
    @Id
    @ApiModelProperty(name = "id", notes = "主键", example = "e9049b08-8fbe-11ec-a113-fe79607838af")
    private String          id;
    @ApiModelProperty(name = "name", notes = "名称", example = "汉字")
    private String          name;
    @ApiModelProperty(name = "name", notes = "名称", example = "学习汉字")
    private String          description;
    @ApiModelProperty(name = "name", notes = "名称", example = "http://liondance.cn:9000")
    private String          imageUrl;
    private Long            sort;
    private String          route;
    @Enumerated(value = EnumType.STRING)
    private RouteType       routeType;
    @Enumerated(value = EnumType.STRING)
    private ExecutionStatus executionStatus;

}
