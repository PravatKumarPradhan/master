package com.sdgt.medcare.master.exeception;

import org.springframework.dao.DataIntegrityViolationException;

public class SomeOtherException extends Throwable {

   private Integer errorCode;
   public SomeOtherException(String yourErrorCode2, DataIntegrityViolationException e) {
   }

   public SomeOtherException(String message,DataIntegrityViolationException e,ErrorCodes errorCodes){
      super(message,e);
      this.errorCode=errorCodes.getCode();

   }
}
