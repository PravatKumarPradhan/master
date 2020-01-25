package com.sdgt.medcare.master.entity.global;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_preference_card_details", schema = "otims")
public class PreferenceCardDetailsMaster extends BaseMaster {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private UnitMaster unitMaster;

	@Column(name = "cart_type")
	private String cartType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_service_id")
    private ItemMaster itemMaster;

    @Column(name = "required_qty")
    private String requiredQty;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JsonBackReference
   // @JoinColumn(name="preference_card_id")
    private PreferenceCardMaster preferenceCardMaster;

    public PreferenceCardMaster getPreferenceCardMaster() {
        return preferenceCardMaster;
    }

    public void setPreferenceCardMaster(PreferenceCardMaster preferenceCardMaster) {
        this.preferenceCardMaster = preferenceCardMaster;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(final OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setUnitMaster(final UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(final String cartType) {
        this.cartType = cartType;
    }

    public ItemMaster getItemMaster() {
        return itemMaster;
    }

    public void setItemMaster(final ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
    }

    public String getRequiredQty() {
        return requiredQty;
    }

    public void setRequiredQty(String requiredQty) {
        this.requiredQty = requiredQty;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }
}
