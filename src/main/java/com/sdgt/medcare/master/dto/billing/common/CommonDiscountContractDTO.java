package com.sdgt.medcare.master.dto.billing.common;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Date;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@EqualsAndHashCode
public abstract class CommonDiscountContractDTO extends BaseDTO {
    private String masterId;

    // To be set true only if wanted to delete
    @EqualsAndHashCode.Exclude private boolean delete;


    //if master provided these are not mandatory field. Unless you want to update
    private String persistStatus;
    private Date date;
    private String companyTypeCode;
    private String companyTypeDesc;
    private String companyCode;
    private String companyDesc;
    private String employeeCode;
    private String code;
    private String desc;
    private String referenceNo;
    private Date effectiveStartDate;
    private Date effectiveEndDate;

    private Collection<CommonUnitContractDiscountMapperDTO> applicableUnitCodeList;
    private Collection<CommonDiscountContractConfigurationDTO> configurationList;
}
