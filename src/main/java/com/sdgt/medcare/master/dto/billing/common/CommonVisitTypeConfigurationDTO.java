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
public class CommonVisitTypeConfigurationDTO {
    @EqualsAndHashCode.Exclude private String visitTypeConfigurationId;
    private String visitTypeCode;
    @EqualsAndHashCode.Exclude private Long noOfVisit;
    @EqualsAndHashCode.Exclude private Double visitLimit;
    @EqualsAndHashCode.Exclude private Double preApprovedVisitLimit;

    // To be set true only if wanted to delete
    @EqualsAndHashCode.Exclude private boolean delete;
}
