package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.dto.common.ItemSearchRequest;
import com.sdgt.medcare.master.dto.common.ItemsRequest;
import com.sdgt.medcare.master.entity.global.ItemMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional(readOnly = false)
public class ItemMasterRepositoryImpl implements ItemMasterRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ItemMaster> getItemByItemTypeId(Long itemTypeId) {
        List<ItemMaster> itemMasterList = new ArrayList<>();
        try {
            Query query = entityManager.createQuery(" Select im from ItemMaster as im" +
                    " Inner join im.itemCategoryMaster as itemCategory " +
                    " Inner join itemCategory.itemGroupMaster as ig" +
                    " Inner join ig.itemTypeMaster as it" +
                    " where it.id= :itemTypeId", ItemMaster.class);
            query.setParameter("itemTypeId", itemTypeId);
            itemMasterList = query.getResultList();
        } catch (Exception e) {
            throw e;
        }

        return itemMasterList;
    }

    @Override
    public ItemMaster getItemMasterByItemId(String itemCode) {
        ItemMaster itemMaster = null;
        try {
            Query query = entityManager.createQuery(" Select im from ItemMaster as im" +
                    " where im.code= :itemCode", ItemMaster.class);
            query.setParameter("itemCode", itemCode);
            itemMaster = (ItemMaster) query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
        return itemMaster;
    }

    @Override
    public List<ItemMaster> getLinenItemList(ItemsRequest itemsRequest) {
        try {
            String qu = "Select item from ItemMaster AS item "
                    + " INNER JOIN item.itemCategoryMaster AS itemCategory WITH itemCategory.active=true AND item.linenTypeMaster is not null "
                    + " INNER JOIN itemCategory.itemTypeMaster AS itemType WITH itemType.active=true "
                    + " AND (:itemTypeId = 0l OR itemType.id = :itemTypeId) "
                    + " AND ("
                    + " (:requestingStoreId = 0l ) OR item.itemCategoryMaster.id IN "
                    + " (SELECT storeProduct.itemCategoryMaster.id FROM StoreProductMaster AS storeProduct "
                    + "	WHERE storeProduct.storeMaster.id = :requestingStoreId AND storeProduct.active=true )"
                    + " ) "
                    + " AND ("
                    + " (:requestingStoreId = 0l ) OR item.id NOT IN (SELECT itemRestriction.itemMaster.id FROM ItemRestrictionMaster AS itemRestriction "
                    + "	WHERE itemRestriction.storeMaster.id = :requestingStoreId AND itemRestriction.active=true )"
                    + ") "
                    + " AND item.id IN (SELECT uom.itemMaster.id FROM UnitOfMeasurementMaster as uom "
                    + "	WHERE uom.active=true GROUP BY (uom.itemMaster.id))"
                    + " OR ((:issuingStoreId = 0l ) OR item.itemCategoryMaster.id IN (SELECT storeProduct.itemCategoryMaster.id FROM StoreProductMaster AS storeProduct "
                    + "	WHERE storeProduct.storeMaster.id = :issuingStoreId AND storeProduct.active=true )) "
                    + " AND ((:issuingStoreId = 0l ) OR item.id NOT IN (SELECT itemRestriction.itemMaster.id FROM ItemRestrictionMaster AS itemRestriction "
                    + "	WHERE itemRestriction.storeMaster.id = :issuingStoreId AND itemRestriction.active=true )) "
                    + " AND item.active=true  "
                    + " AND item.pharmacy = false AND item.indent = false AND item.drugRecall= false  "
                    + "ORDER BY item.desc ";
            ; // and "+"";
            qu = qu.replace("\"", "'");
            Query query = entityManager.createQuery(qu);
            query.setParameter("itemTypeId", itemsRequest.getItemTypeId() != null ? itemsRequest.getItemTypeId() : 0L);
            query.setParameter("requestingStoreId", itemsRequest.getRequestingStoreId() != null ? itemsRequest.getRequestingStoreId() : 0L);
            query.setParameter("issuingStoreId", itemsRequest.getIssuingStoreId() != null ? itemsRequest.getIssuingStoreId() : 0L);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object getAllItemList(ItemSearchRequest searchRequest, PageRequest pageRequest) {
        Page<ItemMaster> itemMasterList;
        try {
            Query query = entityManager.createQuery("SELECT item From ItemMaster AS item " +
                    " LEFT JOIN item.genericMaster AS generic " +
                    " LEFT JOIN item.itemCategoryMaster as itemCategory " +
                    " LEFT JOIN item.itemGroupMaster AS itemGroup " +
                    " LEFT JOIN item.itemTypeMaster as itemType " +
                    " WHERE item.active = true " +
                    " AND (:itemType is null OR itemType.id = :itemType) " +
                    " AND (:itemGroup is null OR itemGroup.id = :itemGroup) " +
                    " AND (:itemCategory is null OR itemCategory.id = :itemCategory) " +
                    " AND (:generic is null OR generic.id = :generic) " +
                    " AND (:itemCode = '' OR lower(item.code) LIKE :itemCode) " +
                    " AND (:itemName = '' OR lower(item.desc) LIKE :itemName) " +
                    " ORDER BY item.desc", ItemMaster.class);
            query.setParameter("itemType", searchRequest.getItemType().equals(0L) ? null : searchRequest.getItemType());
            query.setParameter("itemGroup", searchRequest.getItemGroup().equals(0L) ? null : searchRequest.getItemGroup());
            query.setParameter("itemCategory", searchRequest.getItemCategory().equals(0L) ? null : searchRequest.getItemCategory());
            query.setParameter("generic", searchRequest.getGeneric().equals(0L) ? null : searchRequest.getGeneric());
            query.setParameter("itemCode", searchRequest.getItemCode() != null ? searchRequest.getItemCode().toLowerCase() + '%' : "");
            query.setParameter("itemName", searchRequest.getItemName() != null ? searchRequest.getItemName().toLowerCase() + '%' : "");
            query.setMaxResults(pageRequest.getPageSize());
            query.setFirstResult((int) pageRequest.getOffset());
            itemMasterList = new PageImpl<>(query.getResultList());
        } catch (Exception e) {
            throw e;
        }
        return itemMasterList;
    }

}
