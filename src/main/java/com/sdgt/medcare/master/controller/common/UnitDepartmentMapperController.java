package com.sdgt.medcare.master.controller.common;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitDepartmentMapper;
import com.sdgt.medcare.master.repository.common.UnitDepartmentMapperRepository;
import com.sdgt.medcare.master.repository.common.UnitRepository;
import com.sdgt.medcare.master.service.common.UnitDepartmentMapperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Pravat Kumar Pradhan
 */

@RestController
@RequestMapping("/dept")
@CrossOrigin
public class UnitDepartmentMapperController {

    private UnitDepartmentMapperService unitDepartmentMapperService;

    public UnitDepartmentMapperController(UnitDepartmentMapperService unitDepartmentMapperService) {
        this.unitDepartmentMapperService = unitDepartmentMapperService;
    }

    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private UnitDepartmentMapperRepository unitDepartmentMapperRepository;

    /**
     * @return
     */
    @GetMapping("/getDeptByUnit")
    public List<UnitDepartmentMapper> getDepartmentByUnit() {

        String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
        if (unitCode != null) {
            List<UnitMaster> unitCode1 = unitRepository.findByCode(unitCode);
            Long unitId = getUnitId(unitCode1.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());
          //  return unitDepartmentMapperRepository.findByUnitMasterId(unitId, pageable);
            return unitDepartmentMapperRepository.findByUnitMasterId(unitId);

        } else {
            Long unitMasterId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

            return unitDepartmentMapperRepository.findByUnitMasterId(unitMasterId);
        }
    }

    /**
     * @return
     */
    @PostMapping("/getDeptByUnitCode")
    public List<UnitDepartmentMapper> getDepartmentByUnitCode(@RequestBody String unitCodeJson) {
        JsonObject unitCodeObj = new JsonParser().parse(unitCodeJson).getAsJsonObject();
        String unitCode = unitCodeObj.get("unitCode").getAsString().trim().toUpperCase();

        if (StringUtils.isNotBlank(unitCode) && unitCode != null) {
            List<UnitMaster> unitCodeMaster = unitRepository.findByCode(unitCode);
            String finalUnitCode = unitCode;
            Long unitId = getUnitId(unitCodeMaster.stream().filter(unit -> unit.getCode().equalsIgnoreCase(finalUnitCode)).findFirst());
            return unitDepartmentMapperRepository.findByUnitMasterId(unitId);

        } else {
            Long unitMasterId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

            return unitDepartmentMapperRepository.findByUnitMasterId(unitMasterId);
        }
    }


    /**
     * @param unitIdfromCode
     * @return
     */
    public Long getUnitId(Optional<UnitMaster> unitIdfromCode) {
        Long unitId = 0L;
        if (unitIdfromCode.isPresent()) {
            UnitMaster unit = unitIdfromCode.get();
            unitId = Long.parseLong(unit.getId());
        }
        return unitId;
    }
}
