package com.sdgt.medcare.master.service.common;


import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.core.service.BaseService;
import com.sdgt.medcare.master.dto.common.ItemDTO;
import com.sdgt.medcare.master.dto.common.ItemListRequest;
import com.sdgt.medcare.master.dto.common.ItemSearchRequest;
import com.sdgt.medcare.master.dto.common.ItemsRequest;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.AnalysisTypeMaster;
import com.sdgt.medcare.master.entity.global.ConsumptionTypeMaster;
import com.sdgt.medcare.master.entity.global.FormulationTypeMaster;
import com.sdgt.medcare.master.entity.global.GenericMaster;
import com.sdgt.medcare.master.entity.global.ItemCategoryMaster;
import com.sdgt.medcare.master.entity.global.ItemGroupMaster;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.global.ItemTypeMaster;
import com.sdgt.medcare.master.entity.global.MarkupMaster;
import com.sdgt.medcare.master.entity.global.PharmacologyClassificationMaster;
import com.sdgt.medcare.master.entity.global.PregnancyClassMaster;
import com.sdgt.medcare.master.entity.global.RouteOfAdministrationMaster;
import com.sdgt.medcare.master.entity.global.StorageUnitMaster;
import com.sdgt.medcare.master.entity.global.StrengthUnitMaster;
import com.sdgt.medcare.master.entity.global.TaxMaster;
import com.sdgt.medcare.master.entity.global.UsageType;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.unit.StoreMaster;
import com.sdgt.medcare.master.entity.unit.UnitOfMeasurementMaster;
import com.sdgt.medcare.master.model.CommonSuccessResponse;
import com.sdgt.medcare.master.model.Uom;
import com.sdgt.medcare.master.repository.common.AnalysisTypeRepository;
import com.sdgt.medcare.master.repository.common.ConsumptionTypeRepository;
import com.sdgt.medcare.master.repository.common.FormulationTypeRepository;
import com.sdgt.medcare.master.repository.common.GenericRepository;
import com.sdgt.medcare.master.repository.common.ItemCategoryMasterRepository;
import com.sdgt.medcare.master.repository.common.ItemGroupMasterRepository;
import com.sdgt.medcare.master.repository.common.ItemMasterRepository;
import com.sdgt.medcare.master.repository.common.ItemTypeRepository;
import com.sdgt.medcare.master.repository.common.ItemUnitRepository;
import com.sdgt.medcare.master.repository.common.MarkupRepository;
import com.sdgt.medcare.master.repository.common.OrganizationMasterRepository;
import com.sdgt.medcare.master.repository.common.PharmacologyClassificationRepository;
import com.sdgt.medcare.master.repository.common.PregnancyClassRepository;
import com.sdgt.medcare.master.repository.common.RouteOfAdministrationRepository;
import com.sdgt.medcare.master.repository.common.StorageUnitRepository;
import com.sdgt.medcare.master.repository.common.StoreRepository;
import com.sdgt.medcare.master.repository.common.StrengthUnitRepository;
import com.sdgt.medcare.master.repository.common.TaxRepository;
import com.sdgt.medcare.master.repository.common.UnitMasterRepository;
import com.sdgt.medcare.master.repository.common.UnitOfMeasurementRepository;
import com.sdgt.medcare.master.repository.common.UomTypeRepository;
import com.sdgt.medcare.master.repository.common.UsageTypeRepository;
import com.sdgt.medcare.master.util.NumberParseAssistant;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Sachin Raghuwanshu
 */

@Service
public class ItemMasterService extends BaseService<ItemMasterService> {
    /**
     * @since 29th October 2019, @author Karthik Chandra
     */
    private Logger logger = LoggerFactory.getLogger(ItemMasterService.class);
    @Autowired
    ItemMasterRepository itemMasterRepository;

    @Autowired
    OrganizationMasterRepository organizationMasterRepository;

    @Autowired
    UnitMasterRepository unitMasterRepository;

    @Autowired
    AnalysisTypeRepository analysisTyperRepository;

    @Autowired
    ItemCategoryMasterRepository itemCategoryRepository;

    @Autowired
    ItemTypeRepository itemTypeRepository;

    @Autowired
    ItemGroupMasterRepository itemGroupRepository;

