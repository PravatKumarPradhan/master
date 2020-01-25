package com.sdgt.medcare.master.entity.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "m_panel", schema = "lab")
public class PanelMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_master_id")
    private ServiceMaster serviceMaster;

    @Column(name = "panel_alias", length = 50)
    private String panelAlias;

    @JsonIgnoreProperties({"panelMaster","organizationMaster"})
    @OneToMany(fetch = FetchType.LAZY,mappedBy="panelMaster")
    Set<PanelUnitTestMappper> panelUnitTestMapppers;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public ServiceMaster getServiceMaster() {
        return serviceMaster;
    }

    public void setServiceMaster(ServiceMaster serviceMaster) {
        this.serviceMaster = serviceMaster;
    }

    public String getPanelAlias() {
        return panelAlias;
    }

    public void setPanelAlias(String panelAlias) {
        this.panelAlias = panelAlias;
    }

    public Set<PanelUnitTestMappper> getPanelUnitTestMapppers() {
        return panelUnitTestMapppers;
    }

    public void setPanelUnitTestMapppers(Set<PanelUnitTestMappper> panelUnitTestMapppers) {
        this.panelUnitTestMapppers = panelUnitTestMapppers;
    }
}
