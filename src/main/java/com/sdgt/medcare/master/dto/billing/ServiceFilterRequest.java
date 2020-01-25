package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 * @author Pravat Kumar Pradhan
 */
@Data
public class ServiceFilterRequest extends BaseDTO {
    private String groupId;
    private String groupCode;
    private String subGroupId;
    private String subGroupCode;
    private String serviceCode;
    private String serviceNameOrDescription;
    private Long fromDate;
    private Long toDate;
    private Boolean status;
}
