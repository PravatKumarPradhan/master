package com.sdgt.medcare.master.entity.billing.contract;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
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
@Table(name = "m_contract_visit_config", schema = "service")
public class ContractVisitConfiguration extends AbstractBaseEntity {
    @OneToOne
    @JoinColumn(name = "visit_type_id", nullable = false)
    private VisitTypeMaster visitTypeMaster;

    @Column(name = "no_of_visit")
    private Long noOfVisit;

    @Column(name = "visit_limit")
    private Double visitLimit;

    @Column(name = "pre_approved_visit_limit")
    private Double preApprovedVisitLimit;
}
