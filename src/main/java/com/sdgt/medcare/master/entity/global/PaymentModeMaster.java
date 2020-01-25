package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_payment_mode")
 public class PaymentModeMaster extends BaseMaster {


    @Column(name="is_date_req")
    @MastersFieldCustomAnnotation(displayName = "Date Required",sequence = 3)
    private Boolean isDateReq;

    @Column(name="is_card_type_req")
    @MastersFieldCustomAnnotation(displayName = "Card Type Required",sequence = 4)
    private Boolean isCardTypeReq;

    @MastersFieldCustomAnnotation(displayName = "Bank Required",sequence = 5)
    @Column(name="is_bank_req")
    private  Boolean isBankReq;

   public Boolean getIsDateReq(){
      return isDateReq;
   }

   public void setIsDateReq(Boolean isDateReq){
      this.isDateReq=isDateReq;
   }

    public Boolean getIsCardTypeReq() {
        return isCardTypeReq;
    }

    public void setIsCardTypeReq(Boolean isCardTypeReq) {
        isCardTypeReq = isCardTypeReq;
    }

    public Boolean getIsBankReq() {
        return isBankReq;
    }

    public void setIsBankReq(Boolean isbankReq) {
        isBankReq = isbankReq;
    }

    public PaymentModeMaster() {
    }

    public PaymentModeMaster( Boolean isDateReq, Boolean isCardTypeReq, Boolean isBankReq) {
        /*this.paymentModeCode = paymentModeCode;*/
        this.isDateReq = isDateReq;
        this.isCardTypeReq = isCardTypeReq;
        this.isBankReq = isBankReq;
    }
}
