package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.entity.unit.FloorMaster;
import com.sdgt.medcare.master.repository.common.FloorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FloorService extends BaseService<FloorMaster> {


    private FloorRepository floorRepository;

    public FloorService(FloorRepository floorRepository){
        this.floorRepository=floorRepository;
    }


    public Page<FloorMaster> getFloorMaster(Pageable pageable){
        return floorRepository.allFloor(pageable);
      // return floorRepository.findAll(pageable);
    }
}
