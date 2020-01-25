package com.sdgt.medcare.master.entity.unit;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.GenderMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_ward",schema = "adt")
@Entity
public class WardMaster extends BaseMaster {

    @Column(name="ward_master_code",unique = true,length = 50)
    private String wardMasterCode;

    @Column(name="is_er")
    private Boolean isEr   ;

    @Column (name="is_day_care")
    private Boolean isDaycare ;

    @Column (name="is_icu")
    private Boolean isIcu ;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="gender_id")
    private GenderMaster genderMaster;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="floor_id")
    private FloorMaster floorMaster;

    @Column (name="threshold_percentage")
    private Integer thresholdPercentage;

    @Column (name="release_percentage")
    private Integer releasePercentage;


    public WardMaster(String wardMasterCode, Boolean isEr,Boolean isIcu, Boolean isDaycare, GenderMaster genderMaster,
                      OrganizationMaster organizationMaster, UnitMaster unitMaster, BlockMaster blockMaster,FloorMaster floorMaster, Integer thresholdPercentage,
                      Integer releasePercentage) {
	this.wardMasterCode = wardMasterCode;
	this.isEr = isEr;
	this.isIcu=isIcu;
	this.isDaycare = isDaycare;
	this.genderMaster = genderMaster;
	this.organizationMaster = organizationMaster;
	this.unitMaster = unitMaster;
	this.blockMaster = blockMaster;
	this.floorMaster=floorMaster;
	this.thresholdPercentage = thresholdPercentage;
	this.releasePercentage = releasePercentage;
    }
    /* @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="store_id")
    private StoreMas genderMaster;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="block_id")
    private BlockMaster blockMaster;

    public FloorMaster getFloorMaster() {
	return floorMaster;
    }

    public void setFloorMaster(FloorMaster floorMaster) {
	this.floorMaster = floorMaster;
    }

    public String getWardMasterCode() {
	return wardMasterCode;
    }

    public void setWardMasterCode(String wardMasterCode) {
	this.wardMasterCode = wardMasterCode;
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

    public BlockMaster getBlockMaster() {
	return blockMaster;
    }

    public void setBlockMaster(BlockMaster blockMaster) {
	this.blockMaster = blockMaster;
    }

    public Boolean getEr() {
	return isEr;
    }

    public void setEr(Boolean er) {
	isEr = er;
    }

    public Boolean getDaycare() {
	return isDaycare;
    }

    public void setDaycare(Boolean daycare) {
	isDaycare = daycare;
    }

    public GenderMaster getGenderMaster() {
	return genderMaster;
    }

    public void setGenderMaster(GenderMaster genderMaster) {
	this.genderMaster = genderMaster;
    }

    public WardMaster() {
    }

    public Boolean getIcu() {
	return isIcu;
    }

    public void setIcu(Boolean icu) {
	isIcu = icu;
    }

    public Integer getThresholdPercentage() {
        return thresholdPercentage;
    }

    public void setThresholdPercentage(Integer thresholdPercentage) {
        this.thresholdPercentage = thresholdPercentage;
    }

    public Integer getReleasePercentage() {
        return releasePercentage;
    }

    public void setReleasePercentage(Integer releasePercentage) {
        this.releasePercentage = releasePercentage;
    }
}
