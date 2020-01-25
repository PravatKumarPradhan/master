package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.EmployeeProfessionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeProffessionalRepository  extends JpaRepository<EmployeeProfessionDetails,Long> {

     List<EmployeeProfessionDetails> findAllByEmployeeMasterDetails(Long employeeMasterDetailsId);

     @Override
     void deleteById(Long aLong);
}
