package com.sdgt.medcare.master.dto.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class ServiceDTO {
    @ApiModelProperty(notes = "The database generated service Id.")
    private String id;
    @ApiModelProperty(notes = "Service code.")
    private String code;
    @ApiModelProperty(notes = "Service description.")
    private String desc;
    @ApiModelProperty(notes = "The group code to which the service belong to.")
    private String groupCode;
    @ApiModelProperty(notes = "The group description to which the service belong to")
    private String groupDesc;
    @ApiModelProperty(notes = "The sub group code to which the service belong to")
    private String subGroupCode;
    @ApiModelProperty(notes = "The sub group desc to which the service belong to")
    private String subGroupDesc;
}
