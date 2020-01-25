package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.EmployeeContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeContactDetailsRepository extends JpaRepository<EmployeeContactDetails,Long> {


   // void deleteByEmployeeMasterDetails(Long employeeMasterDetailsId);
}
