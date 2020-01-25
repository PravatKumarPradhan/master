package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_resource")
public class ResourceMaster extends BaseMaster {

    @Column
    private String resourceType;


    /*
     * @OneToMany(mappedBy = "resourceMaster") private
     * List<ProcedureResourceConfigurationMaster> procedureConfig;
     */

    public ResourceMaster() {
	super();
    }

    /*
     * public List<ProcedureResourceConfigurationMaster> getProcedureConfig() {
     * return procedureConfig; }
     *
     * public void setProcedureConfig(List<ProcedureResourceConfigurationMaster>
     * procedureConfig) { this.procedureConfig = procedureConfig; }
     */

    private Long resourceNumber;


    private String  resourceName;

    public String getResourceType() {
	return resourceType;
    }

    public void setResourceType(String resourceType) {
	this.resourceType = resourceType;
    }

    public Long getResourceNumber() {
	return resourceNumber;
    }

    public void setResourceNumber(Long resourceNumber) {
	this.resourceNumber = resourceNumber;
    }

    public String getResourceName() {
	return resourceName;
    }

    public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
    }

    public String getResourceCode() {
	return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
	this.resourceCode = resourceCode;
    }

    @Column(name="resource_code")
    private String resourceCode;



}
