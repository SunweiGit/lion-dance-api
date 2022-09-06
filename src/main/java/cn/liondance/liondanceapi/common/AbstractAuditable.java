package cn.liondance.liondanceapi.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author sunwei
 */

@EqualsAndHashCode(callSuper = false)
@Data
@MappedSuperclass
public abstract class AbstractAuditable {
    @ApiModelProperty(name = "createdBy")
    @CreatedBy
    public  String        createdBy;
    @ApiModelProperty(name = "createdDate")
    @CreatedDate
    public  LocalDateTime createdDate;
    @ApiModelProperty(name = "lastModifiedBy")
    @LastModifiedBy
    public  String        lastModifiedBy;
    @ApiModelProperty(name = "lastModifiedDate")
    @LastModifiedDate
    public  LocalDateTime lastModifiedDate;
    @Id
    @Column(name = "id", nullable = false, length = 64)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "guid")
    @ApiModelProperty(name = "id", example = "uuid")
    private String        id;


}
