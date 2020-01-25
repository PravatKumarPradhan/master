package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.unit.EmployeeMasterDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name="m_employee_edu_details",schema="employee")
public class EmployeeEducationDetails extends BaseMaster {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QualificationMaster_id")
	private QualificationMaster qualificationMaster;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="document_type_master")
	private DocumentTypeMaster documentTypeMaster;

	@Column(name="document_name")
	private String documentName;

	@Column(name="document_upload")
	private String documentUpload;

	@Column
	private Blob attachment;

	@Column(name = "passing_year")
	private Long passingYear;


	@Column(name="percentage")
	private Long percentage;

	@Column(name="university")
	private String university;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_master_id")
	private EmployeeMasterDetails employeeMasterDetails;

	public QualificationMaster getQualificationMaster() {
		return qualificationMaster;
	}

	public void setQualificationMaster(QualificationMaster qualificationMaster) {
		this.qualificationMaster = qualificationMaster;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentUpload() {
		return documentUpload;
	}

	public void setDocumentUpload(String documentUpload) {
		this.documentUpload = documentUpload;
	}

	public Blob getAttachment() {
		return attachment;
	}

	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}

	public EmployeeMasterDetails getEmployeeMasterDetails() {
		return employeeMasterDetails;
	}

	public void setEmployeeMasterDetails(EmployeeMasterDetails employeeMasterDetails) {
		this.employeeMasterDetails = employeeMasterDetails;
	}

	public Long getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(Long passingYear) {
		this.passingYear = passingYear;
	}

	public Long getPercentage() {
		return percentage;
	}

	public void setPercentage(Long percentage) {
		this.percentage = percentage;
	}

	public DocumentTypeMaster getDocumentTypeMaster() {
		return documentTypeMaster;
	}

	public void setDocumentTypeMaster(DocumentTypeMaster documentTypeMaster) {
		this.documentTypeMaster = documentTypeMaster;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
}
