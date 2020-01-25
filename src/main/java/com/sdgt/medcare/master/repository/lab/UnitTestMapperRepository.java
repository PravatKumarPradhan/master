package com.sdgt.medcare.master.repository.lab;

import com.sdgt.medcare.master.entity.lab.UnitTestMapper;
import com.sdgt.medcare.master.repository.common.UnitUtilRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitTestMapperRepository extends UnitUtilRepository<UnitTestMapper,Long> {

    @Query("SELECT DISTINCT utm FROM UnitTestMapper utm  " +
            "JOIN FETCH utm.testMaster as tm " +
            "LEFT JOIN FETCH tm.testParameterMappers as tpm " +
            "JOIN FETCH tpm.parameterMaster as pm " +
            "LEFT JOIN FETCH pm.parameterReferenceRangeMasters as prrm " +
            "LEFT JOIN FETCH prrm.genderMaster as gm " +
            "LEFT JOIN FETCH prrm.ageGroupMaster as agm " +
            "JOIN FETCH utm.unitMaster as um " +
            "WHERE tm.id= :testId " +
            "and gm.id= :genderId "+
            "and um.code= :unitCode "+
            "and agm.ageFromDays <= :ageInDays and agm.ageToDays >= :ageInDays "
    )
    List<UnitTestMapper> findTestForNumericParams(
            @Param("unitCode") String unitCode
            ,@Param("testId") Long testId
            ,@Param("genderId") Long genderId
            ,@Param("ageInDays") Integer ageInDays
    );

    @Query("SELECT DISTINCT utm FROM UnitTestMapper utm  JOIN FETCH utm.testMaster as tm JOIN FETCH utm.unitMaster as um " +
            "WHERE tm.id= :testId "+
            "and um.code= :unitCode "
    )
    List<UnitTestMapper> findTestForNoParameters(
            @Param("unitCode") String unitCode
            ,@Param("testId") Long testId
    );

    @Query("SELECT DISTINCT utm FROM UnitTestMapper utm  JOIN FETCH utm.testMaster as tm LEFT JOIN FETCH " +
            "tm.testParameterMappers as tpm JOIN FETCH tpm.parameterMaster as pm " +
            "JOIN FETCH utm.unitMaster as um " +
            "JOIN FETCH utm.unitMaster as um " +
            "WHERE tm.id= :testId "+
            "and um.code= :unitCode "
    )
    List<UnitTestMapper> findTestForTextualParameters(
            @Param("unitCode") String unitCode
            ,@Param("testId") Long testId
    );

    @Query("SELECT DISTINCT utm FROM UnitTestMapper utm  " +
            "JOIN FETCH utm.testMaster as tm " +
            "LEFT JOIN FETCH tm.testParameterMappers as tpm " +
            "JOIN FETCH tpm.parameterMaster as pm " +
            "LEFT JOIN FETCH pm.parameterReferenceRangeMasters as prrm " +
            "LEFT JOIN FETCH prrm.genderMaster as gm " +
            "LEFT JOIN FETCH prrm.ageGroupMaster as agm " +
            "LEFT JOIN FETCH pm.parameterTextualRangeMaster as ptrm " +
            "LEFT JOIN FETCH ptrm.genderMaster as gmt " +
            "LEFT JOIN FETCH ptrm.ageGroupMaster as agmt " +
            "JOIN FETCH utm.unitMaster as um " +
            "WHERE tm.id= :testId " +
            "and um.code= :unitCode "+
            "and ((ptrm IS NULL) or (gmt.id= :genderId "+
            "and agmt.ageFromDays <= :ageInDays and agmt.ageToDays >= :ageInDays)) " +
            "and ((prrm IS NULL) " +
            "or (gm.id= :genderId and agm.ageFromDays <= :ageInDays and agm.ageToDays >= :ageInDays) )"
    )
    List<UnitTestMapper> findTestForParams(
            @Param("unitCode") String unitCode
            ,@Param("testId") Long testId
            ,@Param("genderId") Long genderId
            ,@Param("ageInDays") Integer ageInDays
    );
}
