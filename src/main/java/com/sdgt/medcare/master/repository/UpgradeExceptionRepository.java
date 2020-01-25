package com.sdgt.medcare.master.repository;


import com.sdgt.medcare.master.entity.billing.UpgradeExceptionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UpgradeExceptionRepository extends JpaRepository<UpgradeExceptionMaster, Long> {
    @Query("select t from UpgradeExceptionMaster t where upgradeType=:upgradeType AND subGroupMasterId=:subGroupMasterId AND unitId=:unitId AND orgId=:orgId")
    UpgradeExceptionMaster checkUpgradeExceptionFinancialClass(@Param("upgradeType") String upgradeType,@Param("subGroupMasterId") String subGroupMasterId,@Param("unitId") String unitId,@Param("orgId") String orgId);
}
