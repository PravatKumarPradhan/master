package com.sdgt.medcare.master.entity.lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="m_test_parameter_mapper",schema = "lab")
public class TestParameterMapper extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="test_master_id")
    private TestMaster testMaster;

    @JsonIgnoreProperties("organizationMaster")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parameter_master_id")
    private ParameterMaster parameterMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="header_master_id")
    private HeaderMaster headerMaster;

    @Column(name = "parameter_sequence")
    private Integer parameter_sequence;

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

    public ParameterMaster getParameterMaster() {
        return parameterMaster;
    }

    public void setParameterMaster(ParameterMaster parameterMaster) {
        this.parameterMaster = parameterMaster;
    }

    public HeaderMaster getHeaderMaster() {
        return headerMaster;
    }

    public void setHeaderMaster(HeaderMaster headerMaster) {
        this.headerMaster = headerMaster;
    }

    public Integer getParameter_sequence() {
        return parameter_sequence;
    }

    public void setParameter_sequence(Integer parameter_sequence) {
        this.parameter_sequence = parameter_sequence;
    }
}