    @Autowired
    GenericRepository genericRepository;

    @Autowired
    FormulationTypeRepository formulationTypeRepository;

    @Autowired
    RouteOfAdministrationRepository routeOfAdministrationRepository;

    @Autowired
    PharmacologyClassificationRepository pharmacologyClassificationRepository;

    @Autowired
    StrengthUnitRepository strengthUnitRepository;

    @Autowired
    TaxRepository taxRepository;

    @Autowired
    MarkupRepository markupRepository;

    @Autowired
    StorageUnitRepository storageUnitRepository;

    @Autowired
    UsageTypeRepository usageTypeRepository;

    @Autowired
    PregnancyClassRepository pregnancyClassRepository;

    @Autowired
    ItemUnitRepository itemUnitRepository;

    @Autowired
    ConsumptionTypeRepository consumptionTypeRepository;

    @Autowired
    UomTypeRepository uomTypeRepository;

    @Autowired
    UnitOfMeasurementRepository unitOfMeasurementRepository;

  @Autowired
    StoreRepository storeRepository;



    public ItemMasterService(ItemMasterRepository itemMasterRepository) {
        this.itemMasterRepository = itemMasterRepository;
    }


    /**
     * @param itemMaster
     * @param id
     * @return
     */

    @Transactional
    public Object update(ItemMaster itemMaster, Long id) {
        Optional<ItemMaster> optional = itemMasterRepository.findById(id);
        if (optional.isPresent()) {
            ItemMaster updateItemMaster = optional.get();
            String createdByUpdatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
            OrganizationMaster organizationMaster = organizationMasterRepository.getOne(Long.valueOf(itemMaster.getOrganizationMaster().getId()));

            Optional<TaxMaster> purchaseTaxOptional = taxRepository.findById(Long.valueOf(itemMaster.getPurchaseTaxMaster().getId()));
            if (purchaseTaxOptional.isPresent()) {
                updateItemMaster.setPurchaseTaxMaster(purchaseTaxOptional.get());
            }
            Optional<TaxMaster> saleTaxOptional = taxRepository.findById(Long.valueOf(itemMaster.getSaleTaxMaster().getId()));
            if (saleTaxOptional.isPresent()) {
                updateItemMaster.setSaleTaxMaster(saleTaxOptional.get());
            }
            if (itemMaster.getMarkupMaster() != null) {
                MarkupMaster markupMaster = markupRepository.getOne(Long.valueOf(itemMaster.getMarkupMaster().getId()));
                updateItemMaster.setMarkupMaster(markupMaster);
            }
            if (itemMaster.getStrengthUnitMaster() != null) {
                StrengthUnitMaster strengthUnitMaster = strengthUnitRepository.getOne(Long.valueOf(itemMaster.getStrengthUnitMaster().getId()));
                updateItemMaster.setStrengthUnitMaster(strengthUnitMaster);
            }
            if(itemMaster.getUsageType() != null){
            UsageType usageType = usageTypeRepository.getOne(Long.valueOf(itemMaster.getUsageType().getId()));
            updateItemMaster.setUsageType(usageType);
            }
            if(itemMaster.getPregnancyClass()!=null) {
                PregnancyClassMaster pregnancyClassMaster = pregnancyClassRepository.getOne(Long.valueOf(itemMaster.getPregnancyClass().getId()));
                updateItemMaster.setPregnancyClass(pregnancyClassMaster);
            }
            if(itemMaster.getConsumptionTypeMaster()!=null) {
                ConsumptionTypeMaster consumptionTypeMaster = consumptionTypeRepository.getOne(Long.valueOf(itemMaster.getConsumptionTypeMaster().getId()));
                updateItemMaster.setConsumptionTypeMaster(consumptionTypeMaster);
            }
            if(itemMaster.getRouteOfAdministrationMaster()!=null) {
                RouteOfAdministrationMaster routeOfAdministrationMaster = routeOfAdministrationRepository.getOne(Long.valueOf(itemMaster.getRouteOfAdministrationMaster().getId()));
                updateItemMaster.setRouteOfAdministrationMaster(routeOfAdministrationMaster);
            }
            if(itemMaster.getItemGroupMaster()!=null) {
                ItemGroupMaster itemGroupMaster = itemGroupRepository.getOne(Long.valueOf(itemMaster.getItemGroupMaster().getId()));
                updateItemMaster.setItemGroupMaster(itemGroupMaster);
            }
            if(itemMaster.getItemTypeMaster()!=null) {
                ItemTypeMaster itemTypeMaster = itemTypeRepository.getOne(Long.valueOf(itemMaster.getItemTypeMaster().getId()));
                updateItemMaster.setItemTypeMaster(itemTypeMaster);
            }
            if(itemMaster.getAnalysisTypeMaster() != null){
                AnalysisTypeMaster analysisTypeMaster = analysisTyperRepository.getOne(Long.valueOf(itemMaster.getAnalysisTypeMaster().getId()));
                updateItemMaster.setAnalysisTypeMaster(analysisTypeMaster);
            }
            if(itemMaster.getItemCategoryMaster() != null){
                ItemCategoryMaster itemCategoryMaster = itemCategoryRepository.getOne(Long.valueOf(itemMaster.getItemCategoryMaster().getId()));
                updateItemMaster.setItemCategoryMaster(itemCategoryMaster);
            }
            if(itemMaster.getGenericMaster() != null){
                GenericMaster genericMaster = genericRepository.getOne(Long.valueOf(itemMaster.getGenericMaster().getId()));
                updateItemMaster.setGenericMaster(genericMaster);
            }
            if(itemMaster.getFormulationTypeMaster() != null){
                FormulationTypeMaster formulationTypeMaster = formulationTypeRepository.getOne(Long.valueOf(itemMaster.getFormulationTypeMaster().getId()));
                updateItemMaster.setFormulationTypeMaster(formulationTypeMaster);
            }
            if(itemMaster.getPharmacologyClassificationMaster() != null){
                PharmacologyClassificationMaster pharmacologyClassificationMaster = pharmacologyClassificationRepository.getOne(Long.valueOf(itemMaster.getPharmacologyClassificationMaster().getId()));
                updateItemMaster.setPharmacologyClassificationMaster(pharmacologyClassificationMaster);
            }
            if(itemMaster.getStorageUnitMaster() != null) {
                StorageUnitMaster storageUnitMaster = storageUnitRepository.getOne(Long.valueOf(itemMaster.getStorageUnitMaster().getId()));
                updateItemMaster.setStorageUnitMaster(storageUnitMaster);
            }

            updateItemMaster.setOrganizationMaster(organizationMaster);
            updateItemMaster.setBatchRequired(itemMaster.getBatchRequired());
            updateItemMaster.setCode(itemMaster.getCode());
            updateItemMaster.setDesc(itemMaster.getDesc());
            updateItemMaster.setConsignment(itemMaster.getConsignment());
            updateItemMaster.setStrength(itemMaster.getStrength()!=null ? itemMaster.getStrength(): 0);
            updateItemMaster.setConsumable(itemMaster.getConsumable());
            updateItemMaster.setCostPrice(itemMaster.getCostPrice());
            updateItemMaster.setDrug(itemMaster.getDrug());
            updateItemMaster.setVaccine(itemMaster.getVaccine());
            updateItemMaster.setSalePrice(itemMaster.getSalePrice());
            updateItemMaster.setReusable(itemMaster.getReusable());
            updateItemMaster.setReplenishment(itemMaster.getReplenishment());
            updateItemMaster.setPsychotropic(itemMaster.getPsychotropic());
            updateItemMaster.setProductCode(itemMaster.getProductCode());
            updateItemMaster.setOtc(itemMaster.getOtc());
            updateItemMaster.setNoTimeReuse(itemMaster.getNoTimeReuse());
            updateItemMaster.setNonStock(itemMaster.getNonStock());
            updateItemMaster.setNarcotics(itemMaster.getNarcotics());
            updateItemMaster.setMimsType(itemMaster.getMimsType());
            updateItemMaster.setMimsMy(itemMaster.getMimsMy());
            updateItemMaster.setMarketedBy(itemMaster.getMarketedBy());
            updateItemMaster.setManufacturedBy(itemMaster.getManufacturedBy());
            updateItemMaster.setKit(itemMaster.getKit());
            updateItemMaster.setInfusion(itemMaster.getInfusion());
            updateItemMaster.setHighRisk(itemMaster.getHighRisk());
            updateItemMaster.setFixedPrice(itemMaster.getFixedPrice());
            updateItemMaster.setDosage(itemMaster.getDosage());
            updateItemMaster.setPharmacy(itemMaster.getPharmacy());
            updateItemMaster.setIndent(itemMaster.getIndent());
            updateItemMaster.setCreatedBy(createdByUpdatedBy);
            updateItemMaster.setUpdatedBy(createdByUpdatedBy);
            updateItemMaster.setCreatedDate(new Date());
            updateItemMaster.setUpdatedDate(new Date());
            updateItemMaster = itemMasterRepository.save(updateItemMaster);
            List<Uom> uomList = itemMaster.getUom();
            List<UnitOfMeasurementMaster> unitOfMList = unitOfMeasurementRepository.findByItemId(Long.valueOf(updateItemMaster.getId()));
            List<UnitOfMeasurementMaster> unitOfMeasurementMasters = new ArrayList<>();
            for(UnitOfMeasurementMaster unitOfMeasurementMaster: unitOfMList) {
                boolean isPresent=true;
                for (Uom uom : uomList) {
                    if(unitOfMeasurementMaster.getId().equals(uom.getId())) {
                        isPresent=false;
                        unitOfMeasurementMaster.setItemMaster(updateItemMaster);
                        unitOfMeasurementMaster.setOrganizationMaster(updateItemMaster.getOrganizationMaster());
                        unitOfMeasurementMaster.setUomTypeMaster(uomTypeRepository.getOne(uom.getUomTypeId()));
                        unitOfMeasurementMaster.setItemUnitMaster(itemUnitRepository.getOne(uom.getUomUnitId()));
                        unitOfMeasurementMaster.setConversion(uom.getConversion());
                        unitOfMeasurementMaster.setIpUom(uom.getIpUom());
                        unitOfMeasurementMaster.setOpUom(uom.getOpUom());
                        unitOfMeasurementMaster.setStoreUom(uom.getStoreUom());
                    }
                }
                if(isPresent){
                    unitOfMeasurementMaster.setActive(false);
                }
            }
            for (Uom uom : uomList){
                if(uom.getId()==null || uom.getId().isEmpty()){
                    UnitOfMeasurementMaster unitOfMeasurementMaster = new UnitOfMeasurementMaster();
                    unitOfMeasurementMaster.setItemMaster(updateItemMaster);
                    unitOfMeasurementMaster.setOrganizationMaster(updateItemMaster.getOrganizationMaster());
                    unitOfMeasurementMaster.setUomTypeMaster(uomTypeRepository.getOne(uom.getUomTypeId()));
                    unitOfMeasurementMaster.setItemUnitMaster(itemUnitRepository.getOne(uom.getUomUnitId()));
                    unitOfMeasurementMaster.setConversion(uom.getConversion());
                    unitOfMeasurementMaster.setIpUom(uom.getIpUom());
                    unitOfMeasurementMaster.setOpUom(uom.getOpUom());
                    unitOfMeasurementMaster.setStoreUom(uom.getStoreUom());
                    unitOfMeasurementMasters.add(unitOfMeasurementMaster);
                }
            }
            unitOfMeasurementRepository.saveAll(unitOfMeasurementMasters);
            return new CommonSuccessResponse("Update Successfully", null);
        } else {
            return null;
        }

    }

