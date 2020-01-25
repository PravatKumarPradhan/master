package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.inventory.KitMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("KitMasterRepository")
public interface KitMasterRepository extends JpaRepository<KitMaster, Long>, KitMasterRepositoryCustom {
}
