package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.PatientCategoryMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.*;

@Table(name="m_tariff_patient_category_mapper",schema = "service")
@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = false)
public class TariffPatientCategoryMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tariff_id")
    @MastersFieldCustomAnnotation(displayName = "Tariff",sequence = 0,nullable = false)
    private TariffMaster tariffMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_category_id")
    @MastersFieldCustomAnnotation(displayName = "PatientCategory",sequence = 0,nullable = false)
    private PatientCategoryMaster patientCategoryMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    public TariffMaster getTariffMaster() {
        return tariffMaster;
    }

    public void setTariffMaster(TariffMaster tariffMaster) {
        this.tariffMaster = tariffMaster;
    }

    public PatientCategoryMaster getPatientCategoryMaster() {
        return patientCategoryMaster;
    }

    public void setPatientCategoryMaster(PatientCategoryMaster patientCategoryMaster) {
        this.patientCategoryMaster = patientCategoryMaster;
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
}
