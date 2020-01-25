package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class PackageFilterRequest extends BaseDTO {
    private String financialClassCode;
    private String packageTypeCode;
    private String tariffCode;
    private String visitTypeCode;
    private String packageServiceCode;
}
