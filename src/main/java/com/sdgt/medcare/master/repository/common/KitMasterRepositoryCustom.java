package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.inventory.KitDetailMaster;

import java.util.List;

public interface KitMasterRepositoryCustom {

    List<KitDetailMaster> getDetailsByKitId(Long kitId);
}
