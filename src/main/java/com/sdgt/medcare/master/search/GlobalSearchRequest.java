package com.sdgt.medcare.master.search;

import lombok.Data;

/***
 * @author Pravat Kumar Pradhan
 */
@Data
public class GlobalSearchRequest {
   public String employeeName;
   public Long employeeDepartment;
   public String employeeCode;
   public  Long identificationType;
   public String identificationNumber;
   public Long employeeSubDepartment;
   public Long employeeTypeMaster;
   public String parameterName;
   public String parameterCode;
   public String parameterAlias;
   public Long referenceRangeMasterTypeMaster;
   public Boolean parameterStatus;
   public String testName;
   public String  testCode;
   public String serviceName;
   public Long  testSampleType;
   public Boolean testStatus;
   public String serviceCode;
   public Long reportType;
   public Boolean status;
   public String groupCode;
   public String groupDesc;
   public String subGroupCode;
   public String subGroupDesc;
   public String unitServiceMapperId;
   public Boolean isDoctorRequired;
   public String groupId;
   public String subGroupId;
   public Long fromDate;
   public Long toDate;
   public String serviceNameOrDescription;
   private String employeeNumber;
   private Long tariffId;
   private String tariffCode;
   private Long visitTypeId;
   private String visitTypeCode;
   private String financialCode;
   private Long financialId;
   private Long effectiveDateStart;
   private Long   effectiveDateEnd;
   public String procedureCode;
   public String procedureName;
   public Long procedureSpeciality;
   public Long procedureType;
}
