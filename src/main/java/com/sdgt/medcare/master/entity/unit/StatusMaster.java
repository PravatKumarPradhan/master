package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_status", schema = "inventory")

@DynamicUpdate
public class StatusMaster extends BaseMaster {
}
