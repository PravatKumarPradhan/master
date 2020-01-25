package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import lombok.Data;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class UnitWiseService {
    private String unitCode;
    private String groupCode;
    private String groupDesc;
    private String subGroupCode;
    private String subGroupDesc;
    private String unitServiceMapperId;
    private String serviceCode;
    private String serviceName;
    private Boolean isDoctorRequired;
    private String groupId;
    private String subGroupId;
    private Long fromDate;
    private Long toDate;
    private Boolean status;
    private String serviceId;
    private ServiceMaster serviceMaster;
    private ItemMaster itemMaster;
    private UnitServiceMapper unitServiceMapper;
}
