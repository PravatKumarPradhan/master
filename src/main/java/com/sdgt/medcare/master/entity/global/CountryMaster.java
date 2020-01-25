package com.sdgt.medcare.master.entity.global;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "m_country")
@MastersEntityCustomAnnotation (showOnFrontEnd = true)
@SequenceGenerator(name = "country_master_seq", sequenceName = "public.country_master_seq", allocationSize = 1)
public class CountryMaster extends BaseMaster {

    @Column(name="country_code")
    @MastersFieldCustomAnnotation(visibleToUser = false,sequence = 3,editableByUser = false)
    private String countryCode;

    @Column(name="country_calling_code",length = 5)
    @MastersFieldCustomAnnotation(displayName = "Country Calling Code",sequence = 4)
    private String countryCallingCode;

    @Column(name="country_initial_code",length = 10)
    @MastersFieldCustomAnnotation(displayName = "Country Initial Code",sequence = 5)
    private String countryInitialCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "countryMaster",cascade = CascadeType.ALL)
    private List<StateMaster> listStateMaster;
    public List<StateMaster> getListStateMaster() {
        return listStateMaster;
    }
    public void setListStateMaster(List<StateMaster> listStateMaster) {
        this.listStateMaster = listStateMaster;
    }

    public String getCountryCallingCode() {
        return countryCallingCode;
    }

    public void setCountryCallingCode(String countryCallingCode) {
        this.countryCallingCode = countryCallingCode;
    }

    public String getCountryInitialCode() {
        return countryInitialCode;
    }

    public void setCountryInitialCode(String countryInitialCode) {
        this.countryInitialCode = countryInitialCode;
    }


}
