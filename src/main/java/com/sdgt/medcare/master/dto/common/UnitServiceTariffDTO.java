package com.sdgt.medcare.master.dto.common;

import com.sdgt.medcare.master.dto.BaseDTO;

import java.util.Date;

public class UnitServiceTariffDTO extends BaseDTO {
   /*Create*/
   private String unitTariffServiceRateId;
   private String tariffCode;
   private String financialClassCode;
   private String visitTypeCode;
   private Double rate;
   private Boolean active;
   private Long unitServiceMapperId;
   private Date effectiveDate;
   private  Long serviceMasterId;



   public String getTariffCode() {
      return tariffCode;
   }

   public void setTariffCode(String tariffCode) {
      this.tariffCode = tariffCode;
   }

   public String getFinancialClassCode() {
      return financialClassCode;
   }

   public void setFinancialClassCode(String financialClassCode) {
      this.financialClassCode = financialClassCode;
   }

   public String getVisitTypeCode() {
      return visitTypeCode;
   }

   public void setVisitTypeCode(String visitTypeCode) {
      this.visitTypeCode = visitTypeCode;
   }

   public Double getRate() {
      return rate;
   }

   public void setRate(Double rate) {
      this.rate = rate;
   }

   public Boolean getActive() {
      return active;
   }

   public void setActive(Boolean active) {
      this.active = active;
   }

   public Long getUnitServiceMapperId() {
      return unitServiceMapperId;
   }

   public void setUnitServiceMapperId(Long unitServiceMapperId) {
      this.unitServiceMapperId = unitServiceMapperId;
   }

   public String getUnitTariffServiceRateId() {
      return unitTariffServiceRateId;
   }

   public void setUnitTariffServiceRateId(String unitTariffServiceRateId) {
      this.unitTariffServiceRateId = unitTariffServiceRateId;
   }

   public Date getEffectiveDate() {
      return effectiveDate;
   }

   public void setEffectiveDate(Date effectiveDate) {
      this.effectiveDate = effectiveDate;
   }

   public Long getServiceMasterId() {
      return serviceMasterId;
   }

   public void setServiceMasterId(Long serviceMasterId) {
      this.serviceMasterId = serviceMasterId;
   }
}
