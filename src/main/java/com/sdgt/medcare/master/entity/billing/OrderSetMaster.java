package com.sdgt.medcare.master.entity.billing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Entity
@Table(name = "m_order_set", schema = "service")
public class OrderSetMaster extends BaseMaster {
    @Column(name = "is_global", nullable = false, columnDefinition = "boolean default false")
    private Boolean isGlobal;

    @Column(name = "is_multi_qty", nullable = false, columnDefinition = "boolean default false")
    private Boolean isMultiQty;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private UnitMaster unitMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private OrganizationMaster organizationMaster;

    @JsonIgnoreProperties("orderSetMaster")
    @OneToMany(mappedBy = "orderSetMaster", cascade = CascadeType.ALL)
    private List<OrderSetDetails> orderSetDetailsList;

    public Boolean getGlobal() {
        return isGlobal;
    }

    public void setGlobal(Boolean global) {
        isGlobal = global;
    }

    public Boolean getMultiQty() {
        return isMultiQty;
    }

    public void setMultiQty(Boolean multiQty) {
        isMultiQty = multiQty;
    }

    public UnitMaster getUnitMaster() {
        return unitMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
        this.unitMaster = unitMaster;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public List<OrderSetDetails> getOrderSetDetailsList() {
        return orderSetDetailsList;
    }

    public void setOrderSetDetailsList(List<OrderSetDetails> orderSetDetailsList) {
        this.orderSetDetailsList = orderSetDetailsList;
    }
}
