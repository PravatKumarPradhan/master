package com.sdgt.medcare.master.entity.billing.contract;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.org.GradeMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@Entity
@Table(name = "m_contract_config", schema = "service")
public class ContractConfiguration extends AbstractBaseEntity {
    @OneToOne
    @JoinColumn(name = "grade_id")
    private GradeMaster grade;

    @OneToOne
    @JoinColumn(name = "fall_back_tariff_id")
    private TariffMaster fallBackTariff;

    @Column(name = "co_share_percentage")
    private Double coSharePercentage;

    @Column(name = "exclude_discount")
    private Boolean excludeDiscount;

    @Column(name = "include_all_visit_type")
    private Boolean includeAllVisitType;

    @Column(name = "total_no_of_visit")
    private Long totalNoOfVisit;

    @Column(name = "total_visit_limit")
    private Double totalVisitLimit;

    @Column(name = "total_pre_approved_visit_limit")
    private Double preApprovedVisitLimit;

    @Column(name = "description")
    private String desc;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "configuration_id")
    private Collection<ContractVisitConfiguration> visitConfigurationCollection;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "configuration_id")
    private Collection<ContractExclusionConfiguration> exclusionConfigurationCollection;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "configuration_id")
    private Collection<ContractDiscountDetailsConfiguration> discountDetailsCollection;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "master_id")
    private ContractMaster contract;
}
