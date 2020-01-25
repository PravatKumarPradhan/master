package com.sdgt.medcare.master.entity.global;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "m_state", schema = "public")
@MastersEntityCustomAnnotation  (showOnFrontEnd = true)
@SequenceGenerator(name = "state_master_seq", sequenceName = "public.state_master_seq", allocationSize = 1)
public class StateMaster extends BaseMaster {


    @Column(name="state_code",length = 50)
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
     private String stateCode;

    public StateMaster() {
    }

    public StateMaster(String stateCode, CountryMaster countryMaster) {
        this.stateCode = stateCode;
        this.countryMaster = countryMaster;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @MastersFieldCustomAnnotation(displayName = "Country",sequence = 4,nullable = false)
    private CountryMaster countryMaster;
     @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "stateMaster",cascade = CascadeType.ALL)
    private List<CityMaster> cityMasters;

    public List<CityMaster> getCityMasters() {
        return cityMasters;
    }

    public void setCityMasters(List<CityMaster> cityMasters) {
        this.cityMasters = cityMasters;
    }

    public CountryMaster getCountryMaster() {
        return countryMaster;
    }

    public void setCountryMaster(CountryMaster countryMaster) {

        this.countryMaster = countryMaster;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

}
