package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class PackageFilterResponse extends BaseDTO {
    private String packageDesc;
    private String packageCode;
    private String unitServiceMapperId;
    private String packageServiceCode;
    private String packageTypeCode;
    private String genderCode;
    private Short minimumAgeCriteria;
    private Double packageRate;
    private Double packageDiscountRate;
    private Double packageRoundOffRate;
    private Double packageRevisedRate;
    private String tariffCode;
    private String visitTypeCode;
    private String financialClassCode;
    private Date effectiveStartDate;
    private Date effectiveEndDate;
    private List<PackageCap> packageCapList;
    private List<PackageItemService> packageItemServiceList;
}
