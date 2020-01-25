package com.sdgt.medcare.master.exeception;


public enum ErrorCodes {
   VALIDATION_PARSE_ERROR(409);

   private int code;

   ErrorCodes(int code) {
      this.code = code;
   }

   public int getCode() {
      return code;
   }
}
