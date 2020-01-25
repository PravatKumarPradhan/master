package com.sdgt.medcare.master.entity.lab;


import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_container",schema = "lab")
@MastersEntityCustomAnnotation(showOnFrontEnd = true)
public class ContainerMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(displayName = "Organisation",sequence = 0,nullable = false)
    private OrganizationMaster organizationMaster;

    /*@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="test_id")
    private TestMaster testMaster;*/

    @Column(name="color_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String colorCode;

    @Column(name="capacity")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
    private String capacity;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /*public TestMaster getTestMaster() {
        return testMaster;
    }

    public void setTestMaster(TestMaster testMaster) {
        this.testMaster = testMaster;
    }*/
}
