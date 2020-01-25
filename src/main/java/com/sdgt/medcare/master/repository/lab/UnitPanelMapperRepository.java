package com.sdgt.medcare.master.repository.lab;

import com.sdgt.medcare.master.entity.lab.UnitPanelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitPanelMapperRepository extends JpaRepository<UnitPanelMapper,Long> {

    @Query("SELECT DISTINCT upm FROM UnitPanelMapper upm  " +
            "LEFT JOIN FETCH upm.panelMaster as pam " +
            "LEFT JOIN FETCH pam.panelUnitTestMapppers as pdm " +
            "LEFT JOIN FETCH pdm.unitTestMapper as utm " +
            "LEFT JOIN FETCH utm.testMaster as tm " +
            "LEFT JOIN FETCH tm.testParameterMappers as tpm " +
            "LEFT JOIN FETCH tpm.parameterMaster as pm " +
            "LEFT JOIN FETCH pm.parameterReferenceRangeMasters as prrm " +
            "LEFT JOIN FETCH prrm.genderMaster as gm " +
            "LEFT JOIN FETCH prrm.ageGroupMaster as agm " +
            "LEFT JOIN FETCH pm.parameterTextualRangeMaster as ptrm " +
            "LEFT JOIN FETCH ptrm.genderMaster as gmt " +
            "LEFT JOIN FETCH ptrm.ageGroupMaster as agmt " +
            "JOIN FETCH upm.unitMaster as um " +
            "WHERE pam.id= :panelId " +
            "and um.code= :unitCode "+
            "and ((ptrm IS NULL) or (gmt.id= :genderId "+
            "and agmt.ageFromDays <= :ageInDays and agmt.ageToDays >= :ageInDays)) " +
            "and ((prrm IS NULL) " +
            "or (gm.id= :genderId and agm.ageFromDays <= :ageInDays and agm.ageToDays >= :ageInDays) )"
    )
    List<UnitPanelMapper> findForNumericParams(
            @Param("unitCode") String unitCode
            ,@Param("panelId") Long panelId
            ,@Param("genderId") Long genderId
            ,@Param("ageInDays") Integer ageInDays
    );

    @Query("SELECT DISTINCT upm FROM UnitPanelMapper upm  " +
            "LEFT JOIN FETCH upm.panelMaster as pam " +
            "LEFT JOIN FETCH pam.serviceMaster as sm " +
            "LEFT JOIN FETCH pam.panelUnitTestMapppers as pdm " +
            "LEFT JOIN FETCH pdm.unitTestMapper as utm " +
            "LEFT JOIN FETCH utm.testMaster as tm " +
            "LEFT JOIN FETCH tm.testParameterMappers as tpm " +
            "LEFT JOIN FETCH tpm.parameterMaster as pm " +
            "LEFT JOIN FETCH pm.parameterReferenceRangeMasters as prrm " +
            "LEFT JOIN FETCH prrm.genderMaster as gm " +
            "LEFT JOIN FETCH prrm.ageGroupMaster as agm " +
            "LEFT JOIN FETCH pm.parameterTextualRangeMaster as ptrm " +
            "LEFT JOIN FETCH ptrm.genderMaster as gmt " +
            "LEFT JOIN FETCH ptrm.ageGroupMaster as agmt " +
            "JOIN FETCH upm.unitMaster as um " +
            "WHERE sm.id= :serviceId " +
            "and um.code= :unitCode "+
            "and ((ptrm IS NULL) or (gmt.id= :genderId "+
            "and agmt.ageFromDays <= :ageInDays and agmt.ageToDays >= :ageInDays)) " +
            "and ((prrm IS NULL) " +
            "or (gm.id= :genderId and agm.ageFromDays <= :ageInDays and agm.ageToDays >= :ageInDays) )"
    )
    List<UnitPanelMapper> findForNumericParamsByServiceId(
            @Param("unitCode") String unitCode
            ,@Param("serviceId") Long panelId
            ,@Param("genderId") Long genderId
            ,@Param("ageInDays") Integer ageInDays
    );

    @Query("SELECT DISTINCT upm FROM UnitPanelMapper upm  " +
            "LEFT JOIN upm.panelMaster as pam " +
            "LEFT JOIN pam.panelUnitTestMapppers as pdm " +
            "LEFT JOIN pdm.unitTestMapper as utm " +
            "LEFT JOIN utm.testMaster as tm " +
            "JOIN FETCH upm.unitMaster as um " +
            "WHERE pam.id= :panelId "+
            "and um.code= :unitCode "
    )
    List<UnitPanelMapper> findTestForNoParameters(
            @Param("unitCode") String unitCode
            ,@Param("panelId") Long panelId
    );

    @Query("SELECT DISTINCT upm FROM UnitPanelMapper upm  " +
            "LEFT JOIN upm.panelMaster as pam " +
            "LEFT JOIN pam.serviceMaster as sm " +
            "LEFT JOIN pam.panelUnitTestMapppers as pdm " +
            "LEFT JOIN pdm.unitTestMapper as utm " +
            "LEFT JOIN utm.testMaster as tm " +
            "JOIN FETCH upm.unitMaster as um " +
            "WHERE sm.id= :serviceId "+
            "and um.code= :unitCode "
    )
    List<UnitPanelMapper> findTestForNoParametersByService(
            @Param("unitCode") String unitCode
            ,@Param("serviceId") Long serviceId
    );
}
