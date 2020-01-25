package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_route_of_administration", schema = "inventory")
@SequenceGenerator(name = "route_of_administration_seq", sequenceName = "inventory.route_of_administration_seq", allocationSize = 1)
@DynamicUpdate
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class RouteOfAdministrationMaster extends BaseMaster {


   @MastersFieldCustomAnnotation (displayName = "Organisation",sequence = 0,nullable = false)
   @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

   @MastersFieldCustomAnnotation (displayName = "Alias",sequence = 7,visibleToUser = false)
    @Column(name = "alias")
    private String alias;



    public RouteOfAdministrationMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


}
