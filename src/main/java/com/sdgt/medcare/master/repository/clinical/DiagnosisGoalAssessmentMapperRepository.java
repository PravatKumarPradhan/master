package com.sdgt.medcare.master.repository.clinical;

import com.sdgt.medcare.master.entity.clinical.DiagnosisGoalAssessmentMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisGoalAssessmentMapperRepository extends JpaRepository<DiagnosisGoalAssessmentMapper,Long> {

//    @Query("SELECT dgm.goalMaster FROM DiagnosisGoalAssessmentMapper dgm " +
//            "left join fetch dgm.goalMaster as gm " +
//            "left join fetch dgm.assessmentMaster as am " +
//            "left join fetch dgm.diagnosisMaster as dm " +
//            "where diagnosis_code IN (:diagnosis) or assessment_code IN (:assessments) ")
//    List<Object> findAllGoals(
//            @Param("diagnosis") List<String> diagnosis
//            ,@Param("assessments") List<String> assessments
//    );

    List<DiagnosisGoalAssessmentMapper> findAllGoalsByDiagnosisMasterCodeInOrAssessmentMasterCodeIn(
            @Param("diagnosis") List<String> diagnosis
            ,@Param("assessments") List<String> assessments
    );
    List<DiagnosisGoalAssessmentMapper> findAllGoalsByDiagnosisMasterCodeIn(
            @Param("diagnosis") List<String> diagnosis);
    List<DiagnosisGoalAssessmentMapper> findAllGoalsByAssessmentMasterCodeIn(
            @Param("assessments") List<String> assessments
    );

}
