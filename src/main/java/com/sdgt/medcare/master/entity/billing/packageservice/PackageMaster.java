package com.sdgt.medcare.master.entity.billing.packageservice;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.global.GenderMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
@Table(name = "m_package", schema = "service")
public class PackageMaster extends AbstractBaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private OrganizationMaster organizationMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private UnitMaster unitMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_service_mapper_id")
    private UnitServiceMapper unitServiceMapper;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pkg_Type_id")
    private PackageTypeMaster packageTypeMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    private GenderMaster genderMaster;

    @Column(name = "min_age")
    private Short minimumAgeCriteria;

    @Column(name = "max_age")
    private Short maximumAgeCriteria;

    @Column(name = "effective_start_date")
    private Date effectiveStartDate;

    @Column(name = "effective_end_date")
    private Date effectiveEndDate;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String desc;
}
