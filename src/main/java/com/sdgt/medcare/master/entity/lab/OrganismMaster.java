package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_organism", schema = "lab")
public class OrganismMaster extends BaseMaster {

    @Column(name = "full_name")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String fullName;

    @Column(name = "snomed_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
    private String snomedCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="organismMaster",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<OrganismAntibioticMapper> organismAntibioticMappers;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSnomedCode() {
        return snomedCode;
    }

    public void setSnomedCode(String snomedCode) {
        this.snomedCode = snomedCode;
    }

    public List<OrganismAntibioticMapper> getOrganismAntibioticMappers() {
        return organismAntibioticMappers;
    }

    public void setOrganismAntibioticMappers(List<OrganismAntibioticMapper> organismAntibioticMappers) {
        this.organismAntibioticMappers = organismAntibioticMappers;
    }
}
