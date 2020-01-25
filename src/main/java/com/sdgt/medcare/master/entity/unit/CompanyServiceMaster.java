package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.CompanyMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import lombok.Data;

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
 *
 * @author Pravat Kumar Pradhan
 */
@Data
@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
@Table(name = "m_company_service", schema = "service")
public class CompanyServiceMaster extends BaseMaster {


    @MastersFieldCustomAnnotation (displayName = "Service",sequence = 2,nullable = false)
    @OneToOne
    @JoinColumn(name = "service_id")
    private ServiceMaster serviceMaster;

    @OneToOne
    @MastersFieldCustomAnnotation (displayName = "Unit",sequence = 1,nullable = false)
    @JoinColumn(name = "unit_id")
    private UnitMaster unitMaster;

    @OneToOne
    @MastersFieldCustomAnnotation (displayName = "Organisation",sequence = 0,nullable = false)
    @JoinColumn(name = "organizationMaster")
    private OrganizationMaster organizationMaster;

    @MastersFieldCustomAnnotation (displayName = "Company",sequence = 3,nullable = false)
    @OneToOne
    @JoinColumn(name = "company_id")
    private CompanyMaster companyMaster;
}
