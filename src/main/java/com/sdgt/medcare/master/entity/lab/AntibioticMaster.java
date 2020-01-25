package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_antibiotic", schema = "lab")
public class AntibioticMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy="antibioticClassMaster",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<AntibioticClassAntibioticMapper> antibioticClassAntibioticMappers;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="antibioticMaster",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<OrganismAntibioticMapper> organismAntibioticMappers;

    public List<AntibioticClassAntibioticMapper> getAntibioticClassAntibioticMappers() {
        return antibioticClassAntibioticMappers;
    }

    public void setAntibioticClassAntibioticMappers(List<AntibioticClassAntibioticMapper> antibioticClassAntibioticMappers) {
        this.antibioticClassAntibioticMappers = antibioticClassAntibioticMappers;
    }

    public List<OrganismAntibioticMapper> getOrganismAntibioticMappers() {
        return organismAntibioticMappers;
    }

    public void setOrganismAntibioticMappers(List<OrganismAntibioticMapper> organismAntibioticMappers) {
        this.organismAntibioticMappers = organismAntibioticMappers;
    }
}
