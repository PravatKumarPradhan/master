package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.*;
import com.sdgt.medcare.master.entity.unit.StoreMaster;
import com.sdgt.medcare.master.entity.unit.WardMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name = "m_store_configuration", schema = "inventory")
@SequenceGenerator(name = "store_configuration_seq", sequenceName = "inventory.store_configuration_seq", allocationSize = 1)
@DynamicUpdate
public class StoreConfigurationMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_Master_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private CabinMaster clinicMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private WardMaster wardMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_type_id")
    private VisitTypeMaster visitTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_pharmacy_store_id")
    private StoreMaster defaultPharmacyStoreMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "after_working_pharmacy_store_id")
    private StoreMaster workingPharmacyStoreMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_narcotics_store_id")
    private StoreMaster defaultNarcoticStoreMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "after_working_narcotics_store_id")
    private StoreMaster workingNarcoticStoreMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_crash_cart_store_id")
    private StoreMaster defaultCrashCartStoreMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "after_working_crash_cart_store_id")
    private StoreMaster workingCrashCartStoreMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holiday_store_id")
    private StoreMaster holidayStoreMaster;

    @Column(name = "working_day")
    private String workingDay;

    @Column(name = "working_from")
    private Time workingFrom;

    @Column(name = "working_to")
    private Time workingTo;

    public StoreConfigurationMaster() {
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

    public CabinMaster getClinicMaster() {
        return clinicMaster;
    }

    public void setClinicMaster(CabinMaster clinicMaster) {
        this.clinicMaster = clinicMaster;
    }

    public WardMaster getWardMaster() {
        return wardMaster;
    }

    public void setWardMaster(WardMaster wardMaster) {
        this.wardMaster = wardMaster;
    }

    public VisitTypeMaster getVisitTypeMaster() {
        return visitTypeMaster;
    }

    public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
        this.visitTypeMaster = visitTypeMaster;
    }

    public StoreMaster getDefaultPharmacyStoreMaster() {
        return defaultPharmacyStoreMaster;
    }

    public void setDefaultPharmacyStoreMaster(StoreMaster defaultPharmacyStoreMaster) {
        this.defaultPharmacyStoreMaster = defaultPharmacyStoreMaster;
    }

    public StoreMaster getWorkingPharmacyStoreMaster() {
        return workingPharmacyStoreMaster;
    }

    public void setWorkingPharmacyStoreMaster(StoreMaster workingPharmacyStoreMaster) {
        this.workingPharmacyStoreMaster = workingPharmacyStoreMaster;
    }

    public StoreMaster getDefaultNarcoticStoreMaster() {
        return defaultNarcoticStoreMaster;
    }

    public void setDefaultNarcoticStoreMaster(StoreMaster defaultNarcoticStoreMaster) {
        this.defaultNarcoticStoreMaster = defaultNarcoticStoreMaster;
    }

    public StoreMaster getWorkingNarcoticStoreMaster() {
        return workingNarcoticStoreMaster;
    }

    public void setWorkingNarcoticStoreMaster(StoreMaster workingNarcoticStoreMaster) {
        this.workingNarcoticStoreMaster = workingNarcoticStoreMaster;
    }

    public StoreMaster getDefaultCrashCartStoreMaster() {
        return defaultCrashCartStoreMaster;
    }

    public void setDefaultCrashCartStoreMaster(StoreMaster defaultCrashCartStoreMaster) {
        this.defaultCrashCartStoreMaster = defaultCrashCartStoreMaster;
    }

    public StoreMaster getWorkingCrashCartStoreMaster() {
        return workingCrashCartStoreMaster;
    }

    public void setWorkingCrashCartStoreMaster(StoreMaster workingCrashCartStoreMaster) {
        this.workingCrashCartStoreMaster = workingCrashCartStoreMaster;
    }

    public StoreMaster getHolidayStoreMaster() {
        return holidayStoreMaster;
    }

    public void setHolidayStoreMaster(StoreMaster holidayStoreMaster) {
        this.holidayStoreMaster = holidayStoreMaster;
    }

    public String getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    public Time getWorkingFrom() {
        return workingFrom;
    }

    public void setWorkingFrom(Time workingFrom) {
        this.workingFrom = workingFrom;
    }

    public Time getWorkingTo() {
        return workingTo;
    }

    public void setWorkingTo(Time workingTo) {
        this.workingTo = workingTo;
    }
}
