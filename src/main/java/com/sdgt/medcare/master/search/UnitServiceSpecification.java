package com.sdgt.medcare.master.search;

import com.sdgt.medcare.master.entity.org.GroupMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.SubGroupMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.repository.common.UnitServiceMapperRepository;
import com.sdgt.medcare.master.util.BaseUtil;
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
import java.util.Date;
import java.util.List;

/**
 * @author Pravat Kumar Pradhan
 */
@Service
public class UnitServiceSpecification {


   @Autowired
   UnitServiceMapperRepository unitServiceMapperRepository;

   public Page<UnitServiceMapper> unitServiceMappersSearch(GlobalSearchRequest searchKey,Long unitId,Pageable pageable){
      Page<UnitServiceMapper> unitServiceMapperList=unitServiceMapperRepository.findAll(UnitServiceMapperSpecification.findByCriteria(searchKey,unitId), pageable);
      return unitServiceMapperList;
   }

   /**
    *
    */
   public static  class UnitServiceMapperSpecification{

      private static Specification<UnitServiceMapper> findByCriteria(GlobalSearchRequest searchKey,Long unitId){
         return new Specification<UnitServiceMapper>() {
            @Override
            public Predicate toPredicate(Root<UnitServiceMapper> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

               List<Predicate> predicateList=new ArrayList<>();
               if(StringUtils.isNotBlank(searchKey.getServiceName())){
                  Join<UnitServiceMapper, UnitMaster> unitMaster=root.join("unitMaster");
                  Join<UnitServiceMapper,ServiceMaster> serviceMaster=root.join("serviceMaster") ;
                  Expression<String> serviceName=serviceMaster.get("desc");
                  Expression<String> caseInSensitive=criteriaBuilder.lower(serviceName);
                  predicateList.add(criteriaBuilder.equal(unitMaster.get("id"),unitId));
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getServiceName().toLowerCase()+"%"));
               }
               if(StringUtils.isNotBlank(searchKey.getServiceCode())){
                  Join<UnitServiceMapper, UnitMaster> unitMaster=root.join("unitMaster");
                  Join<UnitServiceMapper,ServiceMaster> serviceMaster=root.join("serviceMaster");
                  Expression<String> serviceCode=serviceMaster.get("code");
                  Expression<String> caseInSensitive=criteriaBuilder.lower(serviceCode);
                  predicateList.add(criteriaBuilder.equal(unitMaster.get("id"),unitId));
                  predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getServiceCode().toLowerCase()+"%"));
               }
               Predicate pre=criteriaBuilder.disjunction();
               if(StringUtils.isNotBlank(searchKey.getGroupId())){
                  Join<UnitServiceMapper, UnitMaster> unitMaster=root.join("unitMaster");
                  Join<UnitServiceMapper, ServiceMaster> serviceMaster=root.join("serviceMaster");
                  Join<ServiceMaster, GroupMaster> groupMaster=serviceMaster.join("groupMaster");
                  predicateList.add(criteriaBuilder.equal(unitMaster.get("id"),unitId));
                  predicateList.add(criteriaBuilder.equal(groupMaster.get("id"),searchKey.getGroupId()));

               }
               if(BooleanUtils.isFalse(searchKey.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicateList.add(criteriaBuilder.isFalse(isStatus));
               }
               if(BooleanUtils.isTrue(searchKey.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicateList.add(criteriaBuilder.isTrue(isStatus));
               }
               if(StringUtils.isNotBlank(searchKey.getSubGroupId())){
                  Join<UnitServiceMapper, UnitMaster> unitMaster=root.join("unitMaster");
                  Join<UnitServiceMapper,ServiceMaster> serviceMaster=root.join("serviceMaster");
                  Join<ServiceMaster, SubGroupMaster> subGroupMaster=serviceMaster.join("subGroupMaster") ;
                  predicateList.add(criteriaBuilder.equal(unitMaster.get("id"),unitId));
                  predicateList.add(criteriaBuilder.equal(subGroupMaster.get("id"),searchKey.getSubGroupId()));
               }

              /* if(BooleanUtils.isNotTrue(searchKey.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicateList.add(criteriaBuilder.equal(isStatus,searchKey.getStatus()));
               }
               if(BooleanUtils.isNotFalse(searchKey.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicateList.add(criteriaBuilder.equal(isStatus,searchKey.getStatus()));
               }*/

               if(searchKey.getFromDate()!=null&&searchKey.getFromDate().longValue()>0&&searchKey.getToDate()!=null&& searchKey.getToDate().longValue()>0 ) {

                  Join<UnitServiceMapper,ServiceMaster> serviceMaster=root.join("serviceMaster");
                  Expression<Date> fromDate=serviceMaster.get("validityFrom")  ;
                  Expression<Date> toDate=serviceMaster.get("validityTo")  ;
                  predicateList.add(criteriaBuilder.between(toDate, BaseUtil.epchocTosqlDate(searchKey.getFromDate()),BaseUtil.epchocTosqlDate(searchKey.getToDate())));
                  predicateList.add(criteriaBuilder.between(fromDate,BaseUtil.epchocTosqlDate(searchKey.getFromDate()),BaseUtil.epchocTosqlDate(searchKey.getToDate()))) ;
               }
               else  if(searchKey.getFromDate()!=null&&searchKey.getFromDate().longValue()>0) {
                  Join<UnitServiceMapper,ServiceMaster> serviceMaster=root.join("serviceMaster");
                  Expression<Date> fromDate=serviceMaster.get("validityFrom");
                  //  predicates.add(criteriaBuilder.greaterThanOrEqualTo(fromDate).)

                  // predicates.add(criteriaBuilder.greaterThanOrEqualTo(((Path<Date>) fromDate).get("validityFrom"),fromDate));
                  predicateList.add(criteriaBuilder.greaterThanOrEqualTo(fromDate, BaseUtil.epchocTosqlDate(searchKey.getFromDate()))) ;
               }
               else  if(searchKey.getToDate()!=null&& searchKey.getToDate().longValue()>0) {
                  Join<UnitServiceMapper,ServiceMaster> serviceMaster=root.join("serviceMaster");
                  Expression<Date> toDate=serviceMaster.get("validityTo");
                  predicateList.add(criteriaBuilder.lessThanOrEqualTo(toDate,BaseUtil.epchocTosqlDate(searchKey.getToDate())));
               }

               return criteriaBuilder.and(predicateList.toArray(new  Predicate[predicateList.size()]));
            }
         } ;
      }
   }
}
