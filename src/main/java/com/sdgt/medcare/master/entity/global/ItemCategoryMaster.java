package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_item_category", schema = "inventory")
@SequenceGenerator(name = "item_category_seq", sequenceName = "inventory.item_category_seq", allocationSize = 1)
@DynamicUpdate
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class ItemCategoryMaster extends BaseMaster {

    @MastersFieldCustomAnnotation (displayName = "Organisation",sequence = 0,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @MastersFieldCustomAnnotation (displayName = "ItemType",sequence = 1,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id")
    private ItemTypeMaster itemTypeMaster;

    @MastersFieldCustomAnnotation (displayName = "ItemGroup",sequence = 2,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_group_id")
    private ItemGroupMaster itemGroupMaster;

    public ItemCategoryMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public ItemTypeMaster getItemTypeMaster() {
        return itemTypeMaster;
    }

    public void setItemTypeMaster(ItemTypeMaster itemTypeMaster) {
        this.itemTypeMaster = itemTypeMaster;
    }

    public ItemGroupMaster getItemGroupMaster() {
        return itemGroupMaster;
    }

    public void setItemGroupMaster(ItemGroupMaster itemGroupMaster) {
        this.itemGroupMaster = itemGroupMaster;
    }

}
