package com.sdgt.medcare.master.dto.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class ItemDTO {
    @ApiModelProperty(notes = "The database generated item Id.")
    private String id;
    @ApiModelProperty(notes = "Item code.")
    private String code;
    @ApiModelProperty(notes = "Item description.")
    private String desc;
    @ApiModelProperty(notes = "The group code to which the item belong to.")
    private String itemGroupCode;
    @ApiModelProperty(notes = "The group description to which the item belong to")
    private String itemGroupDesc;
    @ApiModelProperty(notes = "The item category code to which the item belong to")
    private String itemCategoryCode;
    @ApiModelProperty(notes = "The item category desc to which the item belong to")
    private String itemCategoryDesc;
}
