package com.sdgt.medcare.master.exeception;

import org.springframework.dao.DataIntegrityViolationException;

public class CustomDataAlreadyExistsException extends RuntimeException {

   private Integer errorCode;

  /* public CustomDataAlreadyExistsException(String message, DataIntegrityViolationException e) {
      super(message, e);
   }*/
   public CustomDataAlreadyExistsException(String message,DataIntegrityViolationException e,ErrorCodes errorCode){
      super(message,e);
      this.errorCode=errorCode.getCode();

   }
}

