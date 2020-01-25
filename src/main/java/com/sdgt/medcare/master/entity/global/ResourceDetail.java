/*
 * package com.sdgt.medcare.master.entity.global;
 *
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.ManyToOne; import javax.persistence.Table;
 *
 * import com.fasterxml.jackson.annotation.JsonBackReference; import
 * com.sdgt.medcare.master.entity.BaseMaster;
 *
 * @Entity
 *
 * @Table(name="m_resource_details") public class ResourceDetail extends
 * BaseMaster {
 *
 * @Column private String resourceType;
 *
 * @Column private Long resourceNumber;
 *
 * @Column private String resourceName;
 *
 *
 *
 * @ManyToOne
 *
 * @JsonBackReference // @JoinColumn(name="procedureMaster_id") private
 * ProcedureMaster procedureMaster;
 *
 * private Long procMasterId;
 *
 * public Long getProcMasterId() { return procMasterId; }
 *
 *
 * public void setProcMasterId(Long procMasterId) { this.procMasterId =
 * procMasterId; }
 *
 *
 * public String getResourceType() { return resourceType; }
 *
 *
 * public void setResourceType(String resourceType) { this.resourceType =
 * resourceType; }
 *
 *
 * public Long getResourceNumber() { return resourceNumber; }
 *
 *
 * public void setResourceNumber(Long resourceNumber) { this.resourceNumber =
 * resourceNumber; }
 *
 *
 * public String getResourceName() { return resourceName; }
 *
 *
 * public void setResourceName(String resourceName) { this.resourceName =
 * resourceName; }
 *
 *
 * public ProcedureMaster getProcedureMaster() { return procedureMaster; }
 *
 *
 * public void setProcedureMaster(ProcedureMaster procedureMaster) {
 * this.procedureMaster = procedureMaster; }
 *
 *
 *
 * public ProcedureResourceConfigurationMaster
 * getProcedureResourceConfigurationMaster() { return
 * procedureResourceConfigurationMaster; }
 *
 *
 * public void setProcedureResourceConfigurationMaster(
 * ProcedureResourceConfigurationMaster procedureResourceConfigurationMaster) {
 * this.procedureResourceConfigurationMaster =
 * procedureResourceConfigurationMaster; }
 *
 *
 *
 *
 *
 * }
 */









package com.sdgt.medcare.master.entity.global;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_resource_details")
public  class ResourceDetail extends BaseMaster {
    @Column
    private String resourceType;

    @Column
    private Long numberOfResource;

    @Column
    private String  resourceName;

    /*
     * @ManyToOne(fetch = FetchType.LAZY)
     *
     * @JoinColumn(name = "procedureResourceConfigurationMaster_id") private
     * ProcedureResourceConfigurationMaster procedureResourceConfigurationMaster;
     */


    @ManyToOne
    @JsonBackReference
    private ProcedureMaster procedureMaster;

    public String getResourceType() {
	return resourceType;
    }


    public void setResourceType(String resourceType) {
	this.resourceType = resourceType;
    }

    public Long getNumberOfResource() {
	return numberOfResource;
    }


    public void setNumberOfResource(Long numberOfResource) {
	this.numberOfResource = numberOfResource;
    }


    public String getResourceName() {
	return resourceName;
    }


    public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
    }


    public ProcedureMaster getProcedureMaster() {
	return procedureMaster;
    }


    public void setProcedureMaster(ProcedureMaster procedureMaster) {
	this.procedureMaster = procedureMaster;
    }


    /*
     * public ProcedureResourceConfigurationMaster
     * getProcedureResourceConfigurationMaster() { return
     * procedureResourceConfigurationMaster; }
     *
     *
     * public void setProcedureResourceConfigurationMaster(
     * ProcedureResourceConfigurationMaster procedureResourceConfigurationMaster) {
     * this.procedureResourceConfigurationMaster =
     * procedureResourceConfigurationMaster; }
     */




}
