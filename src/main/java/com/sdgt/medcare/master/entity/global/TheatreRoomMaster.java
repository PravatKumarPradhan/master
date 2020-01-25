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
@Table(name="m_theatre_room",schema = "otims")
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
public class TheatreRoomMaster extends BaseMaster {


    @MastersFieldCustomAnnotation(nullable = false,sequence = 0,displayName = "Organisation")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @MastersFieldCustomAnnotation(nullable = false,sequence = 1,displayName = "TheatreType")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_type_id")
    private TheatreTypeMaster theatreTypeMaster;

    public TheatreTypeMaster getTheatreTypeMaster() {
        return theatreTypeMaster;
    }

    public void setTheatreTypeMaster(TheatreTypeMaster theatreTypeMaster) {
        this.theatreTypeMaster = theatreTypeMaster;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

}
