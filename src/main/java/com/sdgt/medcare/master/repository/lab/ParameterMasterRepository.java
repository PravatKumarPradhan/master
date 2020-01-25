package com.sdgt.medcare.master.repository.lab;

import com.sdgt.medcare.master.entity.lab.ParameterMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Pravat Kumar Pradhan
 */
@Repository
public interface ParameterMasterRepository extends JpaRepository<ParameterMaster,Long> , JpaSpecificationExecutor<ParameterMaster> {

}
