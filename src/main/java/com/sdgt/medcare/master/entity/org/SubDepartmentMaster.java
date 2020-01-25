package com.sdgt.medcare.master.entity.org;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_sub_department_master")
@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class SubDepartmentMaster extends BaseMaster {

   @Column(name = "sub_department_code", unique = true, length = 50)
   @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
   private String subDepartmentCode;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
   @JoinColumn(name="organization_id")
   @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
   private OrganizationMaster organizationMaster;

   @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
   @JsonIgnoreProperties({"subDepartmentMasters","unitDepartmentMapper"})
   @JoinColumn(name = "department_id")
   @MastersFieldCustomAnnotation(displayName = "Department",sequence = 4,nullable = false)
   private DepartmentMaster departmentMaster;


   public SubDepartmentMaster() {
   }

   public SubDepartmentMaster(String subDepartmentCode, OrganizationMaster organizationMaster, DepartmentMaster departmentMaster) {
      this.subDepartmentCode = subDepartmentCode;
      this.organizationMaster = organizationMaster;
      this.departmentMaster = departmentMaster;
   }

   public String getSubDepartmentCode() {
      return subDepartmentCode;
   }

   public void setSubDepartmentCode(String subDepartmentCode) {
      this.subDepartmentCode = subDepartmentCode;
   }

   public OrganizationMaster getOrganizationMaster() {
      return organizationMaster;
   }

   public void setOrganizationMaster(OrganizationMaster organizationMaster) {
      this.organizationMaster = organizationMaster;
   }

   public DepartmentMaster getDepartmentMaster() {
      return departmentMaster;
   }

   public void setDepartmentMaster(DepartmentMaster departmentMaster) {
      this.departmentMaster = departmentMaster;
   }
}

