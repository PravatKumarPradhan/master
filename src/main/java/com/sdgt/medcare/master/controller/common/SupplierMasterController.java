package com.sdgt.medcare.master.controller.common;

import com.core.base.rest.controller.RestWSController;
import com.sdgt.medcare.master.dto.common.ItemSearchRequest;
import com.sdgt.medcare.master.dto.common.SupplierSearchRequest;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.global.SupplierMaster;
import com.sdgt.medcare.master.model.CommonSuccessResponse;
import com.sdgt.medcare.master.repository.common.SupplierMasterRepository;
import com.sdgt.medcare.master.service.common.SupplierMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Shrutika Bhiwgade
 * */

@CrossOrigin
@RequestMapping("/supplier")
@RestController
public class SupplierMasterController extends RestWSController<SupplierMaster> {

    @Autowired
    SupplierMasterService supplierMasterService;

    @Autowired
    SupplierMasterRepository supplierMasterRepository;

    @PostMapping(value = "/list",
            consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Object getAllItemList(@Valid @RequestBody SupplierSearchRequest searchRequest) {
        if (searchRequest != null) {
            return supplierMasterService.getAllSupplierList(searchRequest);
        } else {
            return new CommonSuccessResponse("Error", null);
        }
    }

}
