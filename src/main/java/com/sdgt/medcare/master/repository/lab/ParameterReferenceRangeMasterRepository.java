package com.sdgt.medcare.master.repository.lab;

import com.sdgt.medcare.master.entity.lab.ParameterReferenceRangeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParameterReferenceRangeMasterRepository extends JpaRepository<ParameterReferenceRangeMaster,Long> {

   @Override
   void deleteById(Long aLong);

   @Transactional
   @Modifying
   @Query ("update ParameterReferenceRangeMaster prr set prr.active = false WHERE prr.id = :id ")
   void updateStatus(@Param("id") Long id);



}
