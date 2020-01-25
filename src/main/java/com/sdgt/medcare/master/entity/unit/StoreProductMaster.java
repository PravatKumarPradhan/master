package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.ItemCategoryMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_store_product", schema = "inventory")
@SequenceGenerator(name = "store_product_seq", sequenceName = "inventory.store_product_seq", allocationSize = 1)
@DynamicUpdate
public class StoreProductMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrganizationMaster_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreMaster storeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_category_id")
    private ItemCategoryMaster itemCategoryMaster;

    public StoreProductMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public StoreMaster getStoreMaster() {
        return storeMaster;
    }

    public void setStoreMaster(StoreMaster storeMaster) {
        this.storeMaster = storeMaster;
    }

    public ItemCategoryMaster getItemCategoryMaster() {
        return itemCategoryMaster;
    }

    public void setItemCategoryMaster(ItemCategoryMaster itemCategoryMaster) {
        this.itemCategoryMaster = itemCategoryMaster;
    }
}
