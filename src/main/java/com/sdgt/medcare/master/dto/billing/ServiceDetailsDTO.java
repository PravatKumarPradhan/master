package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import lombok.Data;

import java.util.Collection;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class ServiceDetailsDTO extends BaseDTO {
    private String financialClassCode;
    private String tariffCode;
    private String visitTypeCode;
    private String serviceCode;
    private String companyServiceCode;
    private Collection<DependentService> dependentServiceCollection;
    private Double rate;
    private UnitServiceMapper unitServiceMapper;
    private boolean isFetchSuccess = false;
    private String errorMessage;
}
