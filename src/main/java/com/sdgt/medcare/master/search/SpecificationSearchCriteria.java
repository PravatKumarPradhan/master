package com.sdgt.medcare.master.search;

public class SpecificationSearchCriteria {

   private String key;
   private SearchOperationOperators operation;
   private Object value;
   private Boolean orPredicate;

   public SpecificationSearchCriteria(){

   }

   public SpecificationSearchCriteria(final String key, final  SearchOperationOperators operation, final  Object value){
      super();
      this.key=key;
      this.operation = operation;
      this.value=value;

   }
   public SpecificationSearchCriteria(final String  orPredicate,final String key, final SearchOperationOperators operation, final Object value) {
      super();
      this.key=key;
      this.orPredicate=orPredicate !=null && orPredicate.equals(SearchOperationOperators.OR_PREDICATE_FLAG);
      this.value=value;
      this.operation = operation;
   }

   public SpecificationSearchCriteria(String key,String operation,String prefix,String suffix,String value) {
      SearchOperationOperators operationOperators=SearchOperationOperators.getSimpleOperation(operation.charAt(0));
      if(operationOperators!=null){
         if(operationOperators==SearchOperationOperators.EQUALITY){
            final boolean startWIthAsterisk=prefix!=null && prefix.contains(SearchOperationOperators.ZERO_OR_MORE_REGEX);
            final boolean  endWithAsterisk=suffix!=null && suffix.contains(SearchOperationOperators.ZERO_OR_MORE_REGEX);
            if(startWIthAsterisk && endWithAsterisk){
               operationOperators=SearchOperationOperators.CONTAINS;
            }
            else if(startWIthAsterisk){
               operationOperators=SearchOperationOperators.ENDS_WITH;
            }
            else if(endWithAsterisk){
               operationOperators=SearchOperationOperators.STARTS_WITH;
            }

         }
      }
      this.key=key;
      this.value=value;
      this.operation=operationOperators;

   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public SearchOperationOperators getOperation() {
      return operation;
   }

   public void setOperation(SearchOperationOperators operation) {
      this.operation = operation;
   }

   public Object getValue() {
      return value;
   }

   public void setValue(Object value) {
      this.value = value;
   }

   public Boolean getOrPredicate() {
      return orPredicate;
   }

   public void setOrPredicate(Boolean orPredicate) {
      this.orPredicate = orPredicate;
   }
}
