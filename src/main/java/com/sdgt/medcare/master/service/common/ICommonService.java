package com.sdgt.medcare.master.service.common;

import com.sdgt.medcare.master.model.MarkupDetailSaveRequest;

public interface ICommonService {
    Object getItemByItemTypeId(Long itemTypeId);

    Object addMarkupMaster(MarkupDetailSaveRequest markupDetailSaveRequest);

    Object updateItemMaster(String itemCode);

    Object getServiceProvider();

    Object getItemsByKitId(Long kitId);
}
