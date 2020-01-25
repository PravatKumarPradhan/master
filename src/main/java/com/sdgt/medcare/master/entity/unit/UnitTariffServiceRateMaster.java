package com.sdgt.medcare.master.entity.unit;



import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
@Table(name="unit_tariff_service_rate",schema = "service")
public class UnitTariffServiceRateMaster extends AbstractBaseEntity {

    @Column(name="tariff_service_rate_code")
    private String tariffServiceRateCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_service_mapper_id")
    private UnitServiceMapper unitServiceMapper;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="visit_type_id")
    private VisitTypeMaster visitTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="tariff_id")
    private TariffMaster tariffMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="financial_id")
    private FinancialClassMaster financialClassMaster;

    @Column(name="rate",precision = 8,scale = 2)
    private Float rate;

    @Column(name="stat_percentage",precision = 8,scale = 2)
    private Float statPercentage;
    @Column(name="effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
}
