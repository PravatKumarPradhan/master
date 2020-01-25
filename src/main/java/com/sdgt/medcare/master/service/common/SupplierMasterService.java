package com.sdgt.medcare.master.service.common;

import com.core.service.BaseService;
import com.sdgt.medcare.master.dto.common.SupplierSearchRequest;
import com.sdgt.medcare.master.repository.common.ItemMasterRepository;
import com.sdgt.medcare.master.repository.common.SupplierMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Shrutika Bhiwgade
 */

@Service
public class SupplierMasterService extends BaseService<SupplierMasterService> {

    private Logger logger = LoggerFactory.getLogger(ItemMasterService.class);

    @Autowired
    SupplierMasterRepository supplierMasterRepository;

    public Object getAllSupplierList(SupplierSearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getOffset(), searchRequest.getLimit());
        return supplierMasterRepository.getAllSupplierList(searchRequest, pageRequest);
    }
}
