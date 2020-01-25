package com.sdgt.medcare.master.service.clinical;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.clinical.DiagnosisGoalAssessmentMapper;
import com.sdgt.medcare.master.repository.clinical.DiagnosisGoalAssessmentMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosisGoalAssessmentMapperService extends BaseService<DiagnosisGoalAssessmentMapper> {

    @Autowired
    DiagnosisGoalAssessmentMapperRepository diagnosisGoalAssessmentMapperRepository;

    public List<DiagnosisGoalAssessmentMapper> getAllData(List<String> diagnosis,List<String> assessments){

        //String hql = "SELECT c FROM " + clazz.getSimpleName() + " c WHERE c." + field + " IN :value";

//        String hql = "SELECT * FROM m_diagnosis_goal_mapper " +
//                " inner join m_diagnosis on m_diagnosis_goal_mapper.diagnosis_code = m_diagnosis.code" +
//                " inner join m_assessment on m_diagnosis_goal_mapper.assessment_code = m_assessment.code" +
//                " inner join m_goal on m_diagnosis_goal_mapper.goal_code = m_goal.code" +
//                " where diagnosis_code in :condition or assessment_code in :assessment";
//
//        Query query=entityManager.createQuery(hql);
//        query.setParameter("condition",diagnosis);
//        query.setParameter("assessment",assessments);
//        return query.getResultList();
        List<DiagnosisGoalAssessmentMapper> diagnosisGoalAssessmentMappers=new ArrayList<>();
        if(diagnosis!=null && !diagnosis.isEmpty() && assessments!=null && !assessments.isEmpty()) {
            diagnosisGoalAssessmentMappers  = diagnosisGoalAssessmentMapperRepository
                    .findAllGoalsByDiagnosisMasterCodeInOrAssessmentMasterCodeIn(diagnosis, assessments);
        }
        else if(diagnosis!=null && !diagnosis.isEmpty() && (assessments==null || assessments.isEmpty())) {
            diagnosisGoalAssessmentMappers  = diagnosisGoalAssessmentMapperRepository
                    .findAllGoalsByDiagnosisMasterCodeIn(diagnosis);
        }
        else if((diagnosis==null || diagnosis.isEmpty()) && assessments!=null && !assessments.isEmpty()) {
            diagnosisGoalAssessmentMappers  = diagnosisGoalAssessmentMapperRepository
                    .findAllGoalsByAssessmentMasterCodeIn(assessments);
        }
        return diagnosisGoalAssessmentMappers;
    }
}
