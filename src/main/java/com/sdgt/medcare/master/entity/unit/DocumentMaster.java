package com.sdgt.medcare.master.entity.unit;

import com.sdgt.medcare.master.entity.BaseMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_document", schema = "inventory")
@SequenceGenerator(name = "document_seq", sequenceName = "inventory.document_seq", allocationSize = 1)
@DynamicUpdate
public class DocumentMaster extends BaseMaster {

    @Column(name = "sale_transaction_type")
    private String saleTransactionType;

    public DocumentMaster() {
    }

    public String getSaleTransactionType() {
        return saleTransactionType;
    }

    public void setSaleTransactionType(String saleTransactionType) {
        this.saleTransactionType = saleTransactionType;
    }
}
