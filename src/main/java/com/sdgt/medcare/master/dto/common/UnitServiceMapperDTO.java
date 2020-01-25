package com.sdgt.medcare.master.dto.common;


import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;

import java.util.List;

public class UnitServiceMapperDTO {
    private List<UnitServiceMapper> unitServiceMapper ;

    private ServiceMaster serviceMaster;

    private OrganizationMaster organizationMaster;



    public List<UnitServiceMapper> getUnitServiceMapper() {
        return unitServiceMapper;
    }

    public void setUnitServiceMapper(List<UnitServiceMapper> unitServiceMapper) {
        this.unitServiceMapper = unitServiceMapper;
    }

    public ServiceMaster getServiceMaster() {
        return serviceMaster;
    }

    public void setServiceMaster(ServiceMaster serviceMaster) {
        this.serviceMaster = serviceMaster;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }


}
