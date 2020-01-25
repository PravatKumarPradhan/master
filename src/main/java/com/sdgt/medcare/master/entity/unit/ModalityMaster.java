/**
 * 
 */
package com.sdgt.medcare.master.entity.unit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;

/**
 * @author Sankha
 *
 */
@Entity
@Table(name = "m_modality")
public class ModalityMaster extends AbstractBaseEntity {

	@MastersFieldCustomAnnotation(displayName = "Code", sequence = 3)
	@Column(name = "code")
	protected String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
