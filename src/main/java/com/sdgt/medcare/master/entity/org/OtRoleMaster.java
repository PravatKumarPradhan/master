package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_ot_role", schema = "otims")
public class OtRoleMaster extends BaseMaster {

	@Column(name="ot_role_code")
	private String otRoleCode;

	public String getOtRoleCode() {
		
		return otRoleCode;
	}

	public void setOtRoleCode(String otRoleCode) {
		
		this.otRoleCode = otRoleCode;
	}
}
