package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_item_type", schema = "inventory")
@SequenceGenerator(name = "item_type_seq", sequenceName = "inventory.item_type_seq", allocationSize = 1)
@DynamicUpdate
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class ItemTypeMaster extends BaseMaster {

    public ItemTypeMaster() {
    }

}
