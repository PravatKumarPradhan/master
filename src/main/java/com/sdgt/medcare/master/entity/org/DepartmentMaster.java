package com.sdgt.medcare.master.entity.org;


import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.unit.UnitDepartmentMapper;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
@Table (name = "m_department")
public class DepartmentMaster extends BaseMaster {

   @Column (name = "department_code")

   @MastersFieldCustomAnnotation (visibleToUser = false, sequence = 5, editableByUser = false)
   private String departmentCode;

   @Column (name = "is_clinical_speciality")
   @MastersFieldCustomAnnotation (displayName = "Clinical Specialty", sequence = 4)
   private Boolean isClinicalSpeciality;


   @Column (name = "is_surgical_speciality")
   @MastersFieldCustomAnnotation (displayName = "Surgical Specialty", sequence = 3)
   private Boolean isSurgicalSpeciality;


   @Column (name = "dept_prefix")
   @MastersFieldCustomAnnotation (visibleToUser = false, sequence = 6, editableByUser = false)
   private String departmentPrefix;



   @ManyToMany (fetch = FetchType.LAZY)
   // @JoinTable(name = "unit_dept_mapper", joinColumns = @JoinColumn(name = "UnitMaster_id"), inverseJoinColumns = @JoinColumn(name = "DepartmentMaster_id")))
   @JoinTable (name = "unit_dept_mapper", joinColumns = @JoinColumn (name = "department_master_id"), inverseJoinColumns = @JoinColumn (name = "unit_master_id"))
   @MastersFieldCustomAnnotation (visibleToUser = false, sequence = 7, editableByUser = false)
   private List<UnitDepartmentMapper> unitDepartmentMapper;


   @OneToMany (fetch = FetchType.LAZY, mappedBy = "departmentMaster")
   @MastersFieldCustomAnnotation (visibleToUser = false, sequence = 8, editableByUser = false)
   Set<SubDepartmentMaster> subDepartmentMasters;

   public List<UnitDepartmentMapper> getUnitDepartmentMapper() {
      return unitDepartmentMapper;
   }

   public void setUnitDepartmentMapper(List<UnitDepartmentMapper> unitDepartmentMapper) {
      this.unitDepartmentMapper = unitDepartmentMapper;
   }

   public OrganizationMaster getOrganizationMaster() {
      return organizationMaster;
   }

   public void setOrganizationMaster(OrganizationMaster organizationMaster) {
      this.organizationMaster = organizationMaster;
   }

   @ManyToOne (fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
   @JoinColumn (name = "organization_id")
   @MastersFieldCustomAnnotation (displayName = "Organisation", sequence = 0,nullable = false)
   private OrganizationMaster organizationMaster;

   public String getDepartmentCode() {
      return departmentCode;
   }

   public void setDepartmentCode(String departmentCode) {
      this.departmentCode = departmentCode;
   }


   public String getDepartmentPrefix() {
      return departmentPrefix;
   }

   public void setDepartmentPrefix(String departmentPrefix) {
      this.departmentPrefix = departmentPrefix;
   }

   public Set<SubDepartmentMaster> getSubDepartmentMasters() {
      return subDepartmentMasters;
   }

   public void setSubDepartmentMasters(Set<SubDepartmentMaster> subDepartmentMasters) {
      this.subDepartmentMasters = subDepartmentMasters;
   }

   public Boolean getIsSurgicalSpeciality() {
      return isSurgicalSpeciality;
   }

   public void setIsSurgicalSpeciality(Boolean isSurgicalSpeciality) {
      this.isSurgicalSpeciality = isSurgicalSpeciality;
   }

   public Boolean getIsClinicalSpeciality() {
      return isClinicalSpeciality;
   }

   public void setIsClinicalSpeciality(Boolean isClinicalSpeciality) {
      this.isClinicalSpeciality = isClinicalSpeciality;
   }

   public DepartmentMaster() {
   }
}
