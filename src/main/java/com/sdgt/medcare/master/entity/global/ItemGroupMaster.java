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
@Table(name = "m_item_group", schema = "inventory")
@SequenceGenerator(name = "item_group_seq", sequenceName = "inventory.item_group", allocationSize = 1)
@DynamicUpdate
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class ItemGroupMaster extends BaseMaster {

    @MastersFieldCustomAnnotation (displayName = "Organisation",nullable = false,sequence = 0)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @MastersFieldCustomAnnotation (displayName = "ItemType",nullable = false,sequence = 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id")
    private ItemTypeMaster itemTypeMaster;

    public ItemGroupMaster() {
    }

    public ItemGroupMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public ItemTypeMaster getItemTypeMaster() {
        return itemTypeMaster;
    }

    public void setItemTypeMaster(ItemTypeMaster itemTypeMaster) {
        this.itemTypeMaster = itemTypeMaster;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }
}
