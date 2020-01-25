package com.sdgt.medcare.master.controller.common;


import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.BedMaster;
import com.sdgt.medcare.master.repository.common.BedRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitRepository;
import com.sdgt.medcare.master.service.common.BedMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Pravat Kumar Pradhan
 * @author Nitesh Kumar
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/bed")
public class BedController {

	@Autowired private BedRepository bedRepository;
	@Autowired private UnitRepository unitRepository;
	@Autowired private UnitMasterRepository unitMasterRepository;
	@Autowired BedMasterService bedMasterService;

	/**
	 *
	 @param pageble
	 @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/getBedList")
	public Page<BedMaster>  getBedLis( Pageable pageble){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		System.out.println(unitCode);
		if(unitCode!=null) {
			List <UnitMaster> unitCode1=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitCode1.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());

			return bedRepository.findByUnitMasterId(unitId,pageble);
		}
		else {
			Long  unitMaste=Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findByUnitMasterId(unitMaste,pageble);
		}

	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/getAllBedList")
	public List<BedMaster>  getAllBedList(){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		System.out.println(unitCode);
		if(unitCode!=null) {
			List <UnitMaster> unitMasterList=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());
			return bedMasterService.getAllBedListByVirtual(unitId);
			//return bedRepository.findAllByUnitMasterId(unitId);
		}
		else {
			Long  unitId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByUnitMasterId(unitId);
		}

	}

	/**
	 *
	 * @param pageable
	 * @param blockId
	 * @return
	 */
	@GetMapping("/findAllBedList/{blockId}")
	public Page<BedMaster>  getAllBedListByBlockId(Pageable pageable, @PathVariable Long blockId){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if(unitCode!=null) {
			List <UnitMaster> unitMasterList=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());

			return bedRepository.findAllByUnitMasterIdAndBlockMasterId(unitId, blockId, pageable);
		}
		else {
			Long  unitId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByUnitMasterIdAndBlockMasterId(unitId, blockId, pageable);
		}

	}

	/**
	 *
	 * @param pageable
	 * @param blockId
	 * @param floorId
	 * @return
	 */
	@GetMapping("/findAllBedList/{blockId}/{floorId}")
	public Page<BedMaster>  getBedByBlockIdAndFloorId(Pageable pageable, @PathVariable Long blockId, @PathVariable Long floorId){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if(unitCode!=null) {
			List <UnitMaster> unitMasterList=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());

			return bedRepository.findAllByUnitMasterIdAndBlockMasterIdAndFloorMasterId(unitId, blockId, floorId, pageable);
		}
		else {
			Long  unitId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByUnitMasterIdAndBlockMasterIdAndFloorMasterId(unitId, blockId, floorId, pageable);
		}

	}

	/**
	 *
	 * @param pageable
	 * @param blockId
	 * @param floorId
	 * @param wardId
	 * @return
	 */
	@GetMapping("/findAllBedList/{blockId}/{floorId}/{wardId}")
	public Page<BedMaster>  getBedByBlockIdAndFloorIdAndWardId(Pageable pageable, @PathVariable Long blockId, @PathVariable Long floorId,
										     @PathVariable Long wardId){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if(unitCode!=null) {
			List <UnitMaster> unitMasterList=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());

			return bedRepository.findAllByUnitMasterIdAndBlockMasterIdAndFloorMasterIdAndWardMasterId(unitId, blockId, floorId, wardId, pageable);
		}
		else {
			Long  unitId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByUnitMasterIdAndBlockMasterIdAndFloorMasterIdAndWardMasterId(unitId, blockId, floorId, wardId, pageable);
		}

	}

	/**
	 *
	 * @param pageable
	 * @param blockId
	 * @param floorId
	 * @param wardId
	 * @param bedCategoryId
	 * @return
	 */
	@GetMapping("/findAllBedList/{blockId}/{floorId}/{wardId}/{bedCategoryId}")
	public Page<BedMaster>  getBedByBlockIdAndFloorIdAndWardIdAndBedCategoryId(Pageable pageable, @PathVariable Long blockId, @PathVariable Long floorId,
													   @PathVariable Long wardId, @PathVariable Long bedCategoryId){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if(unitCode!=null) {
			List <UnitMaster> unitMasterList=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());

			return bedRepository.findAllByUnitMasterIdAndBlockMasterIdAndFloorMasterIdAndWardMasterIdAndBedCategoryId(unitId, blockId, floorId, wardId, bedCategoryId, pageable);
		}
		else {
			Long  unitId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByUnitMasterIdAndBlockMasterIdAndFloorMasterIdAndWardMasterIdAndBedCategoryId(unitId, blockId, floorId, wardId, bedCategoryId, pageable);
		}

	}

	/**
	 *
	 * @param pageable
	 * @param statusId
	 * @return
	 */
	@GetMapping("/getBedByStatus/{statusId}")
	public Page<BedMaster>  getBedByStatusId(Pageable pageable, @PathVariable Long statusId){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if(unitCode!=null) {
			List <UnitMaster> unitMasterList=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());

			return bedRepository.findAllByUnitMasterIdAndBedStatusId(unitId, statusId, pageable);
		}
		else {
			Long  unitId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByUnitMasterIdAndBedStatusId(unitId, statusId, pageable);
		}

	}

	/**
	 *
	 * @param pageable
	 * @param bedCategoryId
	 * @return
	 */
	@GetMapping("/getBedByBedCategory/{bedCategoryId}")
	public Page<BedMaster>  getBedBybedCategoryId(Pageable pageable, @PathVariable Long bedCategoryId){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if(unitCode!=null) {
			List <UnitMaster> unitMasterList=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());

			return bedRepository.findAllByUnitMasterIdAndBedCategoryId(unitId, bedCategoryId, pageable);
		}
		else {
			Long  unitId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByUnitMasterIdAndBedCategoryId(unitId, bedCategoryId, pageable);
		}

	}

	/**
	 *
	 * @param pageable
	 * @param wardId
	 * @return
	 */
	@GetMapping("/getBedByWard/{wardId}")
	public Page<BedMaster>  getBedByWardId(Pageable pageable, @PathVariable Long wardId){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if(unitCode!=null) {
			List <UnitMaster> unitMasterList=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());

			return bedRepository.findAllByUnitMasterIdAndWardMasterId(unitId, wardId, pageable);
		}
		else {
			Long  unitId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByUnitMasterIdAndWardMasterId(unitId, wardId, pageable);
		}

	}

	/**
	 *
	 * @param pageable
	 * @param floorId
	 * @return
	 */
	@GetMapping("/getBedByFloor/{floorId}")
	public Page<BedMaster>  getBedByFloorId(Pageable pageable, @PathVariable Long floorId){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if(unitCode!=null) {
			List <UnitMaster> unitMasterList=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());

			return bedRepository.findAllByUnitMasterIdAndFloorMasterId(unitId, floorId, pageable);
		}
		else {
			Long  unitId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByUnitMasterIdAndFloorMasterId(unitId, floorId, pageable);
		}

	}

	/**
	 *
	 * @param pageble
	 * @param wardMaster
	 * @return
	 */
	@GetMapping("getAllBedList/{wardMaster}")
	public Page<BedMaster> getBedByWard(Pageable pageble,@PathVariable Long wardMaster){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if(unitCode!=null) {
			List <UnitMaster> unitCode1=unitRepository.findByCode(unitCode);
			Long unitId=getUnitId(unitCode1.stream().filter(unit->unit.getCode().equalsIgnoreCase(unitCode)).findFirst());
			return bedRepository.findAllByWardMasterIdAndUnitMasterId(wardMaster, unitId, pageble);
		}
		else {

			Long  unitMaste=Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByWardMasterIdAndUnitMasterId(wardMaster, unitMaste, pageble);
		}
	}

	/**
	 *
	 * @param wardMaster
	 * @return
	 */
	@GetMapping("getBedList/{wardMaster}")
	public List<BedMaster> getBedByWardByStatus(@PathVariable Long wardMaster) {
		String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if (unitCode != null) {
			List<UnitMaster> unitMasterList = unitRepository.findByCode(unitCode);
			Long unitId = getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());
			return bedMasterService.getBedByWardAndVirtual(wardMaster, unitId);
		} else {

			Long unitMasterId = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));

			return bedRepository.findAllByWardMasterIdAndUnitMasterIdAndBedStatusId(wardMaster, unitMasterId, 1L);
		}
	}


	/**
	 *
	 * @param wardMaster
	 * @param bedCategory
	 * @return
	 */
	@GetMapping("getBedList/{wardMaster}/{bedCategory}")
	public List<BedMaster> getBedByWardAndBedCategory(@PathVariable Long wardMaster, @PathVariable Long bedCategory) {
		String unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		if (unitCode != null) {
			List<UnitMaster> unitMasterList = unitRepository.findByCode(unitCode);
			Long unitId = getUnitId(unitMasterList.stream().filter(unit -> unit.getCode().equalsIgnoreCase(unitCode)).findFirst());
			return bedMasterService.getBedByWardAndCategoryAndVirtual(wardMaster, unitId, bedCategory);
		} else {
			Long unitMaste = Long.parseLong(HttpUtils.getHeader(HttpHeaders.UNIT_ID));
			return bedRepository.findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndBedStatusId(wardMaster, unitMaste, bedCategory, 1L);
		}

	}

	/**
	 *
	 * @param pageble
	 * @return
	 */
	@GetMapping("/bedCode")
	public Page<BedMaster> getBedList( Pageable pageble){
		String unitCode=HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
		return bedRepository.findByUnitMasterCode(unitCode, pageble);
	}

	/**
	 *
	 * @param unitMasterCode
	 * @return
	 */
	@GetMapping("/bedByEr")
	public List<BedMaster> getBedByEr(@RequestParam(name="unitMasterCode") String unitMasterCode ){
		return bedRepository.findByWardMaster(unitRepository.findByCode(unitMasterCode).get(0));
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@PatchMapping(value= "/statusChange/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public BedMaster patchBedCategoryId(@PathVariable String id, @RequestBody String patchObject){
		return  bedMasterService.updateBedStatus(id , patchObject);


	}

	/**
	 *
	 * @param unitIdfromCode
	 * @return
	 */
	public Long getUnitId(Optional<UnitMaster> unitIdfromCode) {
		Long unitId=0L;
		if(unitIdfromCode.isPresent()) {
			UnitMaster unit=unitIdfromCode.get();
			unitId=Long.parseLong(unit.getId());
		}
		return unitId;
	}

}

