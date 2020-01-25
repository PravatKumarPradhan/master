package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_therapy_room",schema = "public")
public class TherapyRoomMaster extends BaseMaster {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_master_id")
    private OrganizationMaster organizationMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "therapy_type_id")
    private TherapyTypeMaster therapyTypeMaster;


    public TherapyTypeMaster getTherapyTypeMaster() {
		return therapyTypeMaster;
	}

	public void setTherapyTypeMaster(TherapyTypeMaster therapyTypeMaster) {
		this.therapyTypeMaster = therapyTypeMaster;
	}

	public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

}
