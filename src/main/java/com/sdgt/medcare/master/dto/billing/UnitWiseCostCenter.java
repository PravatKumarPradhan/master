package com.sdgt.medcare.master.dto.billing;

import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class UnitWiseCostCenter {
    private String unitServiceCostCenterMapperId;
    private String unitServiceMapperId;
    private String serviceCode;
    private String costCenterCode;
    private String costCenterDescription;
    private String doctorId;
    private String doctorEmpNo;
    private String doctorName;
    private String departmentCode;
    private String departmentName;
    private Boolean isDefault;
}
