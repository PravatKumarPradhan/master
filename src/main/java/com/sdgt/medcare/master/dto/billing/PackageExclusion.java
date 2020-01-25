package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
public class PackageExclusion extends BaseDTO {
    private String exceptionType;
    private String unitServiceMapperId;
    private String serviceCode;
    private String itemCode;
}
