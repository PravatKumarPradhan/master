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
@Table(name="m_room", schema = "adt")
public class RoomMaster  extends BaseMaster {

    @Column(name="room_code",unique = true,length = 50)
    private String roomCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="ward_id")
    private WardMaster wardMaster;


    public Boolean getIsolation() {
	return isIsolation;
    }

    public void setIsolation(Boolean isolation) {
	isIsolation = isolation;
    }

    @Column(name="is_isolation")
    private Boolean isIsolation;
    public RoomMaster(String roomCode, OrganizationMaster organizationMaster, UnitMaster unitMaster,WardMaster wardMaster) {
	this.roomCode = roomCode;
	this.organizationMaster = organizationMaster;
	this.unitMaster = unitMaster;
	this.wardMaster=wardMaster;
    }

    public WardMaster getWardMaster() {
	return wardMaster;
    }

    public void setWardMaster(WardMaster wardMaster) {
	this.wardMaster = wardMaster;
    }

    public String getRoomCode() {
	return roomCode;
    }

    public void setRoomCode(String roomCode) {
	this.roomCode = roomCode;
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
    public RoomMaster() {
    }

}
