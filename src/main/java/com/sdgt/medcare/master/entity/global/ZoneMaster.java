package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_zone")
public class ZoneMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private StateMaster stateMaster;
}
