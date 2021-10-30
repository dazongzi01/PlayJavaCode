package com.dzz.gen.common;

import com.dzz.gen.constants.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageParamRequest {

    @ApiModelProperty(value = "页码", example= Constants.DEFAULT_PAGE + "")
    private int pageNum;

    @ApiModelProperty(value = "每页数量", example = Constants.DEFAULT_LIMIT + "")
    private int pageSize;
}
