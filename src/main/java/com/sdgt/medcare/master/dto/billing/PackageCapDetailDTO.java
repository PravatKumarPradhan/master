package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class PackageCapDetailDTO extends BaseDTO {
    private List<PackageExceptionDetailsDTO> packageExceptionDetailsList;

    private String groupConsumable;
    private String groupId;
    private String groupCode;
    private String subGroupId;
    private String subGroupCode;
    private String itemGroupId;
    private String itemGroupCode;
    private String itemCategoryId;
    private String itemCategoryCode;
    private Double capRate;
}
