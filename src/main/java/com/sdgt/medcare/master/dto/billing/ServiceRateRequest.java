package com.sdgt.medcare.master.dto.billing;

import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class ServiceRateRequest {
    private String financialClassCode;
    private String visitTypeCode;
    private String tariffCode;
    private String unitCode;
}
