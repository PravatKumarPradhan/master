package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
@Table (name = "m_identification_type")
public class IdentificationTypeMaster extends BaseMaster {
   @Column (name = "is_primary_id")
   @MastersFieldCustomAnnotation (displayName = "Primary Type", sequence = 7)
   private Boolean isPrimaryId;

   @Column (name = "is_expire_req")
   @MastersFieldCustomAnnotation (displayName = "Expire Date Required", sequence = 8)
   private Boolean isExpireReq;

   @Column (name = "is_my_kad")
   @MastersFieldCustomAnnotation (visibleToUser = false, editableByUser = false, sequence = 5)
   private Boolean isMyKad;

   @Column (name = "validation")
   @MastersFieldCustomAnnotation (visibleToUser = false, editableByUser = false, sequence = 6)
   private String validation;

   public Boolean getIsPrimaryId() {
      return isPrimaryId;
   }

   public void setIsPrimaryId(Boolean isPrimaryId) {
      this.isPrimaryId = isPrimaryId;
   }

   public Boolean getIsExpireReq() {
      return isExpireReq;
   }

   public void setIsExpireReq(Boolean isExpireReq) {
      this.isExpireReq = isExpireReq;
   }

   public Boolean getMyKad() {
      return isMyKad;
   }

   public void setMyKad(Boolean myKad) {
      isMyKad = myKad;
   }

   public IdentificationTypeMaster() {
   }

   public IdentificationTypeMaster(Boolean isPrimaryId, Boolean isExpireReq, Boolean isMyKad, String validation) {
      this.isPrimaryId = isPrimaryId;
      this.isExpireReq = isExpireReq;
      this.isMyKad = isMyKad;
      this.validation = validation;
   }

   public String getValidation() {
      return validation;
   }

   public void setValidation(String validation) {
      this.validation = validation;
   }
}
