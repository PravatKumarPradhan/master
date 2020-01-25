package com.sdgt.medcare.master.entity.billing.packageservice;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@Entity
@Table(name = "m_package_tariff_configuration", schema = "service")
public class PackageTariffConfigurationMaster extends AbstractBaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private OrganizationMaster organizationMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pkg_id")
    private PackageMaster packageMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff_id")
    private TariffMaster tariffMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_type_id")
    private VisitTypeMaster visitTypeMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finance_class_id")
    private FinancialClassMaster financialClassMaster;

    @Column(name = "pkg_rate")
    private Double packageRate;

    @Column(name = "pkg_discount_rate")
    private Double packageDiscountRate;

    @Column(name = "pkg_round_off_rate")
    private Double packageRoundOffRate;

    @Column(name = "pkg_revised_rate")
    private Double packageRevisedRate;

    @Column(name = "effective_start_date")
    private Date effectiveStartDate;

    @Column(name = "description")
    private String desc;
}
