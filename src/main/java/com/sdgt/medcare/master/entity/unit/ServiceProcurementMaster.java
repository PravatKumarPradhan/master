package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.TaxMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_service_procurement", schema = "inventory")
public class ServiceProcurementMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    @Column(name = "editable")
    private Boolean editable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_unit_id")
    private ItemUnitMaster itemUnitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_id")
    private TaxMaster taxMaster;

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

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public ItemUnitMaster getItemUnitMaster() {
        return itemUnitMaster;
    }

    public void setItemUnitMaster(ItemUnitMaster itemUnitMaster) {
        this.itemUnitMaster = itemUnitMaster;
    }

    public TaxMaster getTaxMaster() {
        return taxMaster;
    }

    public void setTaxMaster(TaxMaster taxMaster) {
        this.taxMaster = taxMaster;
    }
}
