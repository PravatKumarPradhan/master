package com.sdgt.medcare.master.entity.unit;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.BedStatus;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_bed",schema = "adt")
@Entity
public class BedMaster extends BaseMaster {

    @Column(name="bed_code",unique = true,length = 50)
    private String bedCode;

    @Column(name="is_non_census")
    private Boolean isNonCensus;

    @Column(name="is_virtual")
    private Boolean isVirtual;

    @Column(name="is_cradle")
    private Boolean isCradle;

    @Column(name="is_blocked")
    private Boolean isBlocked;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="organization_id")
    private OrganizationMaster  organizationMaster;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_code",referencedColumnName="code")
    private UnitMaster unitMasterCode;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="wardMaster_id")
    private WardMaster wardMaster;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="bed_category_id")
    private BedCategory bedCategory;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="roomMaster_id")
    private RoomMaster roomMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="block_id")
    private BlockMaster blockMaster;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="floor_id")
    private FloorMaster floorMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="bed_status_id")
    private BedStatus bedStatus;

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public Boolean getNonCensus() {
        return isNonCensus;
    }

    public void setNonCensus(Boolean nonCensus) {
        isNonCensus = nonCensus;
    }

    public Boolean getVirtual() {
        return isVirtual;
    }

    public void setVirtual(Boolean virtual) {
        isVirtual = virtual;
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

    public UnitMaster getUnitMasterCode() {
        return unitMasterCode;
    }

    public void setUnitMasterCode(UnitMaster unitMasterCode) {
        this.unitMasterCode = unitMasterCode;
    }

    public WardMaster getWardMaster() {
        return wardMaster;
    }

    public void setWardMaster(WardMaster wardMaster) {
        this.wardMaster = wardMaster;
    }

    public BedCategory getBedCategory() {
        return bedCategory;
    }

    public void setBedCategory(BedCategory bedCategory) {
        this.bedCategory = bedCategory;
    }

    public RoomMaster getRoomMaster() {
        return roomMaster;
    }

    public void setRoomMaster(RoomMaster roomMaster) {
        this.roomMaster = roomMaster;
    }

    public BedStatus getBedStatus() {
        return bedStatus;
    }

    public void setBedStatus(BedStatus bedStatus) {
        this.bedStatus = bedStatus;
    }

    public BlockMaster getBlockMaster() {
        return blockMaster;
    }

    public void setBlockMaster(BlockMaster blockMaster) {
        this.blockMaster = blockMaster;
    }

    public FloorMaster getFloorMaster() {
        return floorMaster;
    }

    public void setFloorMaster(FloorMaster floorMaster) {
        this.floorMaster = floorMaster;
    }

    public Boolean getCradle() {
        return isCradle;
    }

    public void setCradle(Boolean cradle) {
        isCradle = cradle;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}
