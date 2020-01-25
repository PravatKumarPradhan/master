package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "m_antibiotic_class", schema = "lab")
public class AntibioticClassMaster extends BaseMaster {

    @OneToMany(fetch = FetchType.LAZY,mappedBy="antibioticClassMaster",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<AntibioticClassAntibioticMapper> antibioticClassAntibioticMappers;

    public List<AntibioticClassAntibioticMapper> getAntibioticClassAntibioticMappers() {
        return antibioticClassAntibioticMappers;
    }

    public void setAntibioticClassAntibioticMappers(List<AntibioticClassAntibioticMapper> antibioticClassAntibioticMappers) {
        this.antibioticClassAntibioticMappers = antibioticClassAntibioticMappers;
    }
}
