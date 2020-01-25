package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.ItemMaster;
import com.sdgt.medcare.master.entity.global.SupplierMaster;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface SupplierMasterRepository extends MastersBaseRepository<SupplierMaster,Long>, SupplierMasterRepositoryCustom{
}
