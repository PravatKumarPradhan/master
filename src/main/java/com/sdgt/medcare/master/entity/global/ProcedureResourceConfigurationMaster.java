/*
 * package com.sdgt.medcare.master.entity.global;
 *
 * import java.util.List;
 *
 * import javax.persistence.CascadeType; import javax.persistence.Column; import
 * javax.persistence.Entity; import javax.persistence.FetchType; import
 * javax.persistence.JoinColumn; import javax.persistence.ManyToOne; import
 * javax.persistence.OneToMany; import javax.persistence.Table;
 *
 * import com.fasterxml.jackson.annotation.JsonIgnore; import
 * com.sdgt.medcare.master.entity.BaseMaster; import
 * com.sdgt.medcare.master.entity.org.OrganizationMaster; import
 * com.sdgt.medcare.master.entity.org.UnitMaster;
 *
 * @Entity
 *
 * @Table(name = "m_procedure_resource_configuration", schema = "otims") public
 * class ProcedureResourceConfigurationMaster extends BaseMaster {
 *
 * @ManyToOne(fetch = FetchType.LAZY)
 *
 * @JoinColumn(name = "org_id") private OrganizationMaster organizationMaster;
 *
 * @JsonIgnore
 *
 * @Column(name = "org_code") private String organizationCode;
 *
 * @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 *
 * @JoinColumn(name = "unit_id") private UnitMaster unitMaster;
 *
 * @JsonIgnore
 *
 * @Column(name = "unit_code") private String unitCode;
 *
 * @ManyToOne(fetch = FetchType.LAZY)
 *
 * @JoinColumn(name = "procedure_id") private ProcedureMaster procedure;
 *
 *
 * @CollectionTable(name="ResourceDetail") //
 * joinColumns=@JoinColumn(name="resource_id"))
 *
 * @ElementCollection
 *
 * @CollectionTable(name="m_resource_details")
 * //,joinColumns=@JoinColumn(name="id")) private List<ResourceDetail>
 * resourceDetails=new ArrayList<ResourceDetail>();
 *
 *
 *
 *
 * @OneToMany(fetch = FetchType.LAZY,cascade =
 * CascadeType.ALL,mappedBy="procedureResourceConfigurationMaster") private
 * List<ResourceDetail> resourceDetails;
 *
 * @JsonIgnore
 *
 * @Column(name = "procedure_code") private String procedureCode;
 *
 * @JsonIgnore
 *
 * @Column(name = "resource_type_id") private String resourceTypeId;
 *
 * @JsonIgnore
 *
 * @Column(name = "resource_type_code") private String resourceTypeCode;
 *
 * public String getResourceTypeCode() { return resourceTypeCode; }
 *
 * public void setResourceTypeCode(String resourceTypeCode) {
 * this.resourceTypeCode = resourceTypeCode; }
 *
 * @JsonIgnore
 *
 * @Column(name = "required_number") private String requiredNumber;
 *
 * public OrganizationMaster getOrganizationMaster() { return
 * organizationMaster; }
 *
 * public void setOrganizationMaster(OrganizationMaster organizationMaster) {
 * this.organizationMaster = organizationMaster; }
 *
 * public UnitMaster getUnitMaster() { return unitMaster; }
 *
 * public void setUnitMaster(UnitMaster unitMaster) { this.unitMaster =
 * unitMaster; }
 *
 * public ProcedureMaster getProcedure() { return procedure; }
 *
 * public void setProcedure(ProcedureMaster procedure) { this.procedure =
 * procedure; }
 *
 * public String getResourceTypeId() { return resourceTypeId; }
 *
 * public void setResourceTypeId(String resourceTypeId) { this.resourceTypeId =
 * resourceTypeId; }
 *
 * public String getRequiredNumber() { return requiredNumber; }
 *
 * public void setRequiredNumber(String requiredNumber) { this.requiredNumber =
 * requiredNumber; }
 *
 * public String getUnitCode() { return unitCode; }
 *
 * public void setUnitCode(String unitCode) { this.unitCode = unitCode; }
 *
 * public String getProcedureCode() { return procedureCode; }
 *
 * public void setProcedureCode(String procedureCode) { this.procedureCode =
 * procedureCode; }
 *
 * public String getOrganizationCode() { return organizationCode; }
 *
 * public void setOrganizationCode(String organizationCode) {
 * this.organizationCode = organizationCode; }
 *
 *
 * public List<ResourceDetail> getResourceDetails() { return resourceDetails; }
 *
 * public void setResourceDetails(List<ResourceDetail> resourceDetails) {
 * this.resourceDetails = resourceDetails; }
 *
 *
 *
 *
 * public ProcedureResourceConfigurationMaster() { // TODO Auto-generated
 * constructor stub }
 *
 *
 * public List<ResourceDetail> getResourceDetails() { return resourceDetails; }
 *
 * public void setResourceDetails(List<ResourceDetail> resourceDetails) {
 * this.resourceDetails = resourceDetails; }
 *
 *
 *
 * }
 */






