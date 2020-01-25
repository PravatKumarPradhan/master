package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.OTRoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTRoleMasterRepository extends JpaRepository<OTRoleMaster,Long> {
}
