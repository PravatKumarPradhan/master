package com.sdgt.medcare.master.entity.otims;

import com.sdgt.medcare.master.entity.BaseMaster;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_business_status", schema = "otims")

@Data
public class BusinessStatusMaster extends BaseMaster {

    @Column(name="isRequest")
    private boolean isRequest;
}
