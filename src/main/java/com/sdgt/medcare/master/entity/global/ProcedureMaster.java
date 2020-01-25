package com.sdgt.medcare.master.entity.global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;

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

@Entity
@Table(name = "m_procedure", schema = "otims")
public class ProcedureMaster extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_id")
    private UnitMaster unitMaster;

   @Column(name = "code",length = 50)
   protected String code;


   @Column(name = "description")
   protected String desc;

    @Column(name="procedure_code")
    private String procedureCode;

    public UnitMaster getUnitMaster() {
	return unitMaster;
    }

    public void setUnitMaster(UnitMaster unitMaster) {
	this.unitMaster = unitMaster;
    }

    public UnitServiceMapper getUnitServiceMapper() {
	return unitServiceMapper;
    }

    public void setUnitServiceMapper(UnitServiceMapper unitServiceMapper) {
	this.unitServiceMapper = unitServiceMapper;
    }

    @Column(name="cpt_code")
    private String cptCode;

   /* @Column(name="icd_code")
    private String icdCode;*/

    @Column(name = "cpt_name")
    private String cptName;


    @JsonDeserialize
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties({"unitDepartmentMapper","subDepartmentMasters"})
    private DepartmentMaster department;

   @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anaesthesia_type_id")
    private AnaesthesiaTypeMaster anaesthesiaTypeMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_type_id")
    private OperationTypeMaster operationTypeMaster;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="theater_type_id")
    private TheatreTypeMaster theatreTypeMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_room_id")
    private  TheatreRoomMaster theatreRoomMaster;

    @Column(name = "duration")
    private String duration;

    @Column(name = "duration_unit")
    private String durationUnit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceMaster serviceMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_service_id")
    private UnitServiceMapper unitServiceMapper;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="procedure_type")
    private ProcedureTypeMaster procedureTypeMaster;



    @JsonManagedReference
    @JsonDeserialize
    @OneToMany(mappedBy="procedureMaster")
    private List<ResourceDetail> resourceDetails=new ArrayList<ResourceDetail>();


    public OrganizationMaster getOrgId() {
	return organizationMaster;
    }

    public void setOrgId(OrganizationMaster organizationMaster) {
	this.organizationMaster = organizationMaster;
    }

    public String getProcedureCode() {
	return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
	this.procedureCode = procedureCode;
    }

    public String getCptCode() {
	return cptCode;
    }

    public void setCptCode(String cptCode) {
	this.cptCode = cptCode;
    }

    /*public String getIcdCode() {
	return icdCode;
    }

    public void setIcdCode(String icdCode) {
	this.icdCode = icdCode;
    }*/

    public DepartmentMaster getDepartment() {
	return department;
    }

    public void setDepartment(DepartmentMaster departmentMaster) {
	this.department = departmentMaster;
    }

    public AnaesthesiaTypeMaster getAnaesthesiaTypeMaster() {
	return anaesthesiaTypeMaster;
    }
  public void setAnaesthesiaTypeMaster(AnaesthesiaTypeMaster anaesthesiaTypeMaster) {
	this.anaesthesiaTypeMaster = anaesthesiaTypeMaster;
    }
    public OperationTypeMaster getOperationTypeMaster() {
	return operationTypeMaster;
    }

    public void setOperationTypeMaster(OperationTypeMaster operationTypeMaster) {
	this.operationTypeMaster = operationTypeMaster;
    }

    public String getDuration() {
	return duration;
    }

    public void setDuration(String duration) {
	this.duration = duration;
    }

    public String getDurationUnit() {
	return durationUnit;
    }

    public void setDurationUnit(String durationUnit) {
	this.durationUnit = durationUnit;
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

   public ProcedureTypeMaster getProcedureTypeMaster() {
      return procedureTypeMaster;
   }

   public void setProcedureTypeMaster(ProcedureTypeMaster procedureTypeMaster) {
      this.procedureTypeMaster = procedureTypeMaster;
   }

   public List<ResourceDetail> getResourceDetails() {
	return resourceDetails;
    }

    public void setResourceDetails(List<ResourceDetail> resourceDetails) {
	this.resourceDetails = resourceDetails;
    }

   public TheatreRoomMaster getTheatreRoomMaster() {
      return theatreRoomMaster;
   }

   public void setTheatreRoomMaster(TheatreRoomMaster theatreRoomMaster) {
      this.theatreRoomMaster = theatreRoomMaster;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getDesc() {
      return desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public String getCptName() {
      return cptName;
   }

   public void setCptName(String cptName) {
      this.cptName = cptName;
   }

   public TheatreTypeMaster getTheatreTypeMaster() {
      return theatreTypeMaster;
   }

   public void setTheatreTypeMaster(TheatreTypeMaster theatreTypeMaster) {
      this.theatreTypeMaster = theatreTypeMaster;
   }
}
