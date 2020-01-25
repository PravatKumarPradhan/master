package com.sdgt.medcare.master.search;

import com.sdgt.medcare.master.entity.org.GroupMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.SubGroupMaster;
import com.sdgt.medcare.master.repository.common.ServiceMasterRepository;
import com.sdgt.medcare.master.util.BaseUtil;
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
import java.util.Date;
import java.util.List;


/**
 * @author Pravat Kumar Pradhan
 */
@Service
public class ServiceSpecification {

   @Autowired
   private ServiceMasterRepository serviceMasterRepository;


   @Transactional
   public Page<ServiceMaster> searchGroup(GlobalSearchRequest searchKeys, Pageable pageable){
    //  List <ServiceMaster> serviceMasterList=serviceMasterRepository.findAll(searchKeys);
      Page <ServiceMaster> serviceMasterList=serviceMasterRepository.findAll(ServiceMasterSpecification.findByCriteria(searchKeys),pageable);
      return serviceMasterList;
   }
   public static class ServiceMasterSpecification{
      private static Specification<ServiceMaster> findByCriteria(GlobalSearchRequest  searchKeys) {
         return new Specification<ServiceMaster>() {

            @Override
            public  Predicate toPredicate(Root<ServiceMaster> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
               List<Predicate> predicates=new ArrayList<>();
               Predicate pre=criteriaBuilder.disjunction();
               if(searchKeys.getServiceNameOrDescription()!=null && ! searchKeys.getServiceNameOrDescription().isEmpty()){
                  Expression<String> path = root.get("desc");
                  Expression<String> caseInSensitiveForDesc=criteriaBuilder.lower(path);
                  predicates.add( criteriaBuilder.like(caseInSensitiveForDesc,"%"+searchKeys.getServiceNameOrDescription().toLowerCase()+"%")) ;

               }
               if(StringUtils.isNotBlank(searchKeys.getServiceCode())){
                  Expression<String> path=root.get("code");
                  Expression<String> caseInSensitiveForDesc=criteriaBuilder.lower(path);
                  predicates.add(criteriaBuilder.like(caseInSensitiveForDesc,"%"+searchKeys.getServiceCode().toLowerCase()+"%"));
               }
               if(BooleanUtils.isFalse(searchKeys.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicates.add(criteriaBuilder.isFalse(isStatus));
               }
               if(BooleanUtils.isTrue(searchKeys.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicates.add(criteriaBuilder.isTrue(isStatus));
               }

               if(StringUtils.isNotBlank(searchKeys.getGroupId())){
                  Join<ServiceMaster, GroupMaster> groupMaster=root.join("groupMaster")  ;
                //  predicates.add(groupMaster.in(searchKeys.getGroupId()));
                  predicates.add(criteriaBuilder.equal(groupMaster.get("id"),searchKeys.getGroupId()));
               }
               if(StringUtils.isNotBlank(searchKeys.getSubGroupId())){
                  Join<ServiceMaster, SubGroupMaster> subGroupMaster=root.join("subGroupMaster") ;
                // predicates.add(subGroupMasterJoin.in(searchKeys.getSubGroupId()));
                  predicates.add(criteriaBuilder.equal(subGroupMaster.get("id"),searchKeys.getSubGroupId()));
               }

                if(searchKeys.getFromDate()!=null&&searchKeys.getFromDate().longValue()>0&&searchKeys.getToDate()!=null&& searchKeys.getToDate().longValue()>0 ) {

                   Expression<Date> fromDate=root.get("validityFrom")  ;
                   Expression<Date> toDate=root.get("validityTo")  ;
                   predicates.add(criteriaBuilder.between(toDate,BaseUtil.epchocTosqlDate(searchKeys.getFromDate()),BaseUtil.epchocTosqlDate(searchKeys.getToDate())));
                   predicates.add(criteriaBuilder.between(fromDate,BaseUtil.epchocTosqlDate(searchKeys.getFromDate()),BaseUtil.epchocTosqlDate(searchKeys.getToDate()))) ;

               }
                else  if(searchKeys.getFromDate()!=null&&searchKeys.getFromDate().longValue()>0) {
                   Expression<Date> fromDate=root.get("validityFrom");
                   predicates.add(criteriaBuilder.greaterThanOrEqualTo(fromDate, BaseUtil.epchocTosqlDate(searchKeys.getFromDate()))) ;
                }
                else  if(searchKeys.getToDate()!=null&& searchKeys.getToDate().longValue()>0) {
                   Expression<Date> toDate=root.get("validityTo");
                   predicates.add(criteriaBuilder.lessThanOrEqualTo(toDate,BaseUtil.epchocTosqlDate(searchKeys.getToDate())));
                }


               return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
         } ;
      }
   }

}
