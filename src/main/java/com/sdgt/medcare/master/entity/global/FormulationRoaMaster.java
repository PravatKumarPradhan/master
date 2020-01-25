package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
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
@Table(name = "m_formulation_roa", schema = "inventory")
@SequenceGenerator(name = "formulation_roa_seq", sequenceName = "inventory.formulation_roa_seq", allocationSize = 1)
@DynamicUpdate

public class FormulationRoaMaster extends BaseMaster {




    @MastersFieldCustomAnnotation(displayName = "Code",visibleToUser = false)
    @Column (name = "code",unique = true,length = 50)
    protected String code;

    @MastersFieldCustomAnnotation(displayName = "Description",visibleToUser = false)
    @Column(name = "description")
    protected String desc;


    @MastersFieldCustomAnnotation(displayName = "Organisation")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    @MastersFieldCustomAnnotation(displayName = "FormulationType")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formulation_type_id")
    private FormulationTypeMaster formulationTypeMaster;

    @MastersFieldCustomAnnotation(displayName = "Route of Administration")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roa_id")
    private RouteOfAdministrationMaster routeOfAdministrationMaster;

    public FormulationRoaMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public FormulationTypeMaster getFormulationTypeMaster() {
        return formulationTypeMaster;
    }

    public void setFormulationTypeMaster(FormulationTypeMaster formulationTypeMaster) {
        this.formulationTypeMaster = formulationTypeMaster;
    }

    public RouteOfAdministrationMaster getRouteOfAdministrationMaster() {
        return routeOfAdministrationMaster;
    }

    public void setRouteOfAdministrationMaster(RouteOfAdministrationMaster routeOfAdministrationMaster) {
        this.routeOfAdministrationMaster = routeOfAdministrationMaster;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
