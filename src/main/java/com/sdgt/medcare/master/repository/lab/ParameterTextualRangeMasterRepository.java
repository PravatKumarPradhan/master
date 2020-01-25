package com.sdgt.medcare.master.repository.lab;

import com.sdgt.medcare.master.entity.lab.ParameterTextualRangeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParameterTextualRangeMasterRepository extends JpaRepository<ParameterTextualRangeMaster,Long> {

   @Override
   void deleteById(Long aLong);


   @Transactional
   @Modifying
   @Query ("update ParameterTextualRangeMaster ptr set ptr.active = false WHERE ptr.id = :id ")
   void updateStatus(@Param ("id") Long id);
}