    /**
     * @param itemMaster
     * @return
     * Add New ItemMaster
     */
    public Object insert(ItemMaster itemMaster) {
        itemMaster.setCreatedDate(new Date());
        itemMaster.setCreatedBy(HttpUtils.getHeader(HttpHeaders.USER_ID));
        itemMaster.setCreatedBy(HttpUtils.getHeader(HttpHeaders.USER_NAME));
        List<Uom> uomList = itemMaster.getUom();
        itemMaster = itemMasterRepository.save(itemMaster);
        List<UnitOfMeasurementMaster> unitOfMeasurementMasters = new ArrayList<>();
            for (Uom uom : uomList) {
                UnitOfMeasurementMaster unitOfMeasurementMaster = new UnitOfMeasurementMaster();
                    unitOfMeasurementMaster.setItemMaster(itemMaster);
                    unitOfMeasurementMaster.setOrganizationMaster(itemMaster.getOrganizationMaster());
                    unitOfMeasurementMaster.setUomTypeMaster(uomTypeRepository.getOne(uom.getUomTypeId()));
                    unitOfMeasurementMaster.setItemUnitMaster(itemUnitRepository.getOne(uom.getUomUnitId()));
                    unitOfMeasurementMaster.setConversion(uom.getConversion());
                    unitOfMeasurementMaster.setIpUom(uom.getIpUom());
                    unitOfMeasurementMaster.setOpUom(uom.getOpUom());
                    unitOfMeasurementMaster.setStoreUom(uom.getStoreUom());
                    unitOfMeasurementMasters.add(unitOfMeasurementMaster);
                }
        unitOfMeasurementRepository.saveAll(unitOfMeasurementMasters);
        return new CommonSuccessResponse("Successfully Added", null);
    }

