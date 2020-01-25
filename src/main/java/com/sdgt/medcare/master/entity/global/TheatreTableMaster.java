package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_theatre_table",schema = "otims")
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
public class TheatreTableMaster extends BaseMaster {

    @MastersFieldCustomAnnotation(nullable = false,displayName = "Organisation",sequence = 0)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @MastersFieldCustomAnnotation(nullable = false,displayName = "Theater Table Type",sequence = 1)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_table_type_id")
    private TheatreTableTypeMaster theatreTableTypeMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_room_id")
    private TheatreRoomMaster theatreRoomMaster;

    public TheatreRoomMaster getTheatreRoomMaster() {
        return theatreRoomMaster;
    }

    public void setTheatreRoomMaster(TheatreRoomMaster theatreRoomMaster) {
        this.theatreRoomMaster = theatreRoomMaster;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public TheatreTableTypeMaster getTheatreTableTypeMaster() {
        return theatreTableTypeMaster;
    }

    public void setTheatreTableTypeMaster(TheatreTableTypeMaster theatreTableTypeMaster) {
        this.theatreTableTypeMaster = theatreTableTypeMaster;
    }
}
