package com.sdgt.medcare.master.entity.unit;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "m_screen_status_mapper", schema = "inventory")
@SequenceGenerator(name = "screen_status_mapper_seq", sequenceName = "inventory.screen_status_mapper_seq", allocationSize = 1)
public class ScreenMasterMapper {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screen_id",unique = true)
	private DocumentMaster documentMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id",unique = true)
	private StatusMaster statusMaster;

	@Column(name = "status")
	private Character status= 'A';

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	@Column(name = "added_by")
	private String addedBy;

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date updatedDate;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DocumentMaster getDocumentMaster() {
		return documentMaster;
	}

	public void setDocumentMaster(DocumentMaster documentMaster) {
		this.documentMaster = documentMaster;
	}

	public StatusMaster getStatusMaster() {
		return statusMaster;
	}

	public void setStatusMaster(StatusMaster statusMaster) {
		this.statusMaster = statusMaster;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	@PreUpdate
	void updatedAt() {
		this.updatedDate = new Date();
	}


}
