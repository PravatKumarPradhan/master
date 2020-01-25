package com.sdgt.medcare.master.entity.org;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name = "m_cabin", schema = "public")
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
public class CabinMaster extends BaseMaster {


   @MastersFieldCustomAnnotation (displayName = "Organisation", sequence = 0, nullable = false)
   @ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
   @JoinColumn (name = "OrganizationMaster_id")
   private OrganizationMaster organizationMaster;


   @MastersFieldCustomAnnotation (displayName = "Unit", sequence = 1, nullable = false)
   @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
   @JsonIgnoreProperties ({"organizationMaster", "countryMaster", "stateMaster", "cityMaster"})
   @JoinColumn (name = "UnitMaster_id")
   private UnitMaster unitMaster;

   public CabinMaster() {
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
