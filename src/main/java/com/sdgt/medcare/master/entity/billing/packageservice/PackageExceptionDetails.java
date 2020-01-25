package com.sdgt.medcare.master.entity.billing.packageservice;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.global.ItemCategoryMaster;
import com.sdgt.medcare.master.entity.global.ItemGroupMaster;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.org.GroupMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.SubGroupMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "m_package_exception_details", schema = "service")
public class PackageExceptionDetails extends AbstractBaseEntity {
    @OneToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private OrganizationMaster organizationMaster;

    @OneToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private UnitMaster unitMaster;

    @Column(name = "exception_type")
    private String exceptionType;

    @OneToOne
    @JoinColumn(name = "unit_service_mapper_id", referencedColumnName = "id")
    private UnitServiceMapper unitServiceMapper;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ItemMaster itemMaster;

    @ManyToOne
    @JoinColumn(name = "package_cap_details_master_id")
    private PackageCapDetail packageCapDetail;

    @OneToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupMaster groupMaster;

    @OneToOne
    @JoinColumn(name = "sub_group_id", referencedColumnName = "id")
    private SubGroupMaster subGroupMaster;

    @OneToOne
    @JoinColumn(name = "item_group_id", referencedColumnName = "id")
    private ItemGroupMaster itemGroupMaster;

    @OneToOne
    @JoinColumn(name = "item_sub_group_id", referencedColumnName = "id")
    private ItemCategoryMaster itemCategoryMaster;

    @Column(name = "description")
    private String desc;
}
