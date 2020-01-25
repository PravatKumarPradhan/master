package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.dto.common.SupplierSearchRequest;
import org.springframework.data.domain.PageRequest;

public interface SupplierMasterRepositoryCustom {

    Object getAllSupplierList(SupplierSearchRequest searchRequest, PageRequest pageRequest);
}
