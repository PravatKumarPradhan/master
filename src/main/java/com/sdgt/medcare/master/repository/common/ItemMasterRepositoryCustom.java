package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.dto.common.ItemSearchRequest;
import com.sdgt.medcare.master.dto.common.ItemsRequest;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ItemMasterRepositoryCustom   {
    List<ItemMaster> getItemByItemTypeId(Long itemTypeId);

    ItemMaster getItemMasterByItemId(String itemCode);

    List<ItemMaster> getLinenItemList(ItemsRequest itemsRequest);

    Object getAllItemList(ItemSearchRequest searchRequest, PageRequest pageRequest);
}
