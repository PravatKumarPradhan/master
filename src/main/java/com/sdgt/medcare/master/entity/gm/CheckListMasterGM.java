package com.sdgt.medcare.master.entity.gm;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_checklist_gm", schema = "gm")
public class CheckListMasterGM extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="checklist_type_id")
    private CheckListTypeMaster checkListTypeMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public CheckListTypeMaster getCheckListTypeMaster() {
        return checkListTypeMaster;
    }

    public void setCheckListTypeMaster(CheckListTypeMaster checkListTypeMaster) {
        this.checkListTypeMaster = checkListTypeMaster;
    }
}
