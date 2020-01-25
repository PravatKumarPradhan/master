/* package com.sdgt.medcare.master.entity.global;

import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="m_referral")
@Entity
public class ReferralMaster extends BaseMaster {
    
    @Column(length = 50)
    private String referralMasterCode;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "",referencedColumnName = "id",insertable = false)
    private List<CityMaster> cityMaster =new ArrayList<>();

    public ReferralMaster() {
    }

    public String getReferralMasterCode() {
        return referralMasterCode;
    }

    public void setReferralMasterCode(String referralMasterCode) {
        this.referralMasterCode = referralMasterCode;
    }

    public List<CityMaster> getCityMaster() {
        return cityMaster;
    }

    public void setCityMaster(List<CityMaster> cityMaster) {
        this.cityMaster = cityMaster;
    }
}
*/
