package com.sdgt.medcare.master.dto.otims;

import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class OtimsProcedureServiceRequest {
    private String searchKey;
    private String unitCode;
    private String unitServiceMapperId;
    private Long  serviceId;
}
