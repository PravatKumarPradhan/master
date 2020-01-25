package com.sdgt.medcare.master.controller.common;

import com.core.base.rest.controller.RestWSController;
import com.core.base.util.RESTCommonAssistant;
import com.sdgt.medcare.master.dto.common.*;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.model.CommonSuccessResponse;
import com.sdgt.medcare.master.model.UpdateDrugRecall;
import com.sdgt.medcare.master.repository.common.ItemMasterRepository;
import com.sdgt.medcare.master.repository.common.ItemUnitRepository;
import com.sdgt.medcare.master.repository.common.UnitOfMeasurementRepository;
import com.sdgt.medcare.master.repository.common.UomTypeRepository;
import com.sdgt.medcare.master.service.common.ItemMasterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Sachin Raghywanshi
 * @author Karthik Chandra
 */
@CrossOrigin
@RequestMapping("/item")
@RestController
public class ItemMasterController extends RestWSController<ItemMaster> {

    @Autowired
    ItemMasterService itemMasterService;

    @Autowired
    ItemMasterRepository itemMasterRepository;

    @Autowired
    ItemUnitRepository itemUnitRepository;

    @Autowired
    UomTypeRepository uomTypeRepository;

    @Autowired
    UnitOfMeasurementRepository unitOfMeasurementRepository;


    @PatchMapping(value = "/updateDrugRecall",
          consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Object updateDrugRecall(@RequestBody UpdateDrugRecall updateDrugRecall) {
        ItemMaster itemMaster = itemMasterRepository.findByCodeIgnoreCase(updateDrugRecall.getItemCode());
        if (itemMaster != null) {
            itemMaster.setDrugRecall(updateDrugRecall.getDrugRecall());
            itemMasterRepository.save(itemMaster);
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemMaster);
    }

    @PostMapping(value = "/addItem",
          consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Object insert(@RequestBody ItemMaster itemMaster) {
        if (itemMaster != null) {
            return itemMasterService.insert(itemMaster);
        }
        return new CommonSuccessResponse("itemMaster should not be null", null);
    }

    @PostMapping(value = "/getLinenItemList",
          consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Object getItemListForLinen(@RequestBody ItemListRequest itemListRequest) {
        if (itemListRequest != null) {
            return itemMasterService.getLinenItemList(itemListRequest);
        } else {
            return new CommonSuccessResponse("itemMaster should not be null", null);
        }
    }

    @PatchMapping(value = "/updateItem/{id}",
          consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Object update(@RequestBody ItemMaster itemMaster, @PathVariable Long id) {
        if (itemMaster != null) {
            return itemMasterService.update(itemMaster, id);
        } else {
            return new CommonSuccessResponse("Error", null);
        }
    }

    @GetMapping(value = "/itemDetailById/{id}")
    ResponseEntity<Object> getDetailById(@PathVariable Long id) {

        return new ResponseEntity<>(itemMasterService.getDetailById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/list",
          consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Object getAllItemList(@Valid @RequestBody ItemSearchRequest searchRequest) {
        if (searchRequest != null) {
            return itemMasterService.getAllItemList(searchRequest);
        } else {
            return new CommonSuccessResponse("Error", null);
        }
    }

    /*
     * DTO Based.
     *
     * @since 29th October 2019, @author Karthik Chandra
     */

    /**
     * Api to get service.
     *
     * @param key   given key [ID,CODE,ITEMGROUPCODE,ITEMCATEGORYCODE,DESC, ]
     * @param value corresponding value
     * @return list of {@link ServiceDTO}
     */
    @ApiOperation(value = "Fetch item details by given [id, code, desc, itemGroupCode, itemCategoryCode,<empty>]", response = Collection.class)
    @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping({"/", "/{key}/{value}"})
    public ResponseEntity<Collection<ItemDTO>> findService(final @PathVariable(value = "key", required = false) String key,
                                                           final @PathVariable(value = "value", required = false) String value) {
        Collection<ItemDTO> itemDTOCollection = new HashSet<>();
        if (StringUtils.isNotBlank(key)) {
            switch (key.toLowerCase()) {
                case "id":
                    itemDTOCollection = itemMasterService.findByItemId(value);
                    break;
                case "code":
                    itemDTOCollection = itemMasterService.findByItemCode(value);
                    break;
                case "itemgroupcode":
                    itemDTOCollection = itemMasterService.findByItemGroupCode(value);
                    break;
                case "itemcategorycode":
                    itemDTOCollection = itemMasterService.findByItemCategoryCode(value);
                    break;
                case "desc":
                    itemDTOCollection = itemMasterService.findByDesc(value);
                    break;
                default:
                    itemDTOCollection = itemMasterService.findAllItems();
            }
        } else {
            itemDTOCollection = itemMasterService.findAllItems();
        }

        if (CollectionUtils.isEmpty(itemDTOCollection)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(itemDTOCollection);
    }

    /**
     * Api to get list of service by given ids.
     *
     * @param ids given.
     * @return list of {@link ServiceDTO}
     */
    @ApiOperation(value = "Fetch service details by given [id, code, desc, groupCode, subGroupCode,<empty>]", response = ResponseEntity.class)
    @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping({"/ids"})
    public @ResponseBody
    ResponseEntity findByIds(final @RequestBody Collection<String> ids) {
        return RESTCommonAssistant.buildListResponse(itemMasterService.findByIds(ids));
    }

    /**
     * Api to get list of service by given ids.
     *
     * @param codes given.
     * @return list of {@link ServiceDTO}
     */
    @ApiOperation(value = "Fetch service details by given [id, code, desc, groupCode, subGroupCode,<empty>]", response = ResponseEntity.class)
    @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping({"/codes"})
    public @ResponseBody
    ResponseEntity findByCodes(final @RequestBody Collection<String> codes) {
        return RESTCommonAssistant.buildListResponse(itemMasterService.findByCodes(codes));
    }
}
