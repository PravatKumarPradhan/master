package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class PackageItemServiceDetailDTO extends BaseDTO {
    private Double serviceRate;
    private Long serviceQty;
    private Double packageUnitDiscountRate;
    private String packageDiscountTypeCode;
    private String unitServiceMapperId;
    private String serviceCode;
    private String itemCode;
}