    /**
     * @param id
     * @return
     * Get ItemMaster By Id
     */
    public Object getDetailById(Long id) {

        Optional<ItemMaster> optional = itemMasterRepository.findById(id);
        if (optional.isPresent()) {
            ItemMaster itemMaster = optional.get();
            List<UnitOfMeasurementMaster> uomList = unitOfMeasurementRepository.findByItemId(id);
            List<Uom> uoms = new ArrayList<>();
            for (UnitOfMeasurementMaster unitOfMeasurementMaster : uomList) {
                Uom uom = new Uom(unitOfMeasurementMaster.getId(), Long.parseLong(unitOfMeasurementMaster.getUomTypeMaster().getId()), Long.parseLong(unitOfMeasurementMaster.getItemUnitMaster().getId()),
                        unitOfMeasurementMaster.getConversion(), unitOfMeasurementMaster.getIpUom(), unitOfMeasurementMaster.getOpUom(),
                        unitOfMeasurementMaster.getStoreUom());
                uoms.add(uom);

            }
            itemMaster.setUom(uoms);
            return new CommonSuccessResponse("Successfully",itemMaster);
//            return new Gson().toJson(itemMaster);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonSuccessResponse("Invalid Id", null));

        }
    }

    public Object getLinenItemList(ItemListRequest itemListRequest) {
        ItemsRequest itemsRequest = new ItemsRequest();
        if (itemListRequest.getRequestingStoreCode()!=null && !itemListRequest.getRequestingStoreCode().isEmpty()) {
            StoreMaster storeMaster = storeRepository.findByCode(itemListRequest.getRequestingStoreCode());
            if(storeMaster!=null) {
                itemsRequest.setRequestingStoreId(Long.valueOf(storeMaster.getId()));
            }
        }

        if (itemListRequest.getIssuingStoreCode()!=null && !itemListRequest.getIssuingStoreCode().isEmpty()) {
            StoreMaster storeMaster = storeRepository.findByCode(itemListRequest.getIssuingStoreCode());
            if(storeMaster!=null) {
                itemsRequest.setIssuingStoreId(Long.valueOf(storeMaster.getId()));
            }
        }

        return itemMasterRepository.getLinenItemList(itemsRequest);
    }

