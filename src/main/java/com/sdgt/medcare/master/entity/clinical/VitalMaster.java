package com.sdgt.medcare.master.entity.clinical;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.lab.ReferenceRangeMasterTypeMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Table(name="m_vital",schema = "clinical")
@Entity
@JsonDeserialize
public class VitalMaster extends BaseMaster  {

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public ReferenceRangeMasterTypeMaster getReferenceRangeMasterTypeMaster() {
        return referenceRangeMasterTypeMaster;
    }

    public void setReferenceRangeMasterTypeMaster(ReferenceRangeMasterTypeMaster referenceRangeMasterTypeMaster) {
        this.referenceRangeMasterTypeMaster = referenceRangeMasterTypeMaster;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Boolean getFormula() {
        return formula;
    }

    public void setFormula(Boolean formula) {
        this.formula = formula;
    }

    public String getFormulaDefinition() {
        return formulaDefinition;
    }

    public void setFormulaDefinition(String formulaDefinition) {
        this.formulaDefinition = formulaDefinition;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public String getDefaultComment() {
        return defaultComment;
    }

    public void setDefaultComment(String defaultComment) {
        this.defaultComment = defaultComment;
    }

    public Boolean getComment() {
        return comment;
    }

    public void setComment(Boolean comment) {
        this.comment = comment;
    }

    public Boolean getConstant() {
        return constant;
    }

    public void setConstant(Boolean constant) {
        this.constant = constant;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public VitalMaster getParentVitalMaster() {
        return parentVitalMaster;
    }

    public void setParentVitalMaster(VitalMaster parentVitalMaster) {
        this.parentVitalMaster = parentVitalMaster;
    }

    public Set<VitalMaster> getChildVitalMasters() {
        return childVitalMasters;
    }

    public void setChildVitalMasters(Set<VitalMaster> childVitalMasters) {
        this.childVitalMasters = childVitalMasters;
    }

    public Set<VitalReferanceRangeMaster> getVitalReferanceRangeMasters() {
        return vitalReferanceRangeMasters;
    }

    public void setVitalReferanceRangeMasters(Set<VitalReferanceRangeMaster> vitalReferanceRangeMasters) {
        this.vitalReferanceRangeMasters = vitalReferanceRangeMasters;
    }

    public VitalUnitMaster getDefaultUnit() {
        return defaultUnit;
    }

    public void setDefaultUnit(VitalUnitMaster defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    public Set<QulifierVitalMapper> getQulifierVitalMappers() {
        return qulifierVitalMappers;
    }

    public void setQulifierVitalMappers(Set<QulifierVitalMapper> qulifierVitalMappers) {
        this.qulifierVitalMappers = qulifierVitalMappers;
    }

    @JsonIgnoreProperties("vitalMaster")
    @OneToMany(fetch = FetchType.LAZY, mappedBy="vitalMaster")
    Set<QulifierVitalMapper> qulifierVitalMappers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="default_unit")
    private VitalUnitMaster defaultUnit;

    @JsonIgnoreProperties("childVitalMasters")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vital_master_id")
    private VitalMaster parentVitalMaster;

    @JsonIgnoreProperties("parentVitalMaster")
    @OneToMany(fetch = FetchType.LAZY,mappedBy="parentVitalMaster")
    Set<VitalMaster> childVitalMasters;


    @OneToMany(fetch = FetchType.LAZY,mappedBy="vitalMaster")
    Set<VitalReferanceRangeMaster> vitalReferanceRangeMasters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ref_range_master_type_id")
    private ReferenceRangeMasterTypeMaster referenceRangeMasterTypeMaster;

    @Column(name = "alias_name", length = 50)
    private String aliasName;

    @Column(name = "is_formula")
    private Boolean formula;

    @Column(name = "formula_definition")
    private String formulaDefinition;

    @Column(name = "print_name", length = 50)
    private String printName;

    @Column(name = "defaultcomment", length = 50)
    private String defaultComment;

    @Column(name = "is_comment")
    private Boolean comment;

    @Column(name = "is_constant")
    private Boolean constant;

    @Column(name = "is_optional")
    private Boolean optional;

    @Column(name = "method", length = 50)
    private String method;

    @Column(name = "speciality", length = 50)
    private String speciality;

}
