package com.sdgt.medcare.master.entity.clinical;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Table(name= "m_goal",schema = "clinical")
@Entity
public class GoalMaster extends BaseMaster implements Serializable {

    public GoalMaster() {
    }

    @Column(name ="is_checked")
    private Boolean isChecked=false;

    @Column(name="goal_code")
    private String goalCode;

    public Set<GoalInterventionMapper> getGoalInterventionMappers() {
        return goalInterventionMappers;
    }

    public void setGoalInterventionMappers(Set<GoalInterventionMapper> goalInterventionMappers) {
        this.goalInterventionMappers = goalInterventionMappers;
    }

    @JsonIgnoreProperties("goalMaster")
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy="goalMaster")
    Set<GoalInterventionMapper> goalInterventionMappers;

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        isChecked = isChecked;
    }

    public String getGoalCode() {
        return goalCode;
    }

    public void setGoalCode(String goalCode) {
        this.goalCode = goalCode;
    }
}
