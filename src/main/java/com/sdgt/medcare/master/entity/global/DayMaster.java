package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_day")
public class DayMaster extends BaseMaster {
    
    @Column
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String dayCode;

    public String getDayCode() {
        return dayCode;
    }

    public void setDayCode(String dayCode) {
        this.dayCode = dayCode;
    }
}
