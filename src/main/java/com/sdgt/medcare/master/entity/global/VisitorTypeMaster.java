package com.sdgt.medcare.master.entity.global;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_visitor_type",schema = "adt")
public class VisitorTypeMaster extends BaseMaster {

    public VisitorTypeMaster() {
    }
}
