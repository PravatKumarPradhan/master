package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_unit_of_measurement", schema = "inventory")
@SequenceGenerator(name = "unit_of_measurement_seq", sequenceName = "inventory.unit_of_measurement_seq", allocationSize = 1)
@DynamicUpdate

public class UnitOfMeasurementMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrganizationMaster_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemMaster itemMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uom_type_id")
    private UomTypeMaster uomTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_unit_id")
    private ItemUnitMaster itemUnitMaster;

    @Column(nullable = false)
    private Integer conversion;

    @Column(name = "ip_uom")
    private Boolean ipUom;

    @Column(name = "op_uom")
    private Boolean opUom;

    @Column(name = "store_uom")
    private Boolean storeUom;

    public UnitOfMeasurementMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
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

    public Integer getConversion() {
        return conversion;
    }

    public void setConversion(Integer conversion) {
        this.conversion = conversion;
    }

    public Boolean getIpUom() {
        return ipUom;
    }

    public void setIpUom(Boolean ipUom) {
        this.ipUom = ipUom;
    }

    public Boolean getOpUom() {
        return opUom;
    }

    public void setOpUom(Boolean opUom) {
        this.opUom = opUom;
    }

    public Boolean getStoreUom() {
        return storeUom;
    }

    public void setStoreUom(Boolean storeUom) {
        this.storeUom = storeUom;
    }
}
