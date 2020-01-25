package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
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
@Table(name = "m_dependent_service_unit_wise_mapper", schema = "service")
public class DependentUnitServiceMapper extends AbstractBaseEntity {
    @OneToOne
    @JoinColumn(name = "parent_unit_service_mapper_id", nullable = false)
    private UnitServiceMapper parentUnitServiceMapper;

    @OneToOne
    @JoinColumn(name = "child_service_id", nullable = false)
    private ServiceMaster childService;

    @OneToOne
    @JoinColumn(name = "unit_id")
    private UnitMaster unitMaster;

    @Column(name = "percentage_on_parent_service_rate")
    private Double percentageOnParentServiceRate = 0.0;

    @Column(name = "flatServiceRate")
    private Double flatServiceRate = 0.0;

    @Column(name = "override_configured_rate", nullable = false)
    private Boolean overrideConfiguredRate = false;

    @Column (name="isTariffApplicable")
    private Boolean isTariffApplicable;
}
