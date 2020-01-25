package com.sdgt.medcare.master.entity.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table (name = "m_parameter", schema = "lab")
public class ParameterMaster extends BaseMaster {

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "Org_ID")
   private OrganizationMaster organizationMaster;

   @JsonIgnoreProperties ("organizationMaster")
   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "lab_unit_master_id")
   private LabUnitMaster labUnitMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "ref_range_master_type_id")
   private ReferenceRangeMasterTypeMaster referenceRangeMasterTypeMaster;

   @Column (name = "alias_name", length = 50)
   private String aliasName;

   @Column (name = "other_language_name", length = 50)
   private String otherLanguageName;

   @Column (name = "is_formula")
   private Boolean formula;

   @Column (name = "formula_definition")
   private String formulaDefinition;

   @Column (name = "formula_params")
   private String formulaParameters;

   @Column (name = "is_multiparameter")
   private Boolean multiparameter = false;

   @Column (name = "delta_type")
   private String deltaType;

   @Column (name = "delta_no_of_days")
   private Integer deltaNoOfDays;

   @Column (name = "delta_percentage")
   private Float deltaPercentage;

   @Column (name = "delta_values", length = 50)
   private String deltaValues;

   @Column (name = "param_precision")
   private Integer paramPrecision;

   @Column (name = "print_name", length = 50)
   private String printName;

   @Column (name = "defaultcomment", length = 50)
   private String defaultComment;

   @Column (name = "is_comment")
   private Boolean comment;

   @Column (name = "is_constant")
   private Boolean constant = false;

   @Column (name = "is_optional")
   private Boolean optional = false;

   @Column (name = "method", length = 50)
   private String method;

   @JsonIgnoreProperties ({"parameterMaster", "organizationMaster"})
   @OneToMany (fetch = FetchType.LAZY, mappedBy = "parameterMaster", cascade = {CascadeType.ALL})
   Set<ParameterReferenceRangeMaster> parameterReferenceRangeMasters;

   @JsonIgnoreProperties ({"parameterMaster", "organizationMaster"})
   @OneToMany (fetch = FetchType.LAZY, mappedBy = "parameterMaster", cascade = {CascadeType.ALL})
   Set<ParameterTextualRangeMaster> parameterTextualRangeMaster;

   @JsonIgnoreProperties ({"parameterMaster", "organizationMaster"})
   @OneToMany (fetch = FetchType.LAZY, mappedBy = "parameterMaster", cascade = CascadeType.ALL)
   Set<ParameterHelpValuesMaster> parameterHelpValuesMaster;

   public OrganizationMaster getOrganizationMaster() {
      return organizationMaster;
   }

   public void setOrganizationMaster(OrganizationMaster organizationMaster) {
      this.organizationMaster = organizationMaster;
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

   public String getFormulaParameters() {
      return formulaParameters;
   }

   public void setFormulaParameters(String formulaParameters) {
      this.formulaParameters = formulaParameters;
   }

   public Boolean getMultiparameter() {
      return multiparameter;
   }

   public void setMultiparameter(Boolean multiparameter) {
      this.multiparameter = multiparameter;
   }

   public String getDeltaType() {
      return deltaType;
   }

   public void setDeltaType(String deltaType) {
      this.deltaType = deltaType;
   }

   public Integer getDeltaNoOfDays() {
      return deltaNoOfDays;
   }

   public void setDeltaNoOfDays(Integer deltaNoOfDays) {
      this.deltaNoOfDays = deltaNoOfDays;
   }

   public Float getDeltaPercentage() {
      return deltaPercentage;
   }

   public void setDeltaPercentage(Float deltaPercentage) {
      this.deltaPercentage = deltaPercentage;
   }

   public String getDeltaValues() {
      return deltaValues;
   }

   public void setDeltaValues(String deltaValues) {
      this.deltaValues = deltaValues;
   }

   public Integer getParamPrecision() {
      return paramPrecision;
   }

   public void setParamPrecision(Integer paramPrecision) {
      this.paramPrecision = paramPrecision;
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

   public ReferenceRangeMasterTypeMaster getReferenceRangeMasterTypeMaster() {
      return referenceRangeMasterTypeMaster;
   }

   public void setReferenceRangeMasterTypeMaster(ReferenceRangeMasterTypeMaster referenceRangeMasterTypeMaster) {
      this.referenceRangeMasterTypeMaster = referenceRangeMasterTypeMaster;
   }

   public LabUnitMaster getLabUnitMaster() {
      return labUnitMaster;
   }

   public void setLabUnitMaster(LabUnitMaster labUnitMaster) {
      this.labUnitMaster = labUnitMaster;
   }

   public Set<ParameterReferenceRangeMaster> getParameterReferenceRangeMasters() {
      return parameterReferenceRangeMasters;
   }

   public void setParameterReferenceRangeMasters(Set<ParameterReferenceRangeMaster> parameterReferenceRangeMasters) {
      this.parameterReferenceRangeMasters = parameterReferenceRangeMasters;
   }

   public Set<ParameterTextualRangeMaster> getParameterTextualRangeMaster() {
      return parameterTextualRangeMaster;
   }

   public void setParameterTextualRangeMaster(Set<ParameterTextualRangeMaster> parameterTextualRangeMaster) {
      this.parameterTextualRangeMaster = parameterTextualRangeMaster;
   }

   public Set<ParameterHelpValuesMaster> getParameterHelpValuesMaster() {
      return parameterHelpValuesMaster;
   }

   public void setParameterHelpValuesMaster(Set<ParameterHelpValuesMaster> parameterHelpValuesMaster) {
      this.parameterHelpValuesMaster = parameterHelpValuesMaster;
   }

   public String getOtherLanguageName() {
      return otherLanguageName;
   }

   public void setOtherLanguageName(String otherLanguageName) {
      this.otherLanguageName = otherLanguageName;
   }
}
