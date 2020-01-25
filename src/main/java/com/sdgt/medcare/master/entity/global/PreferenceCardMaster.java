package com.sdgt.medcare.master.entity.global;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="m_preference_card",schema = "otims")
public class PreferenceCardMaster extends BaseMaster {

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="org_id")
    private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	private UnitMaster unitMaster;

	@Column(name="Card_against")
	private String CardAgainst;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentMaster department;

	@Column(name="doctor_id")
	private String doctorId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="procedure_Id")
    private ProcedureMaster procedureMaster;

	@JsonManagedReference
    @JsonDeserialize
    @OneToMany(mappedBy="preferenceCardMaster")
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "preference_Card_details_id")*/
	private List<PreferenceCardDetailsMaster> preferenceCardDetailsMaster =new ArrayList<PreferenceCardDetailsMaster>();

    @JsonDeserialize
	@JsonManagedReference
	@OneToOne(mappedBy = "preferenceCardMaster", cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
	//@JoinColumn(name="preference_Card_other_details_id")
	private PreferenceCardOtherDetailsMaster preferenceCardOtherDetailsMaster;


    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public DepartmentMaster getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentMaster department) {
		this.department = department;
	}

	public String getKartAgainst() {
		return CardAgainst;
	}

	public void setKartAgainst(String CardAgainst) {
		this.CardAgainst = CardAgainst;
	}

	public List<PreferenceCardDetailsMaster> getPreferenceCardDetailsMaster() {
		return preferenceCardDetailsMaster;
	}

	public void setPreferenceCardDetailsMaster(List<PreferenceCardDetailsMaster> preferenceCardDetailsMaster) {
		this.preferenceCardDetailsMaster = preferenceCardDetailsMaster;
	}

	public PreferenceCardOtherDetailsMaster getPreferenceCardOtherDetailsMaster() {
		return preferenceCardOtherDetailsMaster;
	}

	public void setPreferenceCardOtherDetailsMaster(PreferenceCardOtherDetailsMaster preferenceCardOtherDetailsMaster) {
		this.preferenceCardOtherDetailsMaster = preferenceCardOtherDetailsMaster;
	}

	public ProcedureMaster getProcedureMaster() {
		return procedureMaster;
	}

	public void setProcedureMaster(ProcedureMaster procedureMaster) {
		this.procedureMaster = procedureMaster;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
}
