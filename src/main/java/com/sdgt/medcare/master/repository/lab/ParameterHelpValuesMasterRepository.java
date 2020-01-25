package com.sdgt.medcare.master.repository.lab;

import com.sdgt.medcare.master.entity.lab.ParameterHelpValuesMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ParameterHelpValuesMasterRepository  extends JpaRepository<ParameterHelpValuesMaster,Long> {

   @Override
   void deleteById(Long id);


   @Transactional
   @Modifying
   @Query ("update ParameterHelpValuesMaster phv set phv.active = false WHERE phv.id = :id ")
   void updateStatus(@Param ("id") Long id);
}
