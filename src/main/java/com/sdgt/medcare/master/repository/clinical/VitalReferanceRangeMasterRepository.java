package com.sdgt.medcare.master.repository.clinical;

import com.sdgt.medcare.master.entity.clinical.VitalReferanceRangeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VitalReferanceRangeMasterRepository extends JpaRepository<VitalReferanceRangeMaster,Long> {

    @Query("SELECT DISTINCT vm FROM VitalMaster vm " +
            "LEFT JOIN FETCH vm.childVitalMasters cvm " +
            "LEFT JOIN FETCH vm.vitalReferanceRangeMasters vrrm " +
            "LEFT JOIN FETCH vm.qulifierVitalMappers qvm " +
            "LEFT JOIN FETCH vrrm.ageGroupMaster agm " +
            "LEFT JOIN FETCH vrrm.genderMaster gm " +
            "LEFT JOIN FETCH cvm.vitalReferanceRangeMasters cvrrm " +
            "LEFT JOIN FETCH cvrrm.ageGroupMaster cagm " +
            "LEFT JOIN FETCH cvrrm.genderMaster cgm " +
            "WHERE vm.speciality = :speciality " +
            "and vm.parentVitalMaster IS NULL " +
            "and ((gm.id= :genderId " +
            "and vrrm.vitalUnitMaster = vm.defaultUnit " +
            "and agm.ageFromDays <= :ageInDays and agm.ageToDays >= :ageInDays) " +
            "or (cgm.id= :genderId " +
            "and cvrrm.vitalUnitMaster = cvm.defaultUnit " +
            "and cagm.ageFromDays <= :ageInDays and cagm.ageToDays >= :ageInDays)) "
    )
    List<VitalReferanceRangeMaster> findAllVitals(
            @Param("speciality") String speciality,
            @Param("genderId") Long genderId
            ,@Param("ageInDays") Integer ageInDays
    );
}
