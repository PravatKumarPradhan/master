package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_template_test_mapper", schema = "lab")
public class TemplateTestMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="test_master_id")
    private TestMaster testMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="template_master_id")
    private TemplateMaster templateMaster;

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }

    public TestMaster getTestMaster() {
        return testMaster;
    }

    public void setTestMaster(TestMaster testMaster) {
        this.testMaster = testMaster;
    }

    public TemplateMaster getTemplateMaster() {
        return templateMaster;
    }

    public void setTemplateMaster(TemplateMaster templateMaster) {
        this.templateMaster = templateMaster;
    }
}
