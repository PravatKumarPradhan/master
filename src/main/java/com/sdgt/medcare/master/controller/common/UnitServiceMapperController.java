package com.sdgt.medcare.master.controller.common;

import com.core.base.rest.controller.RestWSController;
import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.dto.common.UnitServiceMapperDTO;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import com.sdgt.medcare.master.repository.common.UnitRepository;
import com.sdgt.medcare.master.search.GlobalSearchRequest;
import com.sdgt.medcare.master.search.UnitServiceSpecification;
import com.sdgt.medcare.master.service.common.UnitServiceMapperService;
import com.sdgt.medcare.master.util.BaseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Pravat Kumar Pradhan
 */
@RestController
@CrossOrigin
@RequestMapping ("/service-mapper")
public class UnitServiceMapperController extends RestWSController<UnitServiceMapper> {

   @Autowired
   UnitServiceMapperService unitServiceMapperService;

   @Autowired
   private UnitRepository unitRepository;

   @Autowired
   private UnitServiceSpecification specification;

   @PostMapping ("/save1")
   public List<UnitServiceMapper> saveUnitServiceData(@RequestBody List<UnitServiceMapper> unitServiceMapperList) {
      return unitServiceMapperService.create(unitServiceMapperList);
   }

   @PostMapping ("/save")
   public List<UnitServiceMapper> saveDetails(@RequestBody UnitServiceMapperDTO umDto) {
      return unitServiceMapperService.createUnitServiceDate(umDto);
   }

   @GetMapping ("/getAll")
   public Page<UnitServiceMapper> getAllService(Pageable pageable) {
      return unitServiceMapperService.getAllUnitService(pageable);
   }


   @GetMapping ("/getUnit/{service}")
   public List<UnitServiceMapper> getDataByService(@PathVariable Long service) {
      return unitServiceMapperService.findByService(service);
   }


   @GetMapping ("/getServiceList")
   public Page<ServiceMaster> getServiceData(Pageable pageable) {
      return unitServiceMapperService.getServiceData(pageable);
   }

   @GetMapping ("/getAllByUnit")
   public Page<UnitServiceMapper> getListByUnit(Pageable pageable) {
      String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
      if (unitCode != null) {
         List<UnitMaster> unitMasterList = unitRepository.findByCode(unitCode);
         Long uMasterId = unitServiceMapperService.getUnitId(unitMasterList.stream().filter(unitMasterCode -> unitMasterCode.getCode().equalsIgnoreCase(unitCode)).findFirst());
         return unitServiceMapperService.getDataByUnitId(uMasterId, pageable);
      } else
         return null;


   }

   @GetMapping ("/unitService/{id}")
   public UnitServiceMapper getById(@PathVariable Long id) {
      return unitServiceMapperService.findById(id);
   }


   /**
    * @param unitServiceMapper
    * @param id
    *
    * @return
    */
   @PutMapping ("/updateUnitService/{id}")
   public UnitServiceMapper upDateData(@RequestBody UnitServiceMapper unitServiceMapper, @PathVariable Long id) {
      String unitServiceMapperId = id.toString();
      String orgCode = HttpUtils.getHeader(HttpHeaders.ORG_CODE);
      return unitServiceMapperService.update(unitServiceMapper, unitServiceMapperId);
   }


   /***
    *
    * @return list of Service based on unit
    */
   @GetMapping ("/getServiceListByUnit")
   public List<UnitServiceMapper> getListService() {
      String uCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
      if (uCode != null) {
         List<UnitMaster> uMaster = unitRepository.findByCode(uCode);
         Long uId = BaseUtil.getUnitId(uMaster.stream().filter(unitMasterCode -> unitMasterCode.getCode().equalsIgnoreCase(uCode)).findFirst());
         return unitServiceMapperService.getListByUnit(uId);
      } else
         return (List<UnitServiceMapper>) ResponseEntity.noContent();

   }

   @PostMapping ("/search")
   public Page<UnitServiceMapper> search(@RequestBody GlobalSearchRequest filterRequest, Pageable pageable) {
      String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
      if (StringUtils.isNotBlank(unitCode)) {
         List<UnitMaster> uMaster = unitRepository.findByCode(unitCode);
         Long unitId = BaseUtil.getUnitId(uMaster.stream().filter(unitMasterCode -> unitMasterCode.getCode().equalsIgnoreCase(unitCode)).findFirst());
         return specification.unitServiceMappersSearch(filterRequest, unitId, pageable);
      } else
         return (Page<UnitServiceMapper>) ResponseEntity.noContent();
   }


   /**
    *
    * @param id
    * @param uSMDTO
    * @return
    */
   @PutMapping ("/updateService/{id}")
   public List<UnitServiceMapper> updateService(@PathVariable  Long id, @RequestBody UnitServiceMapperDTO uSMDTO) {
      return unitServiceMapperService.updateService(id, uSMDTO);
   }

   /**
    *
    * @param serviceId    *
    * @return
    */
   @GetMapping ("/serviceData/{serviceId}")
   public UnitServiceMapper getServiceDataById(@PathVariable Long serviceId) {
      String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
      if (StringUtils.isNotBlank(unitCode)) {
         List<UnitMaster> unitMaster = unitRepository.findByCode(unitCode);
         Long unitId = BaseUtil.getUnitId(unitMaster.stream().filter(unitMaster1 -> unitMaster1.getCode().equalsIgnoreCase(unitCode)).findFirst());
         return unitServiceMapperService.getUnitServiceByUnitAndService(serviceId, unitId);
      } else
         return (UnitServiceMapper) ResponseEntity.noContent();
   }


   @GetMapping("/getByService/{serviceId}")
   public List<UnitServiceMapper> getDataByServiceId(@PathVariable Long serviceId){
      return unitServiceMapperService.getUnitServiceByService(serviceId);
   }

   @PutMapping("/statusChange/{id}")
   public ResponseEntity statusChange(@PathVariable Long id){
      unitServiceMapperService.updateStatusForUnit(id);
      return  ResponseEntity.ok(1);
   }
}
