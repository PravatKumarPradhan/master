package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "m_antibiotic_class_mapper", schema = "lab")
public class AntibioticClassAntibioticMapper extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "antibiotic_class_id")
    private AntibioticClassMaster antibioticClassMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "antibiotic_id")
    private AntibioticMaster antibioticMaster;

    public AntibioticClassMaster getAntibioticClassMaster() {
        return antibioticClassMaster;
    }

    public void setAntibioticClassMaster(AntibioticClassMaster antibioticClassMaster) {
        this.antibioticClassMaster = antibioticClassMaster;
    }

    public AntibioticMaster getAntibioticMaster() {
        return antibioticMaster;
    }

    public void setAntibioticMaster(AntibioticMaster antibioticMaster) {
        this.antibioticMaster = antibioticMaster;
    }
}
