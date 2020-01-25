package com.sdgt.medcare.master.entity.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.SubDepartmentMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name="m_test",schema = "lab")
public class TestMaster extends BaseMaster {

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="Org_ID")
   private OrganizationMaster organizationMaster;

   @JsonIgnoreProperties("organizationMaster")
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="subdepartment_id")
   private SubDepartmentMaster  subDepartmentMaster;

   @JsonIgnoreProperties("organizationMaster")
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="sample_type_master_id")
   private SampleTypeMaster sampleTypeMaster;

   @JsonIgnoreProperties("organizationMaster")
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="container_master_id")
   private ContainerMaster containerMaster;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="report_type_master_id")
   private ReportTypeMaster reportTypeMaster;



   @JsonIgnoreProperties("organizationMaster")
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="service_master_id")
   private ServiceMaster serviceMaster;

   @JsonIgnoreProperties({"testMaster","organizationMaster"})

   @OneToMany(fetch = FetchType.LAZY,mappedBy="testMaster",cascade = CascadeType.ALL)

   List<TestParameterMapper> testParameterMappers;


   @JsonIgnoreProperties({"testMaster","organizationMaster"})
   @OneToMany(fetch = FetchType.LAZY,mappedBy="testMaster",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
   List<TemplateTestMapper> templateTestMappers;


   @Column(name = "alias_name", length = 50)
   private String aliasName;

   @Column (name = "other_language_name", length = 50)
   private String otherLanguageName;

   @Column(name = "no_of_sample")
   private Float noOfSample;

   @Column(name = "sample_volume")
   private Float sampleVolume;

   @Column(name = "codification_no", length = 50)
   private String codificationNo;

   @Column(name = "foot_note", length = 500)
   private String footNote;

   @Column(name = "urgent_tat_type_duration_id")
   private Float urgentTatTypeDurationId;

   @Column(name = "urgent_tat_duration")
   private Float urgentTatDuration;

   @Column(name = "normal_tat_type_duration_id")
   private Float normalTatTypeDurationId;

   @Column(name = "normal_tat_duration")
   private Float normalTatDuration;

   @Column(name = "provisional_release")
   private Boolean provisionalRelease;

   @Column(name = "approval", length = 50)
   private String approval;

   @Column(name = "save", length = 50)
   private String save;

   @Column(name = "verify", length = 50)
   private String verify;

   @Column(name = "is_clinical_interpritation")
   private Boolean clinicalInterpitation;

   @Column(name="result_entry")
   private Boolean resultEntry;

   public SubDepartmentMaster getSubDepartmentMaster() {
      return subDepartmentMaster;
   }

   public void setSubDepartmentMaster(SubDepartmentMaster subDepartmentMaster) {
      this.subDepartmentMaster = subDepartmentMaster;
   }

   public SampleTypeMaster getSampleTypeMaster() {
      return sampleTypeMaster;
   }

   public void setSampleTypeMaster(SampleTypeMaster sampleTypeMaster) {
      this.sampleTypeMaster = sampleTypeMaster;
   }

   public ContainerMaster getContainerMaster() {
      return containerMaster;
   }

   public void setContainerMaster(ContainerMaster containerMaster) {
      this.containerMaster = containerMaster;
   }





   public ServiceMaster getServiceMaster() {
      return serviceMaster;
   }

   public void setServiceMaster(ServiceMaster serviceMaster) {
      this.serviceMaster = serviceMaster;
   }

   public String getAliasName() {
      return aliasName;
   }

   public void setAliasName(String aliasName) {
      this.aliasName = aliasName;
   }

   public Float getNoOfSample() {
      return noOfSample;
   }

   public void setNoOfSample(Float noOfSample) {
      this.noOfSample = noOfSample;
   }

   public Float getSampleVolume() {
      return sampleVolume;
   }

   public void setSampleVolume(Float sampleVolume) {
      this.sampleVolume = sampleVolume;
   }

   public String getCodificationNo() {
      return codificationNo;
   }

   public void setCodificationNo(String codificationNo) {
      this.codificationNo = codificationNo;
   }

   public String getFootNote() {
      return footNote;
   }

   public void setFootNote(String footNote) {
      this.footNote = footNote;
   }

   public Float getUrgentTatTypeDurationId() {
      return urgentTatTypeDurationId;
   }

   public void setUrgentTatTypeDurationId(Float urgentTatTypeDurationId) {
      this.urgentTatTypeDurationId = urgentTatTypeDurationId;
   }

   public Float getUrgentTatDuration() {
      return urgentTatDuration;
   }

   public void setUrgentTatDuration(Float urgentTatDuration) {
      this.urgentTatDuration = urgentTatDuration;
   }

   public Float getNormalTatTypeDurationId() {
      return normalTatTypeDurationId;
   }

   public void setNormalTatTypeDurationId(Float normalTatTypeDurationId) {
      this.normalTatTypeDurationId = normalTatTypeDurationId;
   }

   public Float getNormalTatDuration() {
      return normalTatDuration;
   }

   public void setNormalTatDuration(Float normalTatDuration) {
      this.normalTatDuration = normalTatDuration;
   }

   public Boolean getProvisionalRelease() {
      return provisionalRelease;
   }

   public void setProvisionalRelease(Boolean provisionalRelease) {
      this.provisionalRelease = provisionalRelease;
   }

   public String getApproval() {
      return approval;
   }

   public void setApproval(String approval) {
      this.approval = approval;
   }

   public String getSave() {
      return save;
   }

   public void setSave(String save) {
      this.save = save;
   }

   public String getVerify() {
      return verify;
   }

   public void setVerify(String verify) {
      this.verify = verify;
   }

   public Boolean getClinicalInterpitation() {
      return clinicalInterpitation;
   }

   public void setClinicalInterpitation(Boolean clinicalInterpitation) {
      this.clinicalInterpitation = clinicalInterpitation;
   }

   public OrganizationMaster getOrganizationMaster() {
      return organizationMaster;
   }

   public void setOrganizationMaster(OrganizationMaster organizationMaster) {
      this.organizationMaster = organizationMaster;
   }

   public List<TestParameterMapper> getTestParameterMappers() {
      return testParameterMappers;
   }

   public void setTestParameterMappers(List<TestParameterMapper> testParameterMappers) {
      this.testParameterMappers = testParameterMappers;
   }

   public List<TemplateTestMapper> getTemplateTestMappers() {
      return templateTestMappers;
   }

   public void setTemplateTestMappers(List<TemplateTestMapper> templateTestMappers) {
      this.templateTestMappers = templateTestMappers;
   }

   public ReportTypeMaster getReportTypeMaster() {
      return reportTypeMaster;
   }

   public void setReportTypeMaster(ReportTypeMaster reportTypeMaster) {
      this.reportTypeMaster = reportTypeMaster;
   }

   public Boolean getResultEntry() {
      return resultEntry;
   }

   public void setResultEntry(Boolean resultEntry) {
      this.resultEntry = resultEntry;
   }

   public String getOtherLanguageName() {
      return otherLanguageName;
   }

   public void setOtherLanguageName(String otherLanguageName) {
      this.otherLanguageName = otherLanguageName;
   }

   @Override
   public String toString() {
      return "TestMaster{" +
            "organizationMaster=" + organizationMaster +
            ", subDepartmentMaster=" + subDepartmentMaster +
            ", sampleTypeMaster=" + sampleTypeMaster +
            ", containerMaster=" + containerMaster +
            ", reportTypeMaster=" + reportTypeMaster +
            ", serviceMaster=" + serviceMaster +
            ", testParameterMappers=" + testParameterMappers +
            ", templateTestMappers=" + templateTestMappers +
            ", aliasName='" + aliasName + '\'' +
            ", noOfSample=" + noOfSample +
            ", sampleVolume=" + sampleVolume +
            ", codificationNo='" + codificationNo + '\'' +
            ", footNote='" + footNote + '\'' +
            ", urgentTatTypeDurationId=" + urgentTatTypeDurationId +
            ", urgentTatDuration=" + urgentTatDuration +
            ", normalTatTypeDurationId=" + normalTatTypeDurationId +
            ", normalTatDuration=" + normalTatDuration +
            ", provisionalRelease=" + provisionalRelease +
            ", approval='" + approval + '\'' +
            ", save='" + save + '\'' +
            ", verify='" + verify + '\'' +
            ", clinicalInterpitation=" + clinicalInterpitation +
            ", resultEntry=" + resultEntry +
            '}';
   }
}
