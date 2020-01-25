package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_organism_organism_group_mapper", schema = "lab")
public class OrganismOrganismGroupMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organism_id")
    private OrganismMaster organismMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organism_group_id")
    private OrganismGroupMaster organismGroupMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public OrganismMaster getOrganismMaster() {
        return organismMaster;
    }

    public void setOrganismMaster(OrganismMaster organismMaster) {
        this.organismMaster = organismMaster;
    }

    public OrganismGroupMaster getOrganismGroupMaster() {
        return organismGroupMaster;
    }

    public void setOrganismGroupMaster(OrganismGroupMaster organismGroupMaster) {
        this.organismGroupMaster = organismGroupMaster;
    }
}
