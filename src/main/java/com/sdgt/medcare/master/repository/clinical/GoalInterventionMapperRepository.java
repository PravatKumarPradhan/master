package com.sdgt.medcare.master.repository.clinical;

import com.sdgt.medcare.master.entity.clinical.GoalInterventionMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalInterventionMapperRepository extends JpaRepository<GoalInterventionMapper,Long> {

    List<GoalInterventionMapper> findAllByGoalMasterCodeIn(
            @Param("goal") List<String> goal
    );
}
