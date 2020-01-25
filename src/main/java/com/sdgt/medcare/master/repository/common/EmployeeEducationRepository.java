package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.EmployeeEducationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Pravat Kumar Pradhan
 */

public interface EmployeeEducationRepository extends JpaRepository<EmployeeEducationDetails,Long> {

     List<EmployeeEducationDetails> findAllByEmployeeMasterDetails(Long employeeMasterDetailsId);

     @Override
     void deleteById(Long aLong);
}
