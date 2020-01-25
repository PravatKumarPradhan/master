package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_organism_morphology_mapper", schema = "lab")
public class OrganismMorphologyMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organism_id")
    private OrganismMaster organismMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "morphology_id")
    private MorphologyMaster morphologyMaster;

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

    public MorphologyMaster getMorphologyMaster() {
        return morphologyMaster;
    }

    public void setMorphologyMaster(MorphologyMaster morphologyMaster) {
        this.morphologyMaster = morphologyMaster;
    }
}
