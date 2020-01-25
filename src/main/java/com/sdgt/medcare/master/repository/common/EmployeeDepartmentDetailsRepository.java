package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.unit.EmployeeDepartmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pravat Kumar Pradhan
 */
@Repository
public interface EmployeeDepartmentDetailsRepository  extends JpaRepository<EmployeeDepartmentDetails,Long> {

    public List<EmployeeDepartmentDetails> findAllByEmployeeMasterDetails(Long employeeMasterDetailsId);

    @Override
    void deleteById(Long aLong);


}
