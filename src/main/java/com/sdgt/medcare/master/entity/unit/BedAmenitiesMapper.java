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

@Entity
@Table(name="m_bed_amenities_mapper",schema = "adt")
public class BedAmenitiesMapper extends BaseMaster {
    @Column(name="bed_amenities_code",length = 50)
     private String bedAmenitiesCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="bed_id")
    private BedMaster bedMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="amenities_id")
    private AmenitiesMaster amenitiesMaster;

    public BedAmenitiesMapper() {
    }

    public BedAmenitiesMapper(String bedAmenitiesCode, OrganizationMaster organizationMaster, UnitMaster unitMaster, BedMaster bedMaster, AmenitiesMaster amenitiesMaster) {
        this.bedAmenitiesCode = bedAmenitiesCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.bedMaster = bedMaster;
        this.amenitiesMaster = amenitiesMaster;
    }

    public String getBedAmenitiesCode() {
        return bedAmenitiesCode;
    }

    public void setBedAmenitiesCode(String bedAmenitiesCode) {
        this.bedAmenitiesCode = bedAmenitiesCode;
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

    public AmenitiesMaster getAmenitiesMaster() {
        return amenitiesMaster;
    }

    public void setAmenitiesMaster(AmenitiesMaster amenitiesMaster) {
        this.amenitiesMaster = amenitiesMaster;
    }
}
