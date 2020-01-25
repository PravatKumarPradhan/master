package com.sdgt.medcare.master.dto.billing;

import lombok.Data;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
public class DependentService {
    private String serviceCode;
    private String serviceName;
    private String serviceGroupCode;
    private String serviceGroupName;
    private String serviceSubGroupCode;
    private String serviceSubGroupName;
    private String companyServiceCode;
    private String companyServiceName;
    private String dependentServiceCode;
    private String dependentServiceName;
    private String dependentServiceGroupCode;
    private String dependentServiceGroupName;
    private String dependentServiceSubGroupCode;
    private String dependentServiceSubGroupName;
    private String dependentCompanyServiceCode;
    private String dependentCompanyServiceName;
    private Double percentageOnActualServiceRate;
    private Double flatRate;
    private Boolean isOverriddenRate;
}
