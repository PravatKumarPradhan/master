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
public class CommonDiscountContractExclusionConfigurationDTO {
    @EqualsAndHashCode.Exclude private String exclusionConfigurationId;
    @EqualsAndHashCode.Exclude private String chargeCategoryCode;
    private String exclusionChargeCode;

    // To be set true only if wanted to delete
    @EqualsAndHashCode.Exclude private boolean delete;
}