    /*
     * DTO Based.
     *
     * @since 29th October 2019, @author Karthik Chandra
     */

    /**
     *  Find all {@link ItemMaster}
     *
     * @return list.
     */
    public Collection<ItemDTO> findAllItems() {
        return translate(itemMasterRepository.findAll());
    }

    /**
     * Find by Id {@link ItemMaster}
     *
     * @param id given.
     * @return list of {@link ItemDTO}
     */
    public Collection<ItemDTO> findByItemId(final String id) {
        final Optional<ItemMaster> itemMasterOptional = itemMasterRepository.findById(NumberParseAssistant.parseLong(id));
        return itemMasterOptional.map(itemMaster -> translate(Collections.singleton(itemMaster))).orElse(Collections.emptySet());
    }

    /**
     * Find by code {@link ItemMaster}
     *
     * @param code given.
     * @return list of {@link ItemDTO}
     */
    public Collection<ItemDTO> findByItemCode(final String code) {
        final ItemMaster itemMaster = itemMasterRepository.findByCodeIgnoreCase(code);

        if (itemMaster == null) {
            logger.error("findByItemCode; item code = {} not found", code);
            return Collections.emptySet();
        }

        return translate(Collections.singleton(itemMaster));
    }

    /**
     * Find by Ids {@link ItemMaster}
     *
     * @param ids given.
     * @return list of {@link ItemDTO}
     */
    public Collection<ItemDTO> findByIds(final Collection<String> ids) {
        return translate(itemMasterRepository.findAllById(ids.stream()
                .map(NumberParseAssistant::parseLong)
                .collect(Collectors.toSet())));
    }

