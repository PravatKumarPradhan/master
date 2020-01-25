package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Pravat Kumar Pradhan
 * @author Karthik Chandra
 */
@Data
@Entity
@Table(name="m_service_category", schema = "service")
public class ServiceCategoryMaster  extends BaseMaster {

    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    @Column(name="service_category_code")
    private String serviceCategoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0)
    private OrganizationMaster organizationMaster;
}
