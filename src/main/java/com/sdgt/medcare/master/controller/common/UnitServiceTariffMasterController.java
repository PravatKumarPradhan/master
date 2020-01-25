package com.sdgt.medcare.master.controller.common;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.dto.common.StatusEnum;
import com.sdgt.medcare.master.dto.common.StatusResponse;
import com.sdgt.medcare.master.dto.common.UnitServiceTariffDTO;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitTariffServiceRateMaster;
import com.sdgt.medcare.master.repository.common.UnitRepository;
import com.sdgt.medcare.master.search.GlobalSearchRequest;
import com.sdgt.medcare.master.search.ServiceTariffMapperSpecification;
import com.sdgt.medcare.master.service.common.UnitTariffServiceRateMasterService;
import com.sdgt.medcare.master.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
   @RequestMapping ("/service-tariff")
public class UnitServiceTariffMasterController {

   @Autowired
   private UnitRepository unitRepository;

   @Autowired
   ServiceTariffMapperSpecification specification;

   private UnitTariffServiceRateMasterService unitTariffServiceRateMasterService;

   public UnitServiceTariffMasterController(UnitTariffServiceRateMasterService unitTariffServiceRateMasterService) {
      this.unitTariffServiceRateMasterService = unitTariffServiceRateMasterService;
   }

   @GetMapping ("/getListByUnit")
   public ResponseEntity<Page<UnitTariffServiceRateMaster>> getListByUnit(Pageable pageable) {
      String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);

      if (unitCode != null) {
         List<UnitMaster> unitMaster = unitRepository.findByCode(unitCode);
         Long unitMasterId = BaseUtil.getUnitId(unitMaster.stream().filter(unitMasterCode -> unitMasterCode.getCode().equalsIgnoreCase(unitCode)).findFirst());
       //  return ResponseEntity.ok(unitTariffServiceRateMasterService.getTariffServiceByUnitMaster(unitMasterId, pageable)) ;
        // return Optional.ofNullable(unitTariffServiceRateMasterService.getTariffServiceByUnitMaster(unitMasterId,pageable).map(results->new ResponseEntity<>(results, HttpStatus.OK))).orElse(new ResponseEntity <>(HttpStatus.NOT_FOUND))) ;
         return Optional.ofNullable(unitTariffServiceRateMasterService.getTariffServiceByUnitMaster(unitMasterId,pageable))
               .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
      } else
         return null;
   }

   /**
    *
    * @param uTSRM  list of
    * @return
    */
   @PostMapping("/save")
   public List<UnitTariffServiceRateMaster>  saveData(@RequestBody List<UnitServiceTariffDTO> uTSRM){
      return unitTariffServiceRateMasterService.create(uTSRM);
   }

   @PostMapping("/search")
   public Page<UnitTariffServiceRateMaster> search(@RequestBody GlobalSearchRequest searchFilter, Pageable pageable){
      System.out.println(searchFilter);
      return   specification.tariffServiceRateMastersSearch(searchFilter,pageable);
   }

   /**
    * Inactivate or activate a {@link  UnitTariffServiceRateMaster} for given.
    * @param unitTariffServiceRateMasterId given valid id.
    * @param newStatus that holds only values of {@link StatusEnum}
    * @return {@link StatusResponse} object.
    */
   @PatchMapping("/unitTariffServiceRate/status")
   public StatusResponse statusUpdate(@RequestParam("id") String unitTariffServiceRateMasterId,
                                      @RequestParam("newStatus") String newStatus) {
      try {
         StatusEnum status = StatusEnum.valueOf(newStatus.toUpperCase());
         return unitTariffServiceRateMasterService.updateStatus(unitTariffServiceRateMasterId, status);
      } catch (IllegalArgumentException e) {
         StatusResponse statusResponse = new StatusResponse();
         statusResponse.setEntity("UnitTariffServiceRateMaster");
         statusResponse.setExecutionComplete(false);
         statusResponse.setErrorMessage("statusUpdate; Invalid newStatus value.");

         return statusResponse;
      }

   }


}
