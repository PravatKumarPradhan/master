package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.unit.DependentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pravat Kumar Pradhan
 */
@Repository
public interface DependentDetailsRepository extends JpaRepository<DependentDetails,Long> {

     List<DependentDetails> findAllByEmployeeMasterDetailsId(Long employeeMasterDetails);

     @Override
     void deleteById(Long id);


}