package com.sdgt.medcare.master.entity.global;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_procedure_resource_configuration", schema = "otims")
public class ProcedureResourceConfigurationMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private OrganizationMaster organizationMaster;

    @JsonIgnore
    @Column(name = "org_code")
    private String organizationCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id")
    private UnitMaster unitMaster;
    @JsonIgnore
    @Column(name = "unit_code")
    private String unitCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_id")
    private ProcedureMaster procedure;

    /*
     * @CollectionTable(name="ResourceDetail") //
     * joinColumns=@JoinColumn(name="resource_id"))
     *
     * @ElementCollection
     *
     * @CollectionTable(name="m_resource_details")
     * //,joinColumns=@JoinColumn(name="id")) private List<ResourceDetail>
     * resourceDetails=new ArrayList<ResourceDetail>();
     */


    /*
     * @OneToMany(fetch = FetchType.LAZY,cascade =
     * CascadeType.ALL,mappedBy="procedureResourceConfigurationMaster") private
     * List<ResourceDetail> resourceDetails;
     */
    @JsonIgnore
    @Column(name = "procedure_code")
    private String procedureCode;
    @JsonIgnore
    @Column(name = "resource_type_id")
    private String resourceTypeId;
    @JsonIgnore
    @Column(name = "resource_type_code")
    private String resourceTypeCode;

    public String getResourceTypeCode() {
	return resourceTypeCode;
    }

    public void setResourceTypeCode(String resourceTypeCode) {
	this.resourceTypeCode = resourceTypeCode;
    }
    @JsonIgnore
    @Column(name = "required_number")
    private String requiredNumber;

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

    public ProcedureMaster getProcedure() {
	return procedure;
    }

    public void setProcedure(ProcedureMaster procedure) {
	this.procedure = procedure;
    }

    public String getResourceTypeId() {
	return resourceTypeId;
    }

    public void setResourceTypeId(String resourceTypeId) {
	this.resourceTypeId = resourceTypeId;
    }

    public String getRequiredNumber() {
	return requiredNumber;
    }

    public void setRequiredNumber(String requiredNumber) {
	this.requiredNumber = requiredNumber;
    }

    public String getUnitCode() {
	return unitCode;
    }

    public void setUnitCode(String unitCode) {
	this.unitCode = unitCode;
    }

    public String getProcedureCode() {
	return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
	this.procedureCode = procedureCode;
    }

    public String getOrganizationCode() {
	return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
	this.organizationCode = organizationCode;
    }

    /*
     * public List<ResourceDetail> getResourceDetails() { return resourceDetails; }
     *
     * public void setResourceDetails(List<ResourceDetail> resourceDetails) {
     * this.resourceDetails = resourceDetails; }
     */



    public ProcedureResourceConfigurationMaster() {
	// TODO Auto-generated constructor stub
    }

    /*
     * public List<ResourceDetail> getResourceDetails() { return resourceDetails; }
     *
     * public void setResourceDetails(List<ResourceDetail> resourceDetails) {
     * this.resourceDetails = resourceDetails; }
     */


}
