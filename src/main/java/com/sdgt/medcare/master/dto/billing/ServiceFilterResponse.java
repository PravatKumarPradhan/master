package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class ServiceFilterResponse extends BaseDTO {
    private String serviceId;
    private String serviceCode;
    private String serviceDesc;
    private String unitServiceMapperId;
}
