package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_counter")
public class CounterMaster extends BaseMaster {
    @Column(name="counter_code")
    private String counterCode;

    public void setCounterCode(String counterCode) {
        this.counterCode = counterCode;
    }
}
