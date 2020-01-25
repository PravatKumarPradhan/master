package com.sdgt.medcare.master.controller.common;

import com.sdgt.medcare.master.entity.unit.FloorMaster;
import com.sdgt.medcare.master.service.common.FloorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/floor")
public class FloorController {

    private FloorService floorService;

    FloorController(FloorService floorService){
        this.floorService=floorService;
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping("/getAllFloor")
    public Page<FloorMaster> getFloor(Pageable pageable){
        return floorService.getFloorMaster(pageable);
    }


}
