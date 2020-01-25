package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Table(name="m_tariff",schema = "service")
@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class TariffMaster  extends BaseMaster {

    @Column(name="tariff_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String tariffCode;

    @Column(name="effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
    private Date effectiveDate;


    @Column(name="expire_date")
    @Temporal(TemporalType.TIMESTAMP)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 5,editableByUser = false)
    private Date expireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;

    public TariffMaster() {
    }

    public TariffMaster(String tariffCode, Date effectiveDate, Date expireDate, OrganizationMaster organizationMaster) {
        this.tariffCode = tariffCode;
        this.effectiveDate = effectiveDate;
        this.expireDate = expireDate;
        this.organizationMaster = organizationMaster;
    }

    public String getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }


}
