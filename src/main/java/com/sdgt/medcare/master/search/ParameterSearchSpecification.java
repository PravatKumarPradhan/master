package com.sdgt.medcare.master.search;

import com.sdgt.medcare.master.entity.lab.ParameterMaster;
import com.sdgt.medcare.master.entity.lab.ReferenceRangeMasterTypeMaster;
import com.sdgt.medcare.master.repository.lab.ParameterMasterRepository;
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
public class ParameterSearchSpecification {


   @Autowired
   ParameterMasterRepository parameterMasterRepository;
   public Page<ParameterMaster> parameterMastersSearch(GlobalSearchRequest searchKey, Pageable pageable){
      Page<ParameterMaster> parameterSearchList=   parameterMasterRepository.findAll(ParameterMasterSpecification.findByCriteria(searchKey),pageable);
      return parameterSearchList;

   }
   public static class ParameterMasterSpecification{
      public static Specification<ParameterMaster>  findByCriteria(GlobalSearchRequest searchKey){
         return new Specification<ParameterMaster>() {
            @Override
            public Predicate toPredicate(Root<ParameterMaster> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
               List<Predicate> predicateList=new ArrayList<>();
               if(StringUtils.isNotBlank(searchKey.getParameterName())){
                  Expression<String> parameterName=root.get("desc");
                  Expression<String> caseInSensitive=criteriaBuilder.lower(parameterName);
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getParameterName().toLowerCase()+"%"));
               }
               if(StringUtils.isNotBlank(searchKey.getParameterCode())){
                  Expression<String> parameterCode=root.get("code");
                  Expression<String> caseInSensitive=criteriaBuilder.lower(parameterCode);
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getParameterCode().toLowerCase()+"%"));
               }
               if(StringUtils.isNotBlank(searchKey.getParameterAlias())){
                  Expression<String> parameterAlias=root.get("aliasName");
                  Expression<String> caseInSensitive=criteriaBuilder.lower(parameterAlias);
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getParameterAlias().toLowerCase()+"%"));
               }
               if(searchKey.getReferenceRangeMasterTypeMaster()!=null && searchKey.getReferenceRangeMasterTypeMaster().longValue()>0){
                  Join<ParameterMaster, ReferenceRangeMasterTypeMaster> referenceRangeMasterTypeMaster=root.join("referenceRangeMasterTypeMaster") ;
                  predicateList.add(criteriaBuilder.equal(referenceRangeMasterTypeMaster.get("id"),searchKey.getReferenceRangeMasterTypeMaster()));
               }
               Boolean value = "true".equalsIgnoreCase(String.valueOf(searchKey.getParameterStatus())) ? Boolean.TRUE :
                      "false".equalsIgnoreCase(String.valueOf(searchKey.getParameterStatus())) ? Boolean.FALSE : null;
               if(value!=null ) {
                  Expression<Boolean> parameterStatus=root.join("active");
                  predicateList.add(criteriaBuilder.equal(parameterStatus,value));
               }
               return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
         } ;
      }
   }
}
