package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.TheatreTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreTypeMasterRepository extends JpaRepository<TheatreTypeMaster,Long> {
   TheatreTypeMaster findByCode( final String code) ;

}
