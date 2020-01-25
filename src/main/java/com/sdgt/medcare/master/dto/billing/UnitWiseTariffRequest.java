package com.sdgt.medcare.master.dto.billing;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Builder
@Data
@ToString
public class UnitWiseTariffRequest {
    private String financialClassCode;
    private String tariffCode;
    private String payerCode;
    private String visitTypeCode;
    private String serviceCode;
    private String unitCode;
}
