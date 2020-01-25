package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.FinancialClassMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.TariffMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "m_tariffservice",schema="service")
public class TariffServiceMaster extends BaseMaster {


/*	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tariffmaster_tariffservicemaster", joinColumns = @JoinColumn(name = "TariffMaster_id"), inverseJoinColumns = @JoinColumn(name = "TariffServiceMaster_id"))
	private List<TariffMaster> listTariffMaster;*/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tariff_master_id")
	private TariffMaster tariffMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "financial_class_master_id")
	private FinancialClassMaster financialClassMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_master_id")
	private VisitTypeMaster visitTypeMaster;

	@Column(name = "effective_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveDate;

	@Column(name = "rate")
	private double rate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_unit_master_id")
	private ServiceUnitMaster serviceUnitMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_master_id")
	private OrganizationMaster organizationMaster;

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	/*public List<TariffMaster> getListTariffMaster() {
		return listTariffMaster;
	}

	public void setListTariffMaster(List<TariffMaster> listTariffMaster) {
		this.listTariffMaster = listTariffMaster;
	}

*/
	public FinancialClassMaster getFinancialClassMaster() {
		return financialClassMaster;
	}

	public void setFinancialClassMaster(FinancialClassMaster financialClassMaster) {
		this.financialClassMaster = financialClassMaster;
	}

	public VisitTypeMaster getVisitTypeMaster() {
		return visitTypeMaster;
	}

	public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		this.visitTypeMaster = visitTypeMaster;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public ServiceUnitMaster getServiceUnitMaster() {
		return serviceUnitMaster;
	}

	public void setServiceUnitMaster(ServiceUnitMaster serviceUnitMaster) {
		this.serviceUnitMaster = serviceUnitMaster;
	}

	public TariffMaster getTariffMaster() {
		return tariffMaster;
	}

	public void setTariffMaster(TariffMaster tariffMaster) {
		this.tariffMaster = tariffMaster;
	}




}
