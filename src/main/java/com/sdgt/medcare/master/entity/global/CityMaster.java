package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table(name="m_city")
@Entity
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
public class CityMaster  extends BaseMaster {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    @MastersFieldCustomAnnotation(displayName = "State",sequence = 3,nullable = false)
    private StateMaster stateMaster;

    public CityMaster() {

    }





    public CityMaster(StateMaster stateMaster) {
        this.stateMaster = stateMaster;
    }

    public StateMaster getStateMaster() {
        return stateMaster;
    }

    public void setStateMaster(StateMaster stateMaster) {
        this.stateMaster = stateMaster;
    }


}


