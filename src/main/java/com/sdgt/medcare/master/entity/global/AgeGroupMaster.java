/*
package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "m_age_group_master", schema = "lab")
public class AgeGroupMaster extends BaseMaster
{


	@Column(name = "age_group_to")
	private String ageGroupTo;

	@Column(name = "age_type_grpfrom_id")
	private String ageTypeGrpfromId;
	
	@Column(name = "age_type_grpto_id")
	private String ageTypeGrptoId;
	
	@Column(name = "age_type_grp_name")
	private String ageTypeGrpName;
	
	@Column(name = "age_from_day")
	private String ageFromDay;
	
	@Column(name = "age_to_day")
	private String ageToday;
	

	@Column(name = "age_group_from")
	private String ageGroupFrom;	
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<ParamRefrengMaster> listParamRefrengMaster;


	public String getAgeGroupTo() {
		return ageGroupTo;
	}


	public void setAgeGroupTo(String ageGroupTo) {
		this.ageGroupTo = ageGroupTo;
	}


	public String getAgeTypeGrpfromId() {
		return ageTypeGrpfromId;
	}


	public void setAgeTypeGrpfromId(String ageTypeGrpfromId) {
		this.ageTypeGrpfromId = ageTypeGrpfromId;
	}


	public String getAgeTypeGrptoId() {
		return ageTypeGrptoId;
	}


	public void setAgeTypeGrptoId(String ageTypeGrptoId) {
		this.ageTypeGrptoId = ageTypeGrptoId;
	}


	public String getAgeTypeGrpName() {
		return ageTypeGrpName;
	}


	public void setAgeTypeGrpName(String ageTypeGrpName) {
		this.ageTypeGrpName = ageTypeGrpName;
	}


	public String getAgeFromDay() {
		return ageFromDay;
	}


	public void setAgeFromDay(String ageFromDay) {
		this.ageFromDay = ageFromDay;
	}


	public String getAgeToday() {
		return ageToday;
	}


	public void setAgeToday(String ageToday) {
		this.ageToday = ageToday;
	}


	public String getAgeGroupFrom() {
		return ageGroupFrom;
	}


	public void setAgeGroupFrom(String ageGroupFrom) {
		this.ageGroupFrom = ageGroupFrom;
	}


	public List<ParamRefrengMaster> getListParamRefrengMaster() {
		return listParamRefrengMaster;
	}


	public void setListParamRefrengMaster(List<ParamRefrengMaster> listParamRefrengMaster) {
		this.listParamRefrengMaster = listParamRefrengMaster;
	}

	
	
	

}
*/
