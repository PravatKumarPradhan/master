package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.MarkupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkupMasterRepository extends JpaRepository<MarkupMaster,Long> {
//	@Query("select t from BedMaster t join WardMaster wm join BedCategoryMaster bcm where wm.id like %:wardid% or bcm like %:bedclassid%")
//	public List<BedMaster> Getlistbedmaster(@Param("wardid") String bedid,@Param("bedclassid") String bedclassid);

}
