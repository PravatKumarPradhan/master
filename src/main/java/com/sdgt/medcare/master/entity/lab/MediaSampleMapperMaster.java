package com.sdgt.medcare.master.entity.lab;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_media_sample_mapper", schema = "lab")
public class MediaSampleMapperMaster extends BaseMaster {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private MediaMaster mediaMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_type_id")
    private SampleTypeMaster sampleTypeMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Org_ID")
    private OrganizationMaster organizationMaster;

    public MediaMaster getMediaMaster() {
        return mediaMaster;
    }

    public void setMediaMaster(MediaMaster mediaMaster) {
        this.mediaMaster = mediaMaster;
    }

    public SampleTypeMaster getSampleTypeMaster() {
        return sampleTypeMaster;
    }

    public void setSampleTypeMaster(SampleTypeMaster sampleTypeMaster) {
        this.sampleTypeMaster = sampleTypeMaster;
    }

    public OrganizationMaster getOrganizationMaster() {
        return organizationMaster;
    }

    public void setOrganizationMaster(OrganizationMaster organizationMaster) {
        this.organizationMaster = organizationMaster;
    }
}
