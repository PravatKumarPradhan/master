package com.sdgt.medcare.master.dto.billing;

import lombok.Builder;
import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
@Builder
public class ServiceDetailResponse {
    private String code;
    private String description;
    private String groupCode;
    private String groupDescription;
    private String subGroupCode;
    private String subGroupDescription;
}
