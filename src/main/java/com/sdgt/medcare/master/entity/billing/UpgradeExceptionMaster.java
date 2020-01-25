package com.sdgt.medcare.master.entity.billing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table (name = "m_upgrade_exceptions", schema = "service")
public class UpgradeExceptionMaster extends BaseMaster {

   @Column (name = "upgrade_type")
   private String upgradeType;

   @Column (name = "sub_group_master_id")
   private String subGroupMasterId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_master_id")
    private UnitMaster unitMaster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_master_id")
    private OrganizationMaster organizationMaster;


}
