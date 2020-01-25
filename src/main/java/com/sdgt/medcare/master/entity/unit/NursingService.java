package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_nursing_service")
public class NursingService extends BaseMaster{

        @Column(name="nursing_service_code",unique = true,length = 50)
        private String nursingServiceCode;

        @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

        @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
        @JoinColumn(name="unit_id")
        private UnitMaster unitMaster;

    public NursingService(String nursingServiceCode, OrganizationMaster organizationMaster, UnitMaster unitMaster) {
        this.nursingServiceCode = nursingServiceCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
    }

    public OrganizationMaster getOrganizationMaster() {
            return organizationMaster;
        }

    public String getNursingServiceCode() {
        return nursingServiceCode;
    }

    public void setNursingServiceCode(String nursingServiceCode) {
        this.nursingServiceCode = nursingServiceCode;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
            this.organizationMaster = organizationMaster;
        }

        public UnitMaster getUnitMaster() {
            return unitMaster;
        }

        public void setUnitMaster(UnitMaster unitMaster) {
            this.unitMaster = unitMaster;
        }

    public NursingService() {
    }
}



