package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.ProcedureMaster;
import com.sdgt.medcare.master.entity.org.AdmissionTypeMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import javax.persistence.*;

@Table(name="m_deposit_matrix",schema = "adt")
@Entity
public class DepositMatrix extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="org_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bed_category_master_id")
    private BedCategory bedCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admission_type_id")
    private AdmissionTypeMaster admissionTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procedure_id")
    private ProcedureMaster procedureMaster;

    @Column(name = "against_resrvation")
    private Float againstResrvation;

    @Column(name = "against_admission")
    private Float againstAdmission;

    @Column(name = "against_procedure")
    private Float againstProcedure;

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

    public BedCategory getBedCategory() {
        return bedCategory;
    }

    public void setBedCategory(BedCategory bedCategory) {
        this.bedCategory = bedCategory;
    }

    public AdmissionTypeMaster getAdmissionTypeMaster() {
        return admissionTypeMaster;
    }

    public void setAdmissionTypeMaster(AdmissionTypeMaster admissionTypeMaster) {
        this.admissionTypeMaster = admissionTypeMaster;
    }

    public Float getAgainstResrvation() {
        return againstResrvation;
    }

    public void setAgainstResrvation(Float againstResrvation) {
        this.againstResrvation = againstResrvation;
    }

    public Float getAgainstAdmission() {
        return againstAdmission;
    }

    public void setAgainstAdmission(Float againstAdmission) {
        this.againstAdmission = againstAdmission;
    }

    public ProcedureMaster getProcedureMaster() {
        return procedureMaster;
    }

    public void setProcedureMaster(ProcedureMaster procedureMaster) {
        this.procedureMaster = procedureMaster;
    }

    public Float getAgainstProcedure() {
        return againstProcedure;
    }

    public void setAgainstProcedure(Float againstProcedure) {
        this.againstProcedure = againstProcedure;
    }
}
