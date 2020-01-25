package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class UnitWiseBillingChargesDTO extends BaseDTO {
    private String searchKey;
    private String unitServiceMapperId;
    private String serviceCode;
    private String category;
    private Integer pageSize;
}
