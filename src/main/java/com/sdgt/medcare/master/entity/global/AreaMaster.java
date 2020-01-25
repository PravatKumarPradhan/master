package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="m_area")
@Entity
public class AreaMaster extends BaseMaster {

    @Column(name="post_code",unique = true,length = 20)
    @MastersFieldCustomAnnotation(displayName = "Postcode",sequence = 3)
    Integer postCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @MastersFieldCustomAnnotation(displayName = "City" , sequence = 4)
    private CityMaster cityMaster;

    public AreaMaster() {
    }

    public AreaMaster(Integer postCode, CityMaster cityMaster) {
        this.postCode = postCode;
        this.cityMaster = cityMaster;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public CityMaster getCityMaster() {
        return cityMaster;
    }

    public void setCityMaster(CityMaster cityMaster) {
        this.cityMaster = cityMaster;
    }
}
