package com.sdgt.medcare.master.dto.billing.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@EqualsAndHashCode
public class CommonDiscountContractDiscountConfigurationDTO {
    @EqualsAndHashCode.Exclude private String discountDetailsConfigurationId;
    @EqualsAndHashCode.Exclude private String chargeCategoryCode;
    private String chargeCode;
    @EqualsAndHashCode.Exclude private Double discountPercentage;
    @EqualsAndHashCode.Exclude private Double discountAmount;
    @EqualsAndHashCode.Exclude private Double maxDiscountLimitAmount;

    // To be set true only if wanted to delete
    @EqualsAndHashCode.Exclude private boolean delete;
}
