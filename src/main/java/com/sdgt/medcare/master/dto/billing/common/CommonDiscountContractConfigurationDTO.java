package com.sdgt.medcare.master.dto.billing.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@EqualsAndHashCode
public class CommonDiscountContractConfigurationDTO {
    private String configurationId;

    // To be set true only if wanted to delete
    @EqualsAndHashCode.Exclude private boolean delete;

    // Provide only if need to update
    private String gradeCode;
    private String gradeDesc;
    private String fallBackTariffCode;
    private String fallBackTariffDesc;
    private Double coSharePercentage;
    private Boolean excludeDiscount;
    private Boolean includeAllVisitType;
    private Long totalNoOfVisit;
    private Double totalVisitLimit;
    private Double preApprovedVisitLimit;

    @Setter(value = AccessLevel.NONE)
    private Collection<CommonVisitTypeConfigurationDTO> visitTypeConfigurationDTOList = new HashSet<>();

    public void setVisitTypeConfigurationDTOList(final Set<CommonVisitTypeConfigurationDTO> commonVisitTypeConfigurationDTOCollection) {
        visitTypeConfigurationDTOList.addAll(commonVisitTypeConfigurationDTOCollection);
    }

    @Setter(value = AccessLevel.NONE)
    private Collection<CommonDiscountContractExclusionConfigurationDTO> exclusionConfigurationDTOList = new HashSet<>();

    /**
     * Setter.
     *
     * @param commonExclusionConfigurationSet given.
     */
    public void setExclusionConfigurationDTOList(final Set<CommonDiscountContractExclusionConfigurationDTO> commonExclusionConfigurationSet) {
        exclusionConfigurationDTOList.addAll(commonExclusionConfigurationSet);
    }

    @Setter(value = AccessLevel.NONE)
    private Collection<CommonDiscountContractDiscountConfigurationDTO> discountConfigurationDTOList = new HashSet<>();

    /**
     * Setter.
     * @param discountConfigurationDTOSet given.
     */
    public void setDiscountConfigurationDTOList(final Set<CommonDiscountContractDiscountConfigurationDTO> discountConfigurationDTOSet) {
        discountConfigurationDTOList.addAll(discountConfigurationDTOSet);
    }
}
