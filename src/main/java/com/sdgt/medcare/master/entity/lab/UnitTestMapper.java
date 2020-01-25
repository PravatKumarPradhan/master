package com.sdgt.medcare.master.entity.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_unit_test_mapper", schema = "lab")
public class UnitTestMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @JsonIgnoreProperties("organizationMaster")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="test_master_id")
    private TestMaster testMaster;

    @JsonIgnoreProperties("organizationMaster")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @JsonIgnoreProperties("organizationMaster")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="default_transfer_code")
    private UnitMaster defaultTransferCodeUnitMaster;

    @Column(name = "is_inter_unit_transfer")
    private Boolean interUnitTransfer;

    @Column(name = "is_outsource")
    private Boolean outsource;

    @Column(name = "is_unique_sample")
    private Boolean uniqueSample;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="default_outsourse_lab_code")
    private OutsourceOrgMaster outsourceOrgMaster;

    public TestMaster getTestMaster() {
        return testMaster;
    }

    public void setTestMaster(TestMaster testMaster) {
        this.testMaster = testMaster;
    }

    public UnitMaster getDefaultTransferCodeUnitMaster() {
        return defaultTransferCodeUnitMaster;
    }

    public void setDefaultTransferCodeUnitMaster(UnitMaster defaultTransferCodeUnitMaster) {
        this.defaultTransferCodeUnitMaster = defaultTransferCodeUnitMaster;
    }

    public OutsourceOrgMaster getOutsourceOrgMaster() {
        return outsourceOrgMaster;
    }

    public void setOutsourceOrgMaster(OutsourceOrgMaster outsourceOrgMaster) {
        this.outsourceOrgMaster = outsourceOrgMaster;
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


    public Boolean getInterUnitTransfer() {
        return interUnitTransfer;
    }

    public void setInterUnitTransfer(Boolean interUnitTransfer) {
        this.interUnitTransfer = interUnitTransfer;
    }

    public Boolean getOutsource() {
        return outsource;
    }

    public void setOutsource(Boolean outsource) {
        this.outsource = outsource;
    }

    public Boolean getUniqueSample() {
        return uniqueSample;
    }

    public void setUniqueSample(Boolean uniqueSample) {
        this.uniqueSample = uniqueSample;
    }

}
