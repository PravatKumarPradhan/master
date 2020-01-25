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
@Table(name="m_room_amenitiesMapper",schema = "service")
public class RoomAmenitiesMapper extends BaseMaster {

    @Column(name="room_amenities_code",length = 50)
     private String  roomAmenitiesCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="room_id")
    private RoomMaster roomMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="amenities_id")
    private AmenitiesMaster amenitiesMaster;

    public RoomAmenitiesMapper() {
    }

    public RoomAmenitiesMapper(String roomAmenitiesCode, OrganizationMaster organizationMaster, UnitMaster unitMaster, RoomMaster roomMaster, AmenitiesMaster amenitiesMaster) {
        this.roomAmenitiesCode = roomAmenitiesCode;
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.roomMaster = roomMaster;
        this.amenitiesMaster = amenitiesMaster;
    }

    public String getRoomAmenitiesCode() {
        return roomAmenitiesCode;
    }

    public void setRoomAmenitiesCode(String roomAmenitiesCode) {
        this.roomAmenitiesCode = roomAmenitiesCode;
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

    public RoomMaster getRoomMaster() {
        return roomMaster;
    }

    public void setRoomMaster(RoomMaster roomMaster) {
        this.roomMaster = roomMaster;
    }

    public AmenitiesMaster getAmenitiesMaster() {
        return amenitiesMaster;
    }

    public void setAmenitiesMaster(AmenitiesMaster amenitiesMaster) {
        this.amenitiesMaster = amenitiesMaster;
    }
}
