package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.dto.otims.PreferenceCardDTO;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.global.PreferenceCardDetailsMaster;
import com.sdgt.medcare.master.entity.global.PreferenceCardMaster;
import com.sdgt.medcare.master.entity.global.PreferenceCardOtherDetailsMaster;
import com.sdgt.medcare.master.entity.global.ProcedureMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.repository.common.DepartmentMasterRepository;
import com.sdgt.medcare.master.repository.common.ItemMasterRepository;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.PreferenceCardDetailsRepository;
import com.sdgt.medcare.master.repository.common.PreferenceCardOtherDetailsRepository;
import com.sdgt.medcare.master.repository.common.PreferenceCardRepository;
import com.sdgt.medcare.master.repository.common.ProcedureRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PreferenceCardMasterService extends BaseService<PreferenceCardMaster>{
	private Logger logger = LoggerFactory.getLogger(PreferenceCardMasterService.class);

	@Autowired private PreferenceCardRepository preferenceCardRepository;
	@Autowired private OrganizationMasterRepository organizationMasterRepository;
	@Autowired private UnitMasterRepository unitMasterRepository;
	@Autowired private DepartmentMasterRepository departmentMasterRepository;
	@Autowired private ProcedureRepository procedureRepository;
	@Autowired private ItemMasterRepository itemMasterRepository;

	@Autowired
	PreferenceCardDetailsRepository preferenceCardDetailsRepository;

	@Autowired
	PreferenceCardOtherDetailsRepository preferenceCardOtherDetailsRepository;



	@Transactional
	public PreferenceCardMaster create(PreferenceCardDTO preferenceCardDTO) {
		verifyNotNull(preferenceCardDTO);
		logger.info("Preference Card Master create");

		final OrganizationMaster organizationMaster = findAndValidateOrgCode(preferenceCardDTO.getOrgCode());
		final UnitMaster unitMaster = findAndValidateUnitCode(preferenceCardDTO.getUnitCode());
		final DepartmentMaster departmentMaster=findAndValidateDepartmentCode(preferenceCardDTO.getDeptCode());
		final ProcedureMaster procedureMaster=findAndValidateProcedureCode(preferenceCardDTO.getProcedureCode());


		final PreferenceCardMaster preferenceCardMasterObj=new PreferenceCardMaster();

		preferenceCardMasterObj.setDepartment(departmentMaster);
		preferenceCardMasterObj.setOrganizationMaster(organizationMaster);
		logger.info("Org Master :" +organizationMaster);
		preferenceCardMasterObj.setUnitMaster(unitMaster);
		preferenceCardMasterObj.setCardAgainst(preferenceCardDTO.getCardAgainst());
		preferenceCardMasterObj.setDoctorId(preferenceCardDTO.getDoctorId());
		preferenceCardMasterObj.setProcedureMaster(procedureMaster);
		preferenceCardMasterObj.setActive(true);
		preferenceCardMasterObj.setCode(preferenceCardDTO.getCode());
		preferenceCardMasterObj.setCreatedDate(new Date());
		preferenceCardMasterObj.setCreatedBy(preferenceCardDTO.getCreatedBy());

		PreferenceCardMaster preferenceCardMasterResponse= preferenceCardRepository.save(preferenceCardMasterObj);
		List<PreferenceCardDetailsMaster> preferenceCardDetailsList=new ArrayList<PreferenceCardDetailsMaster>();
		PreferenceCardOtherDetailsMaster preferenceCardOtherDetailsMasters =new PreferenceCardOtherDetailsMaster();

		if(preferenceCardDTO.getPreferenceCardOtherDetailsMaster()!=null ){
			PreferenceCardOtherDetailsMaster preferenceCardOtherDetailsMastersObj =preferenceCardDTO.getPreferenceCardOtherDetailsMaster();
			preferenceCardOtherDetailsMasters.setCode(preferenceCardOtherDetailsMastersObj.getCode());
			preferenceCardOtherDetailsMasters.setPostionDetails(preferenceCardOtherDetailsMastersObj.getPostionDetails());
			preferenceCardOtherDetailsMasters.setPreparation(preferenceCardOtherDetailsMastersObj.getPreparation());
			preferenceCardOtherDetailsMasters.setOrganizationMaster(organizationMaster);
			preferenceCardOtherDetailsMasters.setUnitMaster(unitMaster);
			preferenceCardOtherDetailsMasters.setActive(true);
			preferenceCardOtherDetailsMasters.setCode(preferenceCardOtherDetailsMastersObj.getCode());
			preferenceCardOtherDetailsMasters.setCreatedDate(new Date());
			preferenceCardOtherDetailsMasters.setCreatedBy(preferenceCardOtherDetailsMastersObj.getCreatedBy());
			preferenceCardOtherDetailsMasters.setPreferenceCardMaster(preferenceCardMasterResponse);
			preferenceCardOtherDetailsRepository.save(preferenceCardOtherDetailsMasters);
		}
		if(preferenceCardDTO.getPreferenceCardDetailsMaster()!=null) {
			for (PreferenceCardDetailsMaster preferenceCardDetailsMasterObj : preferenceCardDTO.getPreferenceCardDetailsMaster()) {
				PreferenceCardDetailsMaster preferenceCardDetailsMasterObj1 = new PreferenceCardDetailsMaster();

				preferenceCardDetailsMasterObj1.setCartType(preferenceCardDetailsMasterObj.getCartType());
				preferenceCardDetailsMasterObj1.setPreferenceCardMaster(preferenceCardMasterResponse);
				preferenceCardDetailsMasterObj1.setItemMaster(findAndValidateItemMaster(preferenceCardDetailsMasterObj.getItemMaster().getCode()));
				preferenceCardDetailsMasterObj1.setOrganizationMaster(organizationMaster);
				preferenceCardDetailsMasterObj1.setRemarks(preferenceCardDetailsMasterObj.getRemarks());
				preferenceCardDetailsMasterObj1.setRequiredQty(preferenceCardDetailsMasterObj.getRequiredQty());
				preferenceCardDetailsMasterObj1.setUnitMaster(unitMaster);
				preferenceCardDetailsMasterObj1.setActive(true);
				//preferenceCardDetailsMasterObj1.setCode(preferenceCardDetailsMasterObj.getCode());
				preferenceCardDetailsMasterObj1.setCreatedDate(new Date());
				preferenceCardDetailsMasterObj1.setCreatedBy(preferenceCardDetailsMasterObj.getCreatedBy());
				preferenceCardDetailsList.add(preferenceCardDetailsMasterObj1);

				preferenceCardDetailsRepository.save(preferenceCardDetailsMasterObj1);

			}
		}

		return preferenceCardMasterResponse;
	}

	@Transactional
	public PreferenceCardMaster updatePreferenceCard(Long id, PreferenceCardDTO preferenceCardDTO){
		PreferenceCardMaster preferenceCardMasterResponse=null;
		List<PreferenceCardDetailsMaster> preferenceCardDetailsListT=new ArrayList<PreferenceCardDetailsMaster>();
		PreferenceCardMaster objectToPatch= preferenceCardRepository.findById(id).orElse(null);
		if(objectToPatch!=null){
			if(preferenceCardDTO.getDeptCode()!=null){
				final DepartmentMaster departmentMaster=findAndValidateDepartmentCode(preferenceCardDTO.getDeptCode());
				objectToPatch.setDepartment(departmentMaster);
			}
			if(preferenceCardDTO.getProcedureCode()!=null){
				final ProcedureMaster procedureMaster=findAndValidateProcedureCode(preferenceCardDTO.getProcedureCode());
				objectToPatch.setProcedureMaster(procedureMaster);
			}
			if(preferenceCardDTO.getCardAgainst()!=null){
				objectToPatch.setCardAgainst(preferenceCardDTO.getCardAgainst());
			}

			if(preferenceCardDTO.getPreferenceCardOtherDetailsMaster()!=null){
						if(preferenceCardDTO.getPreferenceCardOtherDetailsMaster().getPostionDetails()!=null){
							objectToPatch.getPreferenceCardOtherDetailsMaster().setPostionDetails(preferenceCardDTO.getPreferenceCardOtherDetailsMaster().getPostionDetails());
						}
						if(preferenceCardDTO.getPreferenceCardOtherDetailsMaster().getPreparation()!=null){
							objectToPatch.getPreferenceCardOtherDetailsMaster().setPreparation(preferenceCardDTO.getPreferenceCardOtherDetailsMaster().getPreparation());
						}
			}

			if(preferenceCardDTO.getPreferenceCardDetailsMaster()!=null) {

				if (preferenceCardDTO.getPreferenceCardDetailsMaster().size() > 0) {

					List<PreferenceCardDetailsMaster> preferenceCardDetailsList = new ArrayList<PreferenceCardDetailsMaster>();
					for (PreferenceCardDetailsMaster preferenceCardDetailsObj:objectToPatch.getPreferenceCardDetailsMaster()){
						preferenceCardDetailsObj.setActive(false);
						preferenceCardDetailsList.add(preferenceCardDetailsObj);
					}
					objectToPatch.setPreferenceCardDetailsMaster(preferenceCardDetailsList);
					for (PreferenceCardDetailsMaster preferenceCardDetailsMaster : preferenceCardDTO.getPreferenceCardDetailsMaster()) {
						//if(preferenceCardDetailsList.get
						if ("blades/sutures".equalsIgnoreCase(preferenceCardDetailsMaster.getCartType())) {
							objectToPatch.getPreferenceCardDetailsMaster().removeIf(del -> "blades/sutures".equalsIgnoreCase(del.getCartType()));
							preferenceCardDetailsList = setItemMasterInList(preferenceCardDetailsMaster, objectToPatch);
							preferenceCardDetailsListT.add(preferenceCardDetailsMaster);
						}
						if ("instrumentSets".equalsIgnoreCase(preferenceCardDetailsMaster.getCartType())) {
							objectToPatch.getPreferenceCardDetailsMaster().removeIf(del -> "instrumentSets".equalsIgnoreCase(del.getCartType()));
							preferenceCardDetailsList = setItemMasterInList(preferenceCardDetailsMaster, objectToPatch);
							preferenceCardDetailsListT.add(preferenceCardDetailsMaster);
						}
						if ("equipment".equalsIgnoreCase(preferenceCardDetailsMaster.getCartType())) {
							objectToPatch.getPreferenceCardDetailsMaster().removeIf(del -> "equipment".equalsIgnoreCase(del.getCartType()));
							preferenceCardDetailsList = setItemMasterInList(preferenceCardDetailsMaster, objectToPatch);
							preferenceCardDetailsListT.add(preferenceCardDetailsMaster);
						}
						if ("consumables".equalsIgnoreCase(preferenceCardDetailsMaster.getCartType())) {
							objectToPatch.getPreferenceCardDetailsMaster().removeIf(del -> "consumables".equalsIgnoreCase(del.getCartType()));
							preferenceCardDetailsList = setItemMasterInList(preferenceCardDetailsMaster, objectToPatch);
							preferenceCardDetailsListT.add(preferenceCardDetailsMaster);
						}
						if ("implants".equalsIgnoreCase(preferenceCardDetailsMaster.getCartType())) {
							objectToPatch.getPreferenceCardDetailsMaster().removeIf(del -> "implants".equalsIgnoreCase(del.getCartType()));

							preferenceCardDetailsList = setItemMasterInList(preferenceCardDetailsMaster, objectToPatch);
							preferenceCardDetailsListT.add(preferenceCardDetailsMaster);
						}
						if ("services".equalsIgnoreCase(preferenceCardDetailsMaster.getCartType())) {

						}
						preferenceCardDetailsMaster.setActive(false);
					}
					objectToPatch.setPreferenceCardDetailsMaster(preferenceCardDetailsList);

				}
			}
			}

		preferenceCardMasterResponse=preferenceCardRepository.save(objectToPatch);
		return preferenceCardMasterResponse;

	}



	private List<PreferenceCardDetailsMaster> setItemMasterInList(PreferenceCardDetailsMaster preferenceCardDetailsMaster, PreferenceCardMaster preferenceCardMaster){
		List<PreferenceCardDetailsMaster> preferenceCardDetailsMasterList=new ArrayList<PreferenceCardDetailsMaster>();
		if (preferenceCardDetailsMaster.getItemMaster() != null) {
			Long itemId = Long.parseLong(preferenceCardDetailsMaster.getItemMaster().getId().toString());
			ItemMaster itemMaster = itemMasterRepository.findById(itemId).orElse(null);
			preferenceCardDetailsMaster.setItemMaster(itemMaster);
			preferenceCardDetailsMaster.setPreferenceCardMaster(preferenceCardMaster);
			preferenceCardDetailsMaster.setActive(true);
			preferenceCardDetailsMaster.setCreatedDate(new Date());
		}
		preferenceCardDetailsMasterList.add(preferenceCardDetailsMaster);
		return preferenceCardDetailsMasterList;
	}

	/**
	 * Validates the given DTO.
	 * @param object a dto.
	 */
	private void verifyNotNull(final Object object) {
		if(object == null) {
			logger.error("invalid request.");
			throw new IllegalArgumentException("invalid request.");
		}
	}

	/**
	 * Validates and finds the {@link OrganizationMaster} for the given org code,
	 *
	 * @param orgCode a valid org code.
	 * @return {@link OrganizationMaster} if valid. Else null.
	 * @throws IllegalArgumentException if org code missing.
	 */
	private OrganizationMaster findAndValidateOrgCode(final String orgCode) {
		if (StringUtils.isBlank(orgCode)) {
			logger.error("Unit header missing. {}", orgCode);
			throw new IllegalArgumentException("Org code missing");
		}

		return organizationMasterRepository.findByCode(orgCode);
	}

	/**
	 * Validates and finds the {@link UnitMaster} for the given unit code,
	 *
	 * @param unitCode a valid unit code.
	 * @return {@link UnitMaster} if valid. Else null.
	 * @throws IllegalArgumentException if unit header missing.
	 */
	private UnitMaster findAndValidateUnitCode(final String unitCode) {
		if (StringUtils.isBlank(unitCode)) {
			logger.error("Unit header missing. {}", unitCode);
			throw new IllegalArgumentException("Unit header missing");
		}

		return unitMasterRepository.findByCode(unitCode);
	}

	/**
	 * Validates and finds the {@link DepartmentMaster} for the given unit code,
	 *
	 * @param departmentCode a valid unit code.
	 * @return {@link DepartmentMaster} if valid. Else null.
	 * @throws IllegalArgumentException if unit header missing.
	 */
	private DepartmentMaster findAndValidateDepartmentCode(final String departmentCode) {
		if (StringUtils.isBlank(departmentCode)) {
			logger.error("Department Code missing. {}", departmentCode);
			throw new IllegalArgumentException("Department Code missing");
		}

		return departmentMasterRepository.findByCode(departmentCode);
	}

	/**
	 * Validates and finds the {@link ProcedureMaster} for the given unit code,
	 *
	 * @param procedureCode a valid unit code.
	 * @return {@link ProcedureMaster} if valid. Else null.
	 * @throws IllegalArgumentException if unit header missing.
	 */
	private ProcedureMaster findAndValidateProcedureCode(final String procedureCode) {
		if (StringUtils.isBlank(procedureCode)) {
			logger.error("Procedure Code missing. {}", procedureCode);
			throw new IllegalArgumentException("Procedure Code missing");
		}

		return procedureRepository.findByCode(procedureCode);
	}


	private ItemMaster findAndValidateItemMaster(String code){
		if(StringUtils.isBlank(code)){
			logger.error("Item Code missing. {}", code);
			throw new IllegalArgumentException("Item Code missing");
		}

		return itemMasterRepository.findByCodeIgnoreCase(code);
	}
}
