package com.sdgt.medcare.master.entity.billing.discount.templete;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.billing.ChargeCategoryMaster;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "m_discount_template_exclusion_config", schema = "service")
public class DiscountTemplateExclusionConfiguration extends AbstractBaseEntity {
    @OneToOne
    @JoinColumn(name = "exclusion_charge_category_id", nullable = false)
    private ChargeCategoryMaster chargeCategoryMaster;

    @Column(name = "exclusion_charge_Id", nullable = false)
    private String exclusionChargeId;
}
