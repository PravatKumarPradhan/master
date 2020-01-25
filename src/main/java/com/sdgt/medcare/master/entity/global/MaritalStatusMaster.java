package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
@Table(name="m_martial_status")
public class MaritalStatusMaster extends BaseMaster {
    @Column(name="prefix_id",unique = true,length = 20)
    @MastersFieldCustomAnnotation(visibleToUser = false,editableByUser = false,sequence = 3)
    private Integer prefixId;
    public Integer getPrefixId() {
        return prefixId;
    }
    public void setPrefixId(Integer prefixId) {

        this.prefixId = prefixId;
    }
    public MaritalStatusMaster() {
    }

    public MaritalStatusMaster(Integer prefixId) {

        this.prefixId = prefixId;
    }

}
