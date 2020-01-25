package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.unit.FloorMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<FloorMaster,Long> {

    @Query(" select wm from  FloorMaster fm  inner join  WardMaster wm on wm.floorMaster=fm.id")
   // @Query("Select address from Address a inner join `Order` o ON ...
    public Page<FloorMaster> allFloor(Pageable pageable);


}
