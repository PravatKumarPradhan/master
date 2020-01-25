package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_linen_type", schema = "inventory")
@SequenceGenerator(name = "linen_type_seq", sequenceName = "inventory.linen_type_seq", allocationSize = 1)
@DynamicUpdate
public class LinenTypeMaster extends BaseMaster {

}
