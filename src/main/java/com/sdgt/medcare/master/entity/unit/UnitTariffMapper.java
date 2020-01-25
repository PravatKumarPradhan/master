package com.sdgt.medcare.master.entity.unit;


import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="unit_tariff_mapper",schema = "service")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class UnitTariffMapper extends AbstractBaseEntity {



    @MastersFieldCustomAnnotation(displayName = "Code",visibleToUser = false,sequence = 5)
    @Column (name = "code",length = 50)
    protected String code;

    @MastersFieldCustomAnnotation(displayName = "Description",sequence = 6,visibleToUser = false)
    @Column(name = "description")
    protected String desc;

    @MastersFieldCustomAnnotation (displayName = "Organisation",sequence = 0,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private OrganizationMaster organizationMaster;


    @MastersFieldCustomAnnotation (displayName = "Unit",sequence = 2,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="unit_master_id")
    private UnitMaster unitMaster;

    @MastersFieldCustomAnnotation (displayName = "Tariff",sequence = 3,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="tariff_master_id")
    private TariffMaster tariffMaster;

    public UnitTariffMapper() {
    }

    public UnitTariffMapper(OrganizationMaster organizationMaster, UnitMaster unitMaster, TariffMaster tariffMaster) {
        this.organizationMaster = organizationMaster;
        this.unitMaster = unitMaster;
        this.tariffMaster = tariffMaster;
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

    public TariffMaster getTariffMaster() {
        return tariffMaster;
    }

    public void setTariffMaster(TariffMaster tariffMaster) {
        this.tariffMaster = tariffMaster;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
