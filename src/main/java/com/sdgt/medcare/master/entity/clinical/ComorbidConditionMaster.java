package com.sdgt.medcare.master.entity.clinical;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="m_comorbid_condition",schema = "clinical")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
@Entity
public class ComorbidConditionMaster extends BaseMaster implements Serializable {
   private String icdCode;
   private String snomedCode;

   public String getSnomedCode() {
      return snomedCode;
   }

   public void setSnomedCode(String snomedCode) {
      this.snomedCode = snomedCode;
   }

   public String getIcdCode() {
      return icdCode;
   }

   public void setIcdCode(String icdCode) {
      this.icdCode = icdCode;
   }
}
