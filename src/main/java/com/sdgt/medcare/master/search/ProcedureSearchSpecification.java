package com.sdgt.medcare.master.search;

import com.sdgt.medcare.master.entity.global.ProcedureMaster;
import com.sdgt.medcare.master.entity.global.ProcedureTypeMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.repository.common.ProcedureRepository;
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
 *
 *
 */
@Service
public class ProcedureSearchSpecification {


   @Autowired
   ProcedureRepository procedureRepository;

   public Page<ProcedureMaster> procedureMasterSearch(GlobalSearchRequest searchKeys, Pageable pageable)
   {
      Page <ProcedureMaster> procedureMastersList=procedureRepository.findAll(ProcedureMasterSpecification.findByCriteria(searchKeys),pageable);
      return procedureMastersList;
   }

   public static class   ProcedureMasterSpecification  {
      public static Specification<ProcedureMaster> findByCriteria(GlobalSearchRequest searchKey) {
         return new Specification<ProcedureMaster>() {
            @Override
            public Predicate toPredicate(Root<ProcedureMaster> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

               List<Predicate> predicateList=new ArrayList<>();

               if(StringUtils.isNotBlank(searchKey.getProcedureCode())){
                  Expression<String> procedureCode=root.get("code")  ;
                  Expression<String> caseInsensitiveProcedureCode=criteriaBuilder.lower(procedureCode);
                  predicateList.add(criteriaBuilder.like(caseInsensitiveProcedureCode,"%"+searchKey.getProcedureCode().toLowerCase()+"%"));
               }
               if(StringUtils.isNotBlank(searchKey.getProcedureName())){
                  Expression<String> procedureName=root.get("desc")  ;
                  Expression<String> caseInsensitiveProcedureName=criteriaBuilder.lower(procedureName);
                  predicateList.add(criteriaBuilder.like(caseInsensitiveProcedureName,"%"+searchKey.getProcedureName().toLowerCase()+"%"));
                 }
               if(BooleanUtils.isFalse(searchKey.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicateList.add(criteriaBuilder.isFalse(isStatus));
               }
               if(BooleanUtils.isTrue(searchKey.getStatus())){
                  Expression<Boolean> isStatus=root.get("active");
                  predicateList.add(criteriaBuilder.isTrue(isStatus));
               }
               if(searchKey.getProcedureSpeciality()!=null&& searchKey.getProcedureSpeciality().longValue()>0){
                  Join<ProcedureMaster, DepartmentMaster> specialityType=root.join("department")  ;
                  predicateList.add(criteriaBuilder.equal(specialityType.get("id"),searchKey.getProcedureSpeciality()));
               }

               if(searchKey.getProcedureType()!=null&& searchKey.getProcedureType().longValue()>0){
                  Join<ProcedureMaster, ProcedureTypeMaster> procedureType=root.join("procedureTypeMaster")  ;
                  predicateList.add(criteriaBuilder.equal(procedureType.get("id"),searchKey.getProcedureType()));
               }

               return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
         }  ;
      }
   }
}
