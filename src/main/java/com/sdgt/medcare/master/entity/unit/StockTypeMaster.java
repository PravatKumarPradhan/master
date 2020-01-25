package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_stock_type", schema = "inventory")
@SequenceGenerator(name = "stock_type_seq", sequenceName = "inventory.stock_type_seq", allocationSize = 1)
@DynamicUpdate
public class StockTypeMaster extends BaseMaster {
}
