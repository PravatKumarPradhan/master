package com.sdgt.medcare.master.dto;

import lombok.Data;

@Data
public class UpgradeExceptionDTO extends BaseDTO {
    private String upgradeType;
    private String subGroupMasterId;
    private String unitId;
    private String orgId;
}
