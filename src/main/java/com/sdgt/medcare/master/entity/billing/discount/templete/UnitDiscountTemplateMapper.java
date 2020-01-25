package com.sdgt.medcare.master.entity.billing.discount.templete;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@Entity
@Table(name = "m_unit_discount_template_mapper", schema = "service")
public class UnitDiscountTemplateMapper extends AbstractBaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "discount_template_id")
    private DiscountTemplateMaster discountTemplate;
}
