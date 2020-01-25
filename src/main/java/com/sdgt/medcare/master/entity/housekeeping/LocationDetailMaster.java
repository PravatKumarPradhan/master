package com.sdgt.medcare.master.entity.housekeeping;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.WardMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_location_detail", schema = "public")
public class LocationDetailMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    public WardMaster getWardMaster() {
        return wardMaster;
    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="location_id")
    private LocationMaster locationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="sub_location_id")
    private SubLocationMaster subLocationMaster;

    public void setWardMaster(WardMaster wardMaster) {
        this.wardMaster = wardMaster;
    }

    @Column(name = "is_housekeeping")
    private Boolean isHousekeeping;

    @Column(name = "is_mrd")
    private Boolean isMrd;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="ward_master_id")
    private WardMaster wardMaster;

    @Column(name = "clinic_master_id")
    private Long clinicMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private DepartmentMaster departmentMaster;

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

    public LocationMaster getLocationMaster() {
        return locationMaster;
    }

    public void setLocationMaster(LocationMaster locationMaster) {
        this.locationMaster = locationMaster;
    }

    public SubLocationMaster getSubLocationMaster() {
        return subLocationMaster;
    }

    public void setSubLocationMaster(SubLocationMaster subLocationMaster) {
        this.subLocationMaster = subLocationMaster;
    }

    public Boolean getHousekeeping() {
        return isHousekeeping;
    }

    public void setHousekeeping(Boolean housekeeping) {
        isHousekeeping = housekeeping;
    }

    public Boolean getMrd() {
        return isMrd;
    }

    public void setMrd(Boolean mrd) {
        isMrd = mrd;
    }

    public Long getClinicMaster() {
        return clinicMaster;
    }

    public void setClinicMaster(Long clinicMaster) {
        this.clinicMaster = clinicMaster;
    }

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }
}
