package com.sdgt.medcare.master.dto.common;


import lombok.Data;

/**
 * @author Pravat Kumar Pradhan
 */
@Data
public class SearchRequestServiceTariff {
   private String serviceCode;
   private String serviceName;
   private Long tariffId;
   private String tariffCode;
   private Long visitTypeId;
   private String visitTypeCode;
   private String financialCode;
   private Long financialId;
   //private String financialId;
   private Long effectiveDateStart;
   private Long   effectiveDateEnd;
   private Boolean status;
}
