package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_indent_type", schema = "inventory")
@SequenceGenerator(name = "indent_type_seq", sequenceName = "public.indent_type_seq", allocationSize = 1)
@DynamicUpdate
public class IndentTypeMaster extends BaseMaster {

    public IndentTypeMaster() {
    }
}
