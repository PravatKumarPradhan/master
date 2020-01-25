package com.sdgt.medcare.master.exeception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ConflictException class is used to handle conflict request exception
 *
 * @author Nitesh Kumar
 * @author Pravat Kumar Pradhan
 */

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
   private Logger logger = LoggerFactory.getLogger(ConflictException.class);

   private static final long serialVersionUID = -3916525550413865316L;


   public ConflictException() {
      super();
   }

   public ConflictException(String message) {
      super(message);
   }

   public ConflictException(String message, Throwable cause) {
      super(message, cause);
   }
   protected ConflictException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }



}
