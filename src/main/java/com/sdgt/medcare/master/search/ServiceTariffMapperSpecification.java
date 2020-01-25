package com.sdgt.medcare.master.search;

import com.sdgt.medcare.master.entity.global.VisitTypeMaster;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.entity.unit.UnitTariffServiceRateMaster;
import com.sdgt.medcare.master.repository.common.UnitTariffServiceRateMasterRepository;
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

@Service
public class ServiceTariffMapperSpecification {

   @Autowired
   UnitTariffServiceRateMasterRepository unitTariffServiceRateMasterRepository;

   public Page<UnitTariffServiceRateMaster> tariffServiceRateMastersSearch(GlobalSearchRequest searchKey, Pageable pageable){
      Page<UnitTariffServiceRateMaster>  unitTariffServiceRateMasterList=unitTariffServiceRateMasterRepository.findAll(TariffServiceSpecification.findByCriteria(searchKey),pageable) ;
      return unitTariffServiceRateMasterList;
   }

   public static class TariffServiceSpecification{
      public static Specification<UnitTariffServiceRateMaster> findByCriteria(GlobalSearchRequest searchKey) {
           return new Specification<UnitTariffServiceRateMaster>() {
              @Override
              public Predicate toPredicate(Root<UnitTariffServiceRateMaster> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                 List<Predicate> predicateList=new ArrayList<>();

                 if(StringUtils.isNotBlank(searchKey.getServiceName())){
                    Join<UnitTariffServiceRateMaster, UnitServiceMapper> unitServiceMapper=root.join("unitServiceMapper") ;
                    Join<UnitServiceMapper, ServiceMaster> serviceMaster=unitServiceMapper.join("serviceMaster");
                    Expression<String> serviceName=serviceMaster.get("desc");
                    Expression<String> caseInSensitive=criteriaBuilder.lower(serviceName);
                    predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getServiceName().toLowerCase()+"%"));

                 }
                /* if(BooleanUtils.isNotTrue(searchKey.getStatus())){
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
                 if(searchKey.getVisitTypeId()!=null&& searchKey.getVisitTypeId().longValue()>0){
                    Join<UnitTariffServiceRateMaster, VisitTypeMaster> visitTypeMaster=root.join("visitTypeMaster")  ;
                    predicateList.add(criteriaBuilder.equal(visitTypeMaster.get("id"),searchKey.getVisitTypeId()));
                 }

                 if(searchKey.getTariffId() != null && (searchKey.getTariffId().longValue() > 0)){
                    Join<UnitTariffServiceRateMaster, TariffMaster> tariffMaster=root.join("tariffMaster");
                    predicateList.add(criteriaBuilder.equal(tariffMaster.get("id"),searchKey.getTariffId()));
                 }
                 if(searchKey.getFinancialId()!=null && searchKey.getFinancialId().longValue()>0){
                    Join<UnitTariffServiceRateMaster, FinancialClassMaster> financialClassMaster=root.join("financialClassMaster");
                    predicateList.add(criteriaBuilder.equal(financialClassMaster.get("id"),searchKey.getFinancialId()));
                 }
                 if(StringUtils.isNotBlank(searchKey.getServiceCode())){
                    Join<UnitTariffServiceRateMaster,UnitServiceMapper> unitServiceMapper =root.join("unitServiceMapper") ;
                    Join<UnitServiceMapper,ServiceMaster> serviceMaster=unitServiceMapper.join("serviceMaster");
                    Expression<String> serviceCode=serviceMaster.get("code");
                    Expression<String> caseInSensitive=criteriaBuilder.lower(serviceCode);
                    predicateList.add(criteriaBuilder.like(caseInSensitive,"%"+searchKey.getServiceCode().toLowerCase()+"%"));
                 }
                 if(searchKey.getEffectiveDateStart()!=null && searchKey.getEffectiveDateStart().longValue()>0 && searchKey.getEffectiveDateEnd()!=null && searchKey.getEffectiveDateEnd().longValue()>0){
                    Expression<Date> effectiveDate=root.get("effectiveDate") ;
                    /*predicateList.add(criteriaBuilder.greaterThanOrEqualTo(effectiveDate, BaseUtil.epchocTosqlDate(searchKey.getEffectiveDateStart()))) ;
                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(effectiveDate,BaseUtil.epchocTosqlDate(searchKey.getEffectiveDateEnd())));*/

                    predicateList.add(criteriaBuilder.between(effectiveDate, BaseUtil.epchocTosqlDate(searchKey.getEffectiveDateStart()),BaseUtil.epchocTosqlDate(searchKey.getEffectiveDateEnd())));

                 }
                 return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
              }
           } ;
      }
   }

}
