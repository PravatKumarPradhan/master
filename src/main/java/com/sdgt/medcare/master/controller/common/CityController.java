package com.sdgt.medcare.master.controller.common;

import com.sdgt.medcare.master.entity.global.CityMaster;
import com.sdgt.medcare.master.repository.common.CityMasterRepository;
import com.sdgt.medcare.master.service.common.CityMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/city")
@RestController
public class CityController {

    @Autowired
    CityMasterRepository cityMasterRepository;
    @Autowired
    CityMasterService cityMasterService;

    @GetMapping("/getCity")
    public Page<CityMaster> getCity(Pageable pageable) {
        return cityMasterRepository.findAll(pageable);
    }

    @PostMapping("/addCity")
    ResponseEntity<?> insert(@RequestBody CityMaster cityMaster) {
        List<CityMaster> newCity = Arrays.asList(cityMaster);
        cityMasterRepository.saveAll(newCity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCity);
    }
}
