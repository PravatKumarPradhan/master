package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
public class PackageItemService extends BaseDTO {
    private String unitServiceMapperId;
    private String serviceCode;
    private String serviceDesc;
    private String itemCode;
    private String itemDesc;
    private Double rate;
    private Long serviceQty;
    private Double packageUnitRate;
    private Double packageUnitDiscountRate;
    private Boolean discountTypeStatus;
}
