package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="m_financial_class")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class FinancialClassMaster extends BaseMaster {

    @Column(name="financial_class_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private  String financialCLassCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;

    @Column(name="hierarchical_id")
    @MastersFieldCustomAnnotation(displayName = "Hierarchical Sequence",sequence = 4,nullable = false)
    private Integer hierarchicalId;

    public FinancialClassMaster() {
    }

    public FinancialClassMaster(String financialCLassCode, OrganizationMaster organizationMaster, Integer hierarchicalId) {
        this.financialCLassCode = financialCLassCode;
        this.organizationMaster = organizationMaster;
        this.hierarchicalId = hierarchicalId;
    }


}
