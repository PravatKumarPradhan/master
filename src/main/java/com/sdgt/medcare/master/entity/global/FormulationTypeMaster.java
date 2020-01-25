package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "m_formulation_type", schema = "inventory")
@SequenceGenerator(name = "formulation_type_seq", sequenceName = "inventory.formulation_type_seq", allocationSize = 1)
@DynamicUpdate
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class FormulationTypeMaster extends BaseMaster {

    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_master_id")
    private OrganizationMaster organizationMaster;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulationTypeMaster")
    private List<FormulationRoaMaster> formulationRoaMasterList;
*/
    public FormulationTypeMaster() {
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

 /*   public List<FormulationRoaMaster> getFormulationRoaMasterList() {
        return formulationRoaMasterList;
    }

    public void setFormulationRoaMasterList(List<FormulationRoaMaster> formulationRoaMasterList) {
        this.formulationRoaMasterList = formulationRoaMasterList;
    }*/

}
