package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.SubDepartmentMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_store", schema = "inventory")
@SequenceGenerator(name = "store_seq", sequenceName = "inventory.store_seq", allocationSize = 1)
@DynamicUpdate
public class StoreMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_Master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_master_id")
    private DepartmentMaster departmentMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_department_master_id")
    private SubDepartmentMaster subDepartmentMaster;

    @Column(name = "is_pharmacy")
    private Boolean isPharmacy;

    @Column(name = "is_replenishment")
    private Boolean isReplenishment;

    @Column(name = "is_linen")
    private Boolean isLinen;

    @Column(name = "is_cssd")
    private Boolean isCssd;

    public StoreMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
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

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }

    public SubDepartmentMaster getSubDepartmentMaster() {
        return subDepartmentMaster;
    }

    public void setSubDepartmentMaster(SubDepartmentMaster subDepartmentMaster) {
        this.subDepartmentMaster = subDepartmentMaster;
    }

    public Boolean getPharmacy() {
        return isPharmacy;
    }

    public void setPharmacy(Boolean pharmacy) {
        isPharmacy = pharmacy;
    }

    public Boolean getReplenishment() {
        return isReplenishment;
    }

    public void setReplenishment(Boolean replenishment) {
        isReplenishment = replenishment;
    }

    public Boolean getLinen() {
        return isLinen;
    }

    public void setLinen(Boolean linen) {
        isLinen = linen;
    }

    public Boolean getCssd() {
        return isCssd;
    }

    public void setCssd(Boolean cssd) {
        isCssd = cssd;
    }
}
