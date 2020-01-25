/*
package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="m_tariff")
public class TariffMaster extends BaseMaster {

	@Column(name = "validity_from")
	@Temporal(TemporalType.TIMESTAMP)
	private Date validityFrom;
	
	@Column(name = "validity_to")
	@Temporal(TemporalType.TIMESTAMP)
	private Date validityTo;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<UnitMaster> listUnitMaster;
		
	@ManyToMany(fetch = FetchType.LAZY)
	private List<TariffServiceMaster> listTariffServiceMaster;// = new ArrayList<>();

	public Date getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(Date validityFrom) {
		this.validityFrom = validityFrom;
	}

	public Date getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(Date validityTo) {
		this.validityTo = validityTo;
	}	

	public List<UnitMaster> getListUnitMaster() {
		return listUnitMaster;
	}

	public void setListUnitMaster(List<UnitMaster> listUnitMaster) {
		this.listUnitMaster = listUnitMaster;
	}

	public List<TariffServiceMaster> getListTariffServiceMaster() {
		return listTariffServiceMaster;
	}

	public void setListTariffServiceMaster(List<TariffServiceMaster> listTariffServiceMaster) {
		this.listTariffServiceMaster = listTariffServiceMaster;
	}
	
	
	
}
*/
