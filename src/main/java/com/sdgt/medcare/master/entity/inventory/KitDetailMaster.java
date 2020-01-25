package com.sdgt.medcare.master.entity.inventory;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.ItemUnitMaster;
import com.sdgt.medcare.master.entity.unit.UomTypeMaster;

import javax.persistence.*;

@Entity
@Table(name = "m_kit_detail", schema = "inventory")
public class KitDetailMaster extends BaseMaster {



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kit_id")
    private KitMaster kitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemMaster itemMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uom_type_id")
    private UomTypeMaster uomTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uom_unit_id")
    private ItemUnitMaster itemUnitMaster;

    @Column(name = "quantity")
    private Integer quantity;



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

    public KitMaster getKitMaster() {
        return kitMaster;
    }

    public void setKitMaster(KitMaster kitMaster) {
        this.kitMaster = kitMaster;
    }

    public ItemMaster getItemMaster() {
        return itemMaster;
    }

    public void setItemMaster(ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
    }

    public UomTypeMaster getUomTypeMaster() {
        return uomTypeMaster;
    }

    public void setUomTypeMaster(UomTypeMaster uomTypeMaster) {
        this.uomTypeMaster = uomTypeMaster;
    }

    public ItemUnitMaster getItemUnitMaster() {
        return itemUnitMaster;
    }

    public void setItemUnitMaster(ItemUnitMaster itemUnitMaster) {
        this.itemUnitMaster = itemUnitMaster;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