    /**
     * Find by codes {@link ItemMaster}
     *
     * @param codes given.
     * @return list of {@link ItemDTO}
     */
    public Collection<ItemDTO> findByCodes(final Collection<String> codes) {
        return translate(itemMasterRepository.findAllByCode(codes));
    }

    /**
     * Find by description {@link ItemMaster}
     *
     * @param desc given.
     * @return list of {@link ItemDTO}
     */
    public Collection<ItemDTO> findByDesc(final String desc) {
        final ItemMaster itemMaster = itemMasterRepository.findByDescIgnoreCase(desc);

        if (itemMaster == null) {
            logger.error("findByDesc; item description = {} not found", desc);
            return Collections.emptySet();
        }

        return translate(Collections.singleton(itemMaster));
    }

    /**
     * Find by {@link ItemGroupMaster#getCode()}
     * @param itemGroupCode given.
     * @return list of {@link ItemDTO}
     */
    public Collection<ItemDTO> findByItemGroupCode(final String itemGroupCode) {
        final ItemGroupMaster itemGroupMaster = itemGroupRepository.findByCodeIgnoreCase(itemGroupCode);
        if (itemGroupMaster == null) {
            logger.error("findByItemGroupCode; group code = {} not found", itemGroupCode);
            return Collections.emptySet();
        }
        final Collection<ItemMaster> itemMasterCollection = itemMasterRepository.findByItemGroupMaster(itemGroupMaster);

        return translate(itemMasterCollection);
    }

    /**
     * Find by {@link ItemCategoryMaster#getCode()}
     * @param itemCategoryCode given.
     * @return list of {@link ItemDTO}
     */
    public Collection<ItemDTO> findByItemCategoryCode(final String itemCategoryCode) {
        final ItemCategoryMaster itemCategoryMaster = itemCategoryRepository.findByCodeIgnoreCase(itemCategoryCode);
        if (itemCategoryMaster == null) {
            logger.error("findByItemCategoryCode; group code = {} not found", itemCategoryMaster);
            return Collections.emptySet();
        }
        final Collection<ItemMaster> itemMasterCollection = itemMasterRepository.findByItemCategoryMaster(itemCategoryMaster);

        return translate(itemMasterCollection);
    }


    /**
     * Translate Entity to DTO.
     *
     * @param itemMasterCollection given.
     * @return list of {@link ItemDTO}
     */
    private Collection<ItemDTO> translate(final Collection<ItemMaster> itemMasterCollection) {
        if (CollectionUtils.isEmpty(itemMasterCollection)) {
            return Collections.emptySet();
        }

        final Collection<ItemDTO> dtoCollection = new HashSet<>(itemMasterCollection.size());

        itemMasterCollection.forEach(itemMaster -> {
            final ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(itemMaster.getId());
            itemDTO.setCode(BaseMaster.getCode(itemMaster));
            itemDTO.setDesc(BaseMaster.getDesc(itemMaster));
            itemDTO.setItemGroupCode(BaseMaster.getCode(itemMaster.getItemCategoryMaster().getItemGroupMaster()));
            itemDTO.setItemGroupDesc(BaseMaster.getDesc(itemMaster.getItemCategoryMaster().getItemGroupMaster()));
            itemDTO.setItemCategoryCode(BaseMaster.getCode(itemMaster.getItemCategoryMaster()));
            itemDTO.setItemCategoryDesc(BaseMaster.getDesc(itemMaster.getItemCategoryMaster()));
            dtoCollection.add(itemDTO);
        });

        return dtoCollection;
    }

    public Object getAllItemList(ItemSearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getOffset(), searchRequest.getLimit());
        return itemMasterRepository.getAllItemList(searchRequest, pageRequest);
    }

}
