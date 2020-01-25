package com.sdgt.medcare.master.entity.org;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.unit.BedMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name="m_bed_status",schema = "adt")
@Entity
public class BedStatus extends BaseMaster {

    @Column(name="bed_status_code",unique = true,length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String bedStatusCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 4,editableByUser = false)
    private OrganizationMaster organizationMaster;

    public BedStatus(String bedStatusCode) {
	this.bedStatusCode = bedStatusCode;
    }

    public String getBedStatusCode() {
	return bedStatusCode;
    }

    public void setBedStatusCode(String bedStatusCode) {
	this.bedStatusCode = bedStatusCode;
    }


    @OneToMany(mappedBy = "bedStatus", fetch = FetchType.LAZY)
    private List<BedMaster> bedMaster = new ArrayList<>();

    public BedStatus() {

    }
}
