package com.sdgt.medcare.master.entity.billing.packageservice;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.org.DiscountTypeMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "m_package_item_service_detail", schema = "service")
public class PackageItemServiceDetail extends AbstractBaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private OrganizationMaster organizationMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pkg_tariff_configuration_id")
    private PackageTariffConfigurationMaster packageTariffConfigurationMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_service_mapper_id")
    private UnitServiceMapper unitServiceMapper;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemMaster itemMaster;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "service_qty")
    private Long serviceQty;

    @Column(name = "pkg_unit_rate")
    private Double packageUnitRate;

    @Column(name = "pkg_unit_discount_rate")
    private Double packageUnitDiscountRate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_type_id")
    private DiscountTypeMaster discountTypeMaster;

    @Column(name = "description")
    private String desc;
}
