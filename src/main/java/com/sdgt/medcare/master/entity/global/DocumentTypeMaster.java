package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
@Table(name = "m_document_type", schema = "public")
 public class DocumentTypeMaster extends BaseMaster {

	@MastersFieldCustomAnnotation (visibleToUser = false)
	@Column(name="doc_type_code")
	private String docTypeCode;

	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
}
