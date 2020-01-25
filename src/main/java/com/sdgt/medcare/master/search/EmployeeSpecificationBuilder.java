package com.sdgt.medcare.master.search;

import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecificationBuilder {

   public List<SpecificationSearchCriteria> params;

   public EmployeeSpecificationBuilder(){
      params=new ArrayList<>();
   }

   public final EmployeeSpecificationBuilder with(final String key,final String operation,final Object value,final String prefix,final String suffix){
      return with(key,operation,value,prefix,suffix) ;
   }

   public final EmployeeSpecificationBuilder with(final String orPredicate,final String key,final String operation,final Object value,final String prefix,final String suffix) {

      SearchOperationOperators operator=SearchOperationOperators.getSimpleOperation(operation.charAt(0));
      if(operator!=null){
         if(operator==SearchOperationOperators.EQUALITY){
            final boolean startWIthAsterisk=prefix!=null && prefix.contains(SearchOperationOperators.ZERO_OR_MORE_REGEX);
            final boolean endWIthAsterisk=suffix!=null && suffix.contains(SearchOperationOperators.ZERO_OR_MORE_REGEX);
            if(startWIthAsterisk && endWIthAsterisk){
               operator=SearchOperationOperators.CONTAINS;
            }
            else if(startWIthAsterisk){
               operator=SearchOperationOperators.ENDS_WITH;
            }
            else if(endWIthAsterisk){
               operator=SearchOperationOperators.STARTS_WITH;
            }

         }
         params.add(new SpecificationSearchCriteria(orPredicate,key,operator,value));
      }
      return this;

   }

   public Specification<EmployeeMasterDetails> build() {
      if(params.size()==0)
         return null;
      Specification<EmployeeMasterDetails> result=new EmployeeSpecification(params.get(0));
      for (int i=1;i<params.size();i++){
         result=params.get(i).getOrPredicate()?Specification.where(result).or( new EmployeeSpecification(params.get(i))):
               Specification.where(result).and(new EmployeeSpecification(params.get(i)));

      }
      return result;

   }

   public final EmployeeSpecificationBuilder with(EmployeeSpecification spec){
      params.add(spec.getCriteria());
      return this;
   }
   public final EmployeeSpecificationBuilder with(SpecificationSearchCriteria  criteria){
      params.add(criteria);
      return this;
   }

}
