package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "m_service_unit",schema = "service")
public class ServiceUnitMaster extends BaseMaster {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_master_id")
	private ServiceMaster serviceMaster;

	@Column(name = "is_outsource")
	private char isOutsource = 'F';

	@Column(name = "is_package")
	private char isPackage = 'F';

	@Column(name = "is_rate_editable")
	private char isRateEditable = 'F';

	@Column(name = "rate_editable_min")
	private double rateEditableMin=0;

	@Column(name = "rate_editable_max")
	private double rateEditableMax=0;

	@Column(name = "is_discount_allowed")
	private char isDiscountAllowed = 'F';

	@Column(name = "is_discount_min")
	private char isDiscountMin = 'F';

	@Column(name = "is_discount_max")
	private char isDiscountMax = 'F';

	@Column(name = "tax_applicable")
	private char taxApplicable = 'F';

	@Column(name = "is_procedure")
	private char isProcedure = 'F';

	@Column(name = "is_allow_multiple_qty")
	private char isAllowMultipleQty = 'F';

	@Column(name = "is_doctor_required")
	private char isDoctorRequired = 'F';

	@Column(name = "is_auto_render")
	private char isAutoRender = 'F';

	@Column(name = "is_panel")
	private char isPanel = 'F';

	@Column(name = "is_periodicity")
	private char isPeriodicity = 'F';

	@Column(name = "is_poc")
	private char isPOC = 'F';

	@Column(name = "is_appointable")
	private char isAppointable = 'F';

	@Column(name = "is_referral_doctor")
	private Character isReferralDoctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tax_master_id")
	private TaxMaster taxMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_master_id")
	private OrganizationMaster organizationMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_master_id")
	private UnitMaster unitMaster;

	@OneToMany(fetch = FetchType.LAZY)
	private List<TariffServiceMaster> listTariffServiceMaster;


	public char getIsOutsource() {
		return isOutsource;
	}

	public void setIsOutsource(char isOutsource) {
		this.isOutsource = isOutsource;
	}

	public char getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(char isPackage) {
		this.isPackage = isPackage;
	}

	public char getIsRateEditable() {
		return isRateEditable;
	}

	public void setIsRateEditable(char isRateEditable) {
		this.isRateEditable = isRateEditable;
	}

	public double getRateEditableMin() {
		return rateEditableMin;
	}

	public void setRateEditableMin(double rateEditableMin) {
		this.rateEditableMin = rateEditableMin;
	}

	public double getRateEditableMax() {
		return rateEditableMax;
	}

	public void setRateEditableMax(double rateEditableMax) {
		this.rateEditableMax = rateEditableMax;
	}

	public char getIsDiscountAllowed() {
		return isDiscountAllowed;
	}

	public void setIsDiscountAllowed(char isDiscountAllowed) {
		this.isDiscountAllowed = isDiscountAllowed;
	}

	public char getIsDiscountMin() {
		return isDiscountMin;
	}

	public void setIsDiscountMin(char isDiscountMin) {
		this.isDiscountMin = isDiscountMin;
	}

	public char getIsDiscountMax() {
		return isDiscountMax;
	}

	public void setIsDiscountMax(char isDiscountMax) {
		this.isDiscountMax = isDiscountMax;
	}

	public char getTaxApplicable() {
		return taxApplicable;
	}

	public void setTaxApplicable(char taxApplicable) {
		this.taxApplicable = taxApplicable;
	}

	public char getIsProcedure() {
		return isProcedure;
	}

	public void setIsProcedure(char isProcedure) {
		this.isProcedure = isProcedure;
	}

	public char getIsAllowMultipleQty() {
		return isAllowMultipleQty;
	}

	public void setIsAllowMultipleQty(char isAllowMultipleQty) {
		this.isAllowMultipleQty = isAllowMultipleQty;
	}

	public char getIsDoctorRequired() {
		return isDoctorRequired;
	}

	public void setIsDoctorRequired(char isDoctorRequired) {
		this.isDoctorRequired = isDoctorRequired;
	}

	public char getIsAutoRender() {
		return isAutoRender;
	}

	public void setIsAutoRender(char isAutoRender) {
		this.isAutoRender = isAutoRender;
	}

	public char getIsPanel() {
		return isPanel;
	}

	public void setIsPanel(char isPanel) {
		this.isPanel = isPanel;
	}

	public char getIsPeriodicity() {
		return isPeriodicity;
	}

	public void setIsPeriodicity(char isPeriodicity) {
		this.isPeriodicity = isPeriodicity;
	}

	public char getIsPOC() {
		return isPOC;
	}

	public void setIsPOC(char isPOC) {
		this.isPOC = isPOC;
	}

	public char getIsAppointable() {
		return isAppointable;
	}

	public void setIsAppointable(char isAppointable) {
		this.isAppointable = isAppointable;
	}

	public Character getIsReferralDoctor() {
		return isReferralDoctor;
	}

	public void setIsReferralDoctor(Character isReferralDoctor) {
		this.isReferralDoctor = isReferralDoctor;
	}

	public TaxMaster getTaxMaster() {
		return taxMaster;
	}

	public void setTaxMaster(TaxMaster taxMaster) {
		this.taxMaster = taxMaster;
	}

	public ServiceMaster getServiceMaster() {
		return serviceMaster;
	}

	public void setServiceMaster(ServiceMaster serviceMaster) {
		this.serviceMaster = serviceMaster;
	}

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

	public List<TariffServiceMaster> getListTariffServiceMaster() {
		return listTariffServiceMaster;
	}

	public void setListTariffServiceMaster(List<TariffServiceMaster> listTariffServiceMaster) {
		this.listTariffServiceMaster = listTariffServiceMaster;
	}



}
