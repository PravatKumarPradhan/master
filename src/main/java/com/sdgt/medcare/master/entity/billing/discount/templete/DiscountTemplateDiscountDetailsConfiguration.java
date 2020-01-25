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
@Table(name = "m_discount_template_discount_config", schema = "service")
public class DiscountTemplateDiscountDetailsConfiguration extends AbstractBaseEntity {
    @OneToOne
    @JoinColumn(name = "charge_category_id")
    private ChargeCategoryMaster chargeCategory;

    /**
     * This holds the code of the entity with respect to the referenced {@link ChargeCategoryMaster}
     */
    @Column(name = "charge_id")
    private String chargeId;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "max_discount_limit_amount")
    private Double maxDiscountLimitAmount;
}
