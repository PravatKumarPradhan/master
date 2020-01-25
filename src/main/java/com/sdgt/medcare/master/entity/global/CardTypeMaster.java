package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="m_card_type_master",schema="service")
public class CardTypeMaster extends BaseMaster {

    @Column(name="card_type_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3)
    private String cardTypeCode;

    public String getCardTypeCode() {
        return cardTypeCode;
    }

    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode;
    }
		
}

