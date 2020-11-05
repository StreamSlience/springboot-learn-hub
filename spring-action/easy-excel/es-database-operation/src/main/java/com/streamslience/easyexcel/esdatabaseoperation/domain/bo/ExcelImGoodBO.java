package com.streamslience.easyexcel.esdatabaseoperation.domain.bo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 物料Excel导入BO
 *
 * @author StreamSlience
 * @date 2020-11-05 09:50
 */
@Data
public class ExcelImGoodBO {

    @ExcelProperty(index = 0)
    @ApiModelProperty("物料名称")
    private String goodName;

    @ExcelProperty(index = 1)
    @ApiModelProperty("物料编码")
    private String goodCode;

    @ExcelProperty(index = 2)
    @ApiModelProperty("物料描述")
    private String goodDesc;

    @ExcelProperty(index = 3)
    @ApiModelProperty("物料单位")
    private String goodUnit;

    @ExcelIgnore
    @ApiModelProperty("租户标识")
    private Integer tenantId;

}
