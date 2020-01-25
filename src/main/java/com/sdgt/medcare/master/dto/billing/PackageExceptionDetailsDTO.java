package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class PackageExceptionDetailsDTO extends BaseDTO {
    private String exceptionType;
    private String unitServiceMapperId;
    private String unitServiceMapperCode;
    private String serviceCode;
    private String itemId;
    private String itemCode;
    private String itemGroupCode;
    private String itemCategoryCode;
    private String groupCode;
    private String subGroupCode;
}
