package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_bed_nursing_station_mapper",schema = "adt")
@Entity
public class BedNursingStationMapper extends BaseMaster {

    @Column(name="bed_nursing_code",length = 50)
    private String  bedNursingCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="bed_id")
    private  BedMaster bedMaster;



    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="nursing_station_id")
    private NursingStationMaster nursingStationMaster;

    public BedNursingStationMapper(String bedNursingCode, OrganizationMaster organizationMaster, UnitMaster unitMaster, BedMaster bedMaster, NursingStationMaster nursingStationMaster) {
        this.bedNursingCode = bedNursingCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.bedMaster = bedMaster;
        this.nursingStationMaster = nursingStationMaster;
    }

    public String getBedNursingCode() {
        return bedNursingCode;
    }

    public void setBedNursingCode(String bedNursingCode) {
        this.bedNursingCode = bedNursingCode;
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

    public BedMaster getBedMaster() {
        return bedMaster;
    }

    public void setBedMaster(BedMaster bedMaster) {
        this.bedMaster = bedMaster;
    }

    public NursingStationMaster getNursingStationMaster() {
        return nursingStationMaster;
    }

    public void setNursingStationMaster(NursingStationMaster nursingStationMaster) {
        this.nursingStationMaster = nursingStationMaster;
    }

    public BedNursingStationMapper() {
    }

}
