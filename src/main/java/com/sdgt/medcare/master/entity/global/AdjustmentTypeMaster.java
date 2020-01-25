package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_adjustment_type", schema = "inventory")
 public class AdjustmentTypeMaster extends BaseMaster {
    @Column(name="adj_type_master")
    private String adjTypeCode;

}
