package com.sdgt.medcare.master.dto.common;

import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import java.util.List;

public class ServiceViewMapperDTO {
    protected ServiceMaster serviceMaster;
    protected List<UnitMaster> unitList;

    public ServiceMaster getServiceMaster() {
        return serviceMaster;
    }

    public void setServiceMaster(ServiceMaster serviceMaster) {
        this.serviceMaster = serviceMaster;
    }

    public List<UnitMaster> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<UnitMaster> unitList) {
        this.unitList = unitList;
    }
}
