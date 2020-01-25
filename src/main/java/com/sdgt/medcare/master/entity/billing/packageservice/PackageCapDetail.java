package com.sdgt.medcare.master.entity.billing.packageservice;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.global.ItemCategoryMaster;
import com.sdgt.medcare.master.entity.global.ItemGroupMaster;
import com.sdgt.medcare.master.entity.org.GroupMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.SubGroupMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "m_package_cap_detail", schema = "service")
public class PackageCapDetail extends AbstractBaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private OrganizationMaster organizationMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pkg_tariff_configuration_id")
    private PackageTariffConfigurationMaster packageTariffConfigurationMaster;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "packageExceptionDetails_id")
    private List<PackageExceptionDetails> packageExceptionDetailsList;

    @Column(name = "group_consumable")
    private String groupConsumable;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupMaster groupMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_group_id")
    private SubGroupMaster subGroupMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_group_id")
    private ItemGroupMaster itemGroupMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_category_id")
    private ItemCategoryMaster itemCategoryMaster;

    @Column(name = "cap_rate")
    private Double capRate = 0.0;

    @Column(name = "description")
    private String desc;
}
