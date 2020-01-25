package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_panel_unit_test_mapper", schema = "lab")
public class PanelUnitTestMappper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="panel_master_id")
    private PanelMaster panelMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_test_mapper_id")
    private UnitTestMapper unitTestMapper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="header_master_id")
    private HeaderMaster headerMaster;

    @Column(name = "parameter_sequence")
    private Integer parameterSequence;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public PanelMaster getPanelMaster() {
        return panelMaster;
    }

    public void setPanelMaster(PanelMaster panelMaster) {
        this.panelMaster = panelMaster;
    }

    public HeaderMaster getHeaderMaster() {
        return headerMaster;
    }

    public void setHeaderMaster(HeaderMaster headerMaster) {
        this.headerMaster = headerMaster;
    }

    public Integer getParameterSequence() {
        return parameterSequence;
    }

    public void setParameterSequence(Integer parameterSequence) {
        this.parameterSequence = parameterSequence;
    }

    public UnitTestMapper getUnitTestMapper() {
        return unitTestMapper;
    }

    public void setUnitTestMapper(UnitTestMapper unitTestMapper) {
        this.unitTestMapper = unitTestMapper;
    }
}
