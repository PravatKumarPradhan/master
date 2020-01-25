package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "m_age_group_master", schema = "lab")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
@SequenceGenerator(name = "m_seq_age_group_master", sequenceName = "lab.m_seq_age_group_master", allocationSize = 1)
public class AgeGroupMaster extends BaseMaster{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="organization_id")
	@MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="age_from_duration_master_id")
	@MastersFieldCustomAnnotation(displayName = "From Duration Type",sequence = 3,nullable = false)
	private DurationTypeMaster durationTypeMasterFrom;

	@Column(name = "age_from_Duration")
	@MastersFieldCustomAnnotation(displayName = "From Duration",sequence = 4,nullable = false)
	private Float fromDuration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="age_to_duration_master_id")
	@MastersFieldCustomAnnotation(displayName = "To Duration Type",sequence = 5,nullable = false)
	private DurationTypeMaster durationTypeMasterTo;

	@Column(name = "age_to_Duration")
	@MastersFieldCustomAnnotation(displayName = "To Duration",sequence = 6,nullable = false)
	private Float toDuration;

	@Column(name = "age_group_from")
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 7,editableByUser = false)
	private Integer ageGroupFrom;

	@Column(name = "age_from_days")
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 8,editableByUser = false)
	private Integer ageFromDays;

	@Column(name = "age_group_to")
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 9,editableByUser = false)
	private Integer ageGroupTo;

	@Column(name = "age_to_days")
	@MastersFieldCustomAnnotation(visibleToUser = false,sequence = 10,editableByUser = false)
	private Integer ageToDays;

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public DurationTypeMaster getDurationTypeMasterFrom() {
		return durationTypeMasterFrom;
	}

	public void setDurationTypeMasterFrom(DurationTypeMaster durationTypeMasterFrom) {
		this.durationTypeMasterFrom = durationTypeMasterFrom;
	}

	public Integer getAgeGroupFrom() {
		return ageGroupFrom;
	}

	public void setAgeGroupFrom(Integer ageGroupFrom) {
		this.ageGroupFrom = ageGroupFrom;
	}

	public Integer getAgeFromDays() {
		return ageFromDays;
	}

	public void setAgeFromDays(Integer ageFromDays) {
		this.ageFromDays = ageFromDays;
	}

	public DurationTypeMaster getDurationTypeMasterTo() {
		return durationTypeMasterTo;
	}

	public void setDurationTypeMasterTo(DurationTypeMaster durationTypeMasterTo) {
		this.durationTypeMasterTo = durationTypeMasterTo;
	}

	public Integer getAgeGroupTo() {
		return ageGroupTo;
	}

	public void setAgeGroupTo(Integer ageGroupTo) {
		this.ageGroupTo = ageGroupTo;
	}

	public Integer getAgeToDays() {
		return ageToDays;
	}

	public void setAgeToDays(Integer ageToDays) {
		this.ageToDays = ageToDays;
	}

	public Float getFromDuration() {
		return fromDuration;
	}

	public void setFromDuration(Float fromDuration) {
		this.fromDuration = fromDuration;
	}

	public Float getToDuration() {
		return toDuration;
	}

	public void setToDuration(Float toDuration) {
		this.toDuration = toDuration;
	}

}
