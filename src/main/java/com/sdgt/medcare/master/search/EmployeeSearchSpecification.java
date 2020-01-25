package com.sdgt.medcare.master.search;


import com.sdgt.medcare.master.entity.global.IdentificationTypeMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.EmployeeTypeMaster;
import com.sdgt.medcare.master.entity.org.SubDepartmentMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import com.sdgt.medcare.master.repository.common.EmployeeRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class EmployeeSearchSpecification {


   @Autowired
   private  EmployeeRepository employeeRepository;

   @Transactional
   public Page<EmployeeMasterDetails> employeeMasterSearch(GlobalSearchRequest searchKeys, Pageable pageable){
      Page <EmployeeMasterDetails> employeeMasterList;
      employeeMasterList   =employeeRepository.findAll(EmployeeMasterSpecification.findByCriteria(searchKeys),pageable);
      return employeeMasterList;
   }

   public static class EmployeeMasterSpecification{

      public static Specification<EmployeeMasterDetails> findByCriteria(GlobalSearchRequest searchKey){
         return new Specification<EmployeeMasterDetails>() {
            @Override
            public Predicate toPredicate(Root<EmployeeMasterDetails> root,  CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

               List<Predicate> predicateList=new ArrayList<>();

               if(StringUtils.isNotBlank(searchKey.getEmployeeName())){
                  Expression<String> employeeName=root.get("name")  ;
                  Expression<String> caseInSensitive=criteriaBuilder.lower(employeeName);
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getEmployeeName().toLowerCase()+"%")) ;
               }
               if(searchKey.getEmployeeDepartment()!=null && searchKey.getEmployeeDepartment().longValue()>0){
                  Join<EmployeeMasterDetails, DepartmentMaster> departmentMaster=root.join("departmentMaster") ;
                  predicateList.add(criteriaBuilder.equal(departmentMaster.get("id"),searchKey.getEmployeeDepartment()))  ;

               }
               if(searchKey.getEmployeeSubDepartment()!=null && searchKey.getEmployeeSubDepartment().longValue()>0)   {
                  Join<EmployeeMasterDetails, SubDepartmentMaster> subDepartmentMaster=root.join("subDepartmentMaster") ;
                  predicateList.add(criteriaBuilder.equal(subDepartmentMaster.get("id"),searchKey.getEmployeeSubDepartment())) ;
               }
               if(StringUtils.isNotBlank(searchKey.getEmployeeCode())){
                  Expression<String> employeeCode=root.get("code");
                  Expression<String> caseInSensitive=criteriaBuilder.lower(employeeCode);
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getEmployeeCode().toLowerCase()+"%"));
               }
               if(BooleanUtils.isFalse(searchKey.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicateList.add(criteriaBuilder.isFalse(isStatus));
               }
               if(BooleanUtils.isTrue(searchKey.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicateList.add(criteriaBuilder.isTrue(isStatus));
               }
               if(StringUtils.isNotBlank(searchKey.getEmployeeNumber())){
                  Expression<String> employeeNo=root.get("employeeNo");
                  Expression<String> caseInSensitive=criteriaBuilder.lower(employeeNo);
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getEmployeeNumber().toLowerCase()+"%"));
               }
               if(searchKey.getEmployeeTypeMaster()!=null && searchKey.getEmployeeTypeMaster().longValue()>0)  {
                  Join<EmployeeMasterDetails, EmployeeTypeMaster> employeeTypeMaster=root.join("employeeTypeMaster")  ;
                  predicateList.add(criteriaBuilder.equal(employeeTypeMaster,searchKey.employeeTypeMaster));
               }
               if(searchKey.getIdentificationType()!=null&& searchKey.getIdentificationType().longValue()>0&&StringUtils.isNotBlank(searchKey.identificationNumber)) {
                  Join<EmployeeMasterDetails, IdentificationTypeMaster> identificationTypeMaster=root.join("identificationTypeMaster") ;
                  predicateList.add(criteriaBuilder.equal(identificationTypeMaster.get("id"),searchKey.getIdentificationType()));
                  Expression<String>   identificationNumber=root.get("identificationNumber");
                  Expression<String> caseInSensitive=criteriaBuilder.lower(identificationNumber);
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getIdentificationNumber().toLowerCase()+"%"));
               }
               else  if(searchKey.getIdentificationType()!=null &&searchKey.getIdentificationType().longValue()>0) {
                  Join <EmployeeMasterDetails,IdentificationTypeMaster> identificationTypeMaster=root.join("identificationTypeMaster");
                  predicateList.add(criteriaBuilder.equal(identificationTypeMaster.get("id"),searchKey.getIdentificationType()));
               }
               else if(StringUtils.isNotBlank(searchKey.getIdentificationNumber())){
                  Expression<String>  identificationNumber=root.get("identificationNumber");
                  Expression<String> caseInSensitive=criteriaBuilder.lower(identificationNumber);
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getIdentificationNumber().toLowerCase()+"%"));

               }
               return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
         }  ;
      }
   }
}
