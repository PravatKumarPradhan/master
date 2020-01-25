package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "m_instruction", schema = "public")
public class InstructionMaster extends BaseMaster {


    @Column(name = "is_medication")
    private Character isMedication;


    public Character getIsMedication() {
        
        return isMedication;
    }

    public void setIsMedication(Character isMedication) {
        
        this.isMedication = isMedication;
    }


}
