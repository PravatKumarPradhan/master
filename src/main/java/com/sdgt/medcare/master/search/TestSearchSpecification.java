package com.sdgt.medcare.master.search;


import com.sdgt.medcare.master.entity.lab.ReportTypeMaster;
import com.sdgt.medcare.master.entity.lab.SampleTypeMaster;
import com.sdgt.medcare.master.entity.lab.TestMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.repository.lab.TestMasterRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pravat Kumar Pradhan
 */
@Service
public class TestSearchSpecification {


   @Autowired
   TestMasterRepository testMasterRepository;

   public Page<TestMaster> testMasterSearch(GlobalSearchRequest searchKeys, Pageable pageable){
      //  List <ServiceMaster> serviceMasterList=serviceMasterRepository.findAll(searchKeys);
      Page <TestMaster> serviceMasterList=testMasterRepository.findAll(TestMasterSpecification.findByCriteria(searchKeys),pageable);
      return serviceMasterList;
   }

   public static  class   TestMasterSpecification{
      public static Specification<TestMaster>  findByCriteria(GlobalSearchRequest searchKey){
       return   new Specification<TestMaster>() {
          @Override
          public Predicate toPredicate(Root<TestMaster> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

             List<Predicate> predicateList=new ArrayList<>();
             if(StringUtils.isNotBlank(searchKey.getTestName())){
                Expression<String> testName=root.get("desc")  ;
                Expression<String> caseInSensitive=criteriaBuilder.lower(testName);
                predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getTestName().toLowerCase()+"%")) ;
             }
             if(StringUtils.isNotBlank(searchKey.getTestCode())){
                Expression<String> testCode=root.get("code")  ;
                Expression<String> caseInSensitive=criteriaBuilder.lower(testCode);
                predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getTestCode().toLowerCase()+"%")) ;
             }
             if(StringUtils.isNotBlank(searchKey.getServiceName())){
                Join<TestMaster, ServiceMaster> serviceMaster=root.join("serviceMaster") ;
                Expression<String> serviceName=serviceMaster.get("desc");
                Expression<String> caseInSensitive=criteriaBuilder.lower(serviceName);
                predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getServiceName().toLowerCase()+"%"));
             }
             /*if(BooleanUtils.isNotTrue(searchKey.getStatus())){
                Expression<Boolean> isStatus=root.get("active");
                predicateList.add(criteriaBuilder.equal(isStatus,searchKey.getStatus()));
             }
             if(BooleanUtils.isNotFalse(searchKey.getStatus())){
                Expression<Boolean> isStatus=root.get("active");
                predicateList.add(criteriaBuilder.equal(isStatus,searchKey.getStatus()));
             }*/
             if(BooleanUtils.isFalse(searchKey.getStatus())){
                Expression<Boolean> isStatus=root.get("active");
                predicateList.add(criteriaBuilder.isFalse(isStatus));
             }
             if(BooleanUtils.isTrue(searchKey.getStatus())){
                Expression<Boolean> isStatus=root.get("active");
                predicateList.add(criteriaBuilder.isTrue(isStatus));
             }

             if(StringUtils.isNotBlank(searchKey.getServiceCode())){
                Join<TestMaster,ServiceMaster> serviceMaster=root.join("serviceMaster");
                Expression<String> serviceCode=serviceMaster.get("code");
                Expression<String> caseInSensitive=criteriaBuilder.lower(serviceCode);
                predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getServiceCode().toLowerCase()+"%"));
             }
             if(searchKey.getReportType()!=null&& searchKey.getReportType().longValue()>0){
                Join<TestMaster, ReportTypeMaster> reportTypeMaster=root.join("reportTypeMaster")  ;
                predicateList.add(criteriaBuilder.equal(reportTypeMaster.get("id"),searchKey.getReportType()));
             }
             if(searchKey.getTestSampleType()!=null&& searchKey.getTestSampleType().longValue()>0){
                Join<TestMaster, SampleTypeMaster> sampleTypeMaster=root.join("sampleTypeMaster")  ;
                predicateList.add(criteriaBuilder.equal(sampleTypeMaster.get("id"),searchKey.getTestSampleType()));
             }

             return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
          }
       }  ;
      }
   }

}
