package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "m_markup_detail", schema = "inventory")
public class MarkupDetail extends BaseMaster {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "markup_master_id")
    private MarkupMaster markupMaster;

    @JoinColumn(name = "minimum_rate")
    private Double minimumRate;

    @JoinColumn(name = "maximum_rate")
    private Double maximumRate;

    @JoinColumn(name = "markup_percentage")
    private Double markupPercentage;

    @JoinColumn(name = "markup_rate")
    private Double markupRate;

    @JoinColumn(name = "fixed_selling_price")
    private Double fixedSellingPrice;

    @JoinColumn(name = "round_off_value")
    private Double roundOffValue;

    @JoinColumn(name = "any_rate")
    private Boolean anyRate;



    public MarkupDetail() {
    }

    public MarkupDetail(MarkupMaster markupMaster, Double minimumRate, Double maximumRate, Double markupPercentage, Double markupRate, Double fixedSellingPrice, Boolean anyRate) {
        this.markupMaster = markupMaster;
        this.minimumRate = minimumRate;
        this.maximumRate = maximumRate;
        this.markupPercentage = markupPercentage;
        this.markupRate = markupRate;
        this.fixedSellingPrice = fixedSellingPrice;
        this.anyRate = anyRate;
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

    public MarkupMaster getMarkupMaster() {
        return markupMaster;
    }

    public void setMarkupMaster(MarkupMaster markupMaster) {
        this.markupMaster = markupMaster;
    }

    public Double getMinimumRate() {
        return minimumRate;
    }

    public void setMinimumRate(Double minimumRate) {
        this.minimumRate = minimumRate;
    }

    public Double getMaximumRate() {
        return maximumRate;
    }

    public void setMaximumRate(Double maximumRate) {
        this.maximumRate = maximumRate;
    }

    public Double getMarkupPercentage() {
        return markupPercentage;
    }

    public void setMarkupPercentage(Double markupPercentage) {
        this.markupPercentage = markupPercentage;
    }

    public Double getMarkupRate() {
        return markupRate;
    }

    public void setMarkupRate(Double markupRate) {
        this.markupRate = markupRate;
    }

    public Double getFixedSellingPrice() {
        return fixedSellingPrice;
    }

    public void setFixedSellingPrice(Double fixedSellingPrice) {
        this.fixedSellingPrice = fixedSellingPrice;
    }

    public Double getRoundOffValue() {
        return roundOffValue;
    }

    public void setRoundOffValue(Double roundOffValue) {
        this.roundOffValue = roundOffValue;
    }

    public Boolean getAnyRate() {
        return anyRate;
    }

    public void setAnyRate(Boolean anyRate) {
        this.anyRate = anyRate;
    }



}
