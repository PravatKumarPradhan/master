package com.sdgt.medcare.master.entity.ambulance;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.bmw.ServiceProviderMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "m_vehicle", schema = "ambulance")
public class VehicleMaster  extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="org_id")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

    @Column(name = "vehicle_no")
    private String vehicleNo;

    @Column(name = "vehicle_code")
    private String vehicleCode;

    @Column(name = "vehicle_name")
    private String vehicleName ;

    @Column(name = "registration_no")
    private String registrationNo;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "vehicle_color")
    private String vehicleColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_type_id")
    private VehicleTypeMaster vehicleTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_provider_id")
    private ServiceProviderMaster serviceProviderMaster;

    @Column(name = "is_ambulance")
    private Boolean isAmbulance;

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

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public ServiceProviderMaster getServiceProviderMaster() {
        return serviceProviderMaster;
    }

    public void setServiceProviderMaster(ServiceProviderMaster serviceProviderMaster) {
        this.serviceProviderMaster = serviceProviderMaster;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public VehicleTypeMaster getVehicleTypeMaster() {
        return vehicleTypeMaster;
    }

    public Boolean getAmbulance() {
        return isAmbulance;
    }

    public void setVehicleTypeMaster(VehicleTypeMaster vehicleTypeMaster) {
        this.vehicleTypeMaster = vehicleTypeMaster;
    }

    public void setAmbulance(Boolean ambulance) {
        isAmbulance = ambulance;
    }
}
