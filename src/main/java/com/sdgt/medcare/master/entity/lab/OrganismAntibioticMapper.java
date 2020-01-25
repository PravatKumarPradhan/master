package com.sdgt.medcare.master.entity.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "m_organism_antibiotic_mapper", schema = "lab")
public class OrganismAntibioticMapper extends AbstractBaseEntity {

    @JsonIgnoreProperties("organismAntibioticMappers")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "antibiotic_id")
    private AntibioticMaster antibioticMaster;

    @JsonIgnoreProperties("organismAntibioticMappers")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organism_id")
    private OrganismMaster organismMaster;

    @JsonIgnoreProperties("organismAntibioticMappers")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organism_group_id")
    private OrganismGroupMaster organismGroupMaster;

    public AntibioticMaster getAntibioticMaster() {
        return antibioticMaster;
    }

    public void setAntibioticMaster(AntibioticMaster antibioticMaster) {
        this.antibioticMaster = antibioticMaster;
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
