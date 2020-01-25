package com.sdgt.medcare.master.repository.lab;

import com.sdgt.medcare.master.entity.lab.TestMaster;
import com.sdgt.medcare.master.entity.lab.UnitTestMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TestMasterRepository extends JpaRepository<TestMaster, Long> , JpaSpecificationExecutor<TestMaster> {

    @Query("SELECT DISTINCT utm FROM UnitTestMapper utm " +
            "JOIN FETCH utm.testMaster as tm " +
            "JOIN FETCH utm.unitMaster as um " +
            "JOIN FETCH tm.serviceMaster as sm " +
            "WHERE sm.id= :ServiceId and um.id= :unitId "
    )
    List<UnitTestMapper> findTest(
            @Param("unitId") Long unitId
            ,@Param("ServiceId") Long testId
    );

    @Query("SELECT DISTINCT tm FROM TestMaster tm " +
            "JOIN FETCH tm.serviceMaster as sm " +
            "WHERE sm.code= :ServiceCode "
    )
    List<TestMaster> findTestByServiceId(@Param("ServiceCode") String serviceCode
    );

}
