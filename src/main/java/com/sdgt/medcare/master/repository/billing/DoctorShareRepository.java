package com.sdgt.medcare.master.repository.billing;


import com.sdgt.medcare.master.entity.unit.DoctorShareMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface DoctorShareRepository extends JpaRepository<DoctorShareMaster,Long> {

    @Query("select  t from DoctorShareMaster t  where " +
            " ( (:servicecode IS NULL) or (t.serviceCode =:servicecode)) and ((:doctorId IS NULL) or (t.employeeMasterDetails.id =:doctorId))" +
            " and  ((:docDeptId IS NULL) or (t.departmentMaster.id =:docDeptId)) " +
            " and  ((:ServiceGroupId IS NULL) or (t.groupMaster.id =:ServiceGroupId)) " +
            " and  ((:ServiceSubGroupId IS NULL) or (t.subGroupMaster.id =:ServiceSubGroupId)) order by t.effectiveDate desc,t.id desc ")
    List<DoctorShareMaster> findDoctorShareByServiceCode(@Param("servicecode") String servicecode,
                                                   @Param("doctorId") Long doctorId, @Param("docDeptId") Long docDeptId,
                                                   @Param("ServiceGroupId") Long ServiceGroupId, @Param("ServiceSubGroupId") Long ServiceSubGroupId);

}
