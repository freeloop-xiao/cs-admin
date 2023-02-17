package com.cs.admin.common.dto;

import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 算法签名保存DTO
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/5 15:28
 */
@Data
@ApiModel(value="算法签名保存DTO", description="算法签名保存DTO")
public class AlgorithmDTO {

    @ApiModelProperty("算法")
    private Algorithm algorithm;

    @ApiModelProperty("md5")
    private String md5;

    public AlgorithmDTO(Algorithm algorithm,String md5){
        this.algorithm = algorithm;
        this.md5 = md5;
    }

}
