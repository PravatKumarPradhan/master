package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.GenderMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_template", schema = "lab")
public class TemplateMaster extends BaseMaster {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Org_ID")
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="gender_master_id")
	private GenderMaster genderMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="doctor_master_id")
	private EmployeeMasterDetails employeeMasterDetails;

	@Column(name = "template_data", length=10485760)
	private String templateData;

	public GenderMaster getGenderMaster() {
		return genderMaster;
	}

	public void setGenderMaster(GenderMaster genderMaster) {
		this.genderMaster = genderMaster;
	}

	public EmployeeMasterDetails getResourceMaster() {
		return employeeMasterDetails;
	}

	public void setResourceMaster(EmployeeMasterDetails employeeMasterDetails) {
		this.employeeMasterDetails = employeeMasterDetails;
	}

	public String getTemplateData() {
		return templateData;
	}

	public void setTemplateData(String templateData) {
		this.templateData = templateData;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

}
