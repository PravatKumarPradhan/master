package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.BedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedStatusRepository  extends JpaRepository<BedStatus,Long>{

}
