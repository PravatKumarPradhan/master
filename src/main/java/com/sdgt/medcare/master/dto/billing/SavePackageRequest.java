package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
@Builder
public class SavePackageRequest extends BaseDTO {
    private String packageCode;
    private String packageName;
    private Short minAge;
    private Short maxAge;
    private Date effectiveStartDate;
    private Date effectiveEndDate;
    private String packageTypeCode;
    private String genderCode;
    private Long packageServiceMapperId;
    private List<PackageConfiguration> packageConfigurationList;
}

