package com.sdgt.medcare.master.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdgt.medcare.master.dto.common.ItemUomRequest;
import com.sdgt.medcare.master.dto.common.ItemsParameterRequest;
import com.sdgt.medcare.master.dto.common.ItemsRequest;
import com.sdgt.medcare.master.dto.common.MultipleIdRequest;
import com.sdgt.medcare.master.dto.common.PaginatedResponseDTO;
import com.sdgt.medcare.master.dto.common.StockTakeItemRequest;
import com.sdgt.medcare.master.dto.common.StoresRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AbstractJpaDao {

   private Class clazz;

   @PersistenceContext
   EntityManager entityManager;

   JpaRepository jpaRepository;

   public void setClazz(Class clazzToSet) {
      this.clazz = clazzToSet;
   }

   public Object findOne(Class clazz, Serializable id) {
      return entityManager.find(clazz, id);
   }

	public Object findOneByCode(Class clazz, String code) {
		return entityManager.createQuery("from "+clazz.getName()+" c where c.active='true' and c.code='"+code+"'").getSingleResult();
	}


	public List<Object> findAll(Class clazz) {
		return entityManager.createQuery("from " + clazz.getName() + " c " + "where c." + " active= 'true' ")
				.getResultList();
	}

	public PaginatedResponseDTO findAllIncludingInactive(Class clazz, Long offset, Long limit) {
		List<Object> response;
		PaginatedResponseDTO paginatedResponseDTO;
		Long countResult;
		Query query = entityManager.createQuery("from " + clazz.getName() + " c ");
		Query queryTotal = entityManager.createQuery
				("Select count(c.id) from " + clazz.getName() + " c ");
		if(offset!=null && limit!=null) {
			response=query.setFirstResult(Math.toIntExact(offset)).setMaxResults(Math.toIntExact(limit)).getResultList();
			countResult = (long)queryTotal.getSingleResult();
			paginatedResponseDTO= new  PaginatedResponseDTO(countResult,offset,limit,response);
		}
		else
		{
			response=query.getResultList();
			paginatedResponseDTO= new  PaginatedResponseDTO(null,null,null,response);
		}
		return paginatedResponseDTO;

	}

	public List<Object> findByQuery(Class clazz, String field, String value) {
		//String qu = "SELECT c from " + clazz.getSimpleName() + " c " + " where c." + field + " = '" + value + "'";
		String qu = "SELECT c from " + clazz.getSimpleName() + " c " + " where c." + field + " = '" + value + "'"+"and"+" c." + "active= 'true'";
		return entityManager.createQuery(qu, clazz).getResultList();
	}

	public List<Object> findByAutoSearch(Class clazz, String field, String value) {
		String qu = "SELECT c from " + clazz.getSimpleName() + " c " + " where c." + field + " like '%" + value +"%' ";
		Query query=entityManager.createQuery(qu);
		query.setMaxResults(20);
		List em=  query.getResultList() ;
		return em ;
    }

	public List<Object> findByMultiQuery(Class clazz, String field, List<Long> value) {

		String hql = "SELECT c FROM " + clazz.getSimpleName() + " c WHERE c." + field + " IN :value";
		Query query=entityManager.createQuery(hql);
		query.setParameter("value",value);
		return query.getResultList();
	}

	public List<Object> findByMultiQueryByCode(Class clazz, String field, List<String> value) {

		String hql = "SELECT c FROM " + clazz.getSimpleName() + " c WHERE c." + field + " IN :value";
		Query query=entityManager.createQuery(hql);
		query.setParameter("value",value);
		return query.getResultList();
	}

	public <T> TypedQuery<T> findCodeAndDesc(Class clazz) {
		String qu = "Select code from " + clazz.getName();
		return (TypedQuery<T>) entityManager.createQuery(qu, clazz).getResultList();
	}

	public List<Object> findByOneQuery(Class clazz, String json, Boolean likeSearch) {

//		System.out.println("Likesearch : "+likeSearch);
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject obj = element.getAsJsonObject(); // since you know it's a JsonObject
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();// will return members of your object
		String whereCond = "";
		String qu= "";

		for (Map.Entry<String, JsonElement> entry : entries) {
			if (entry.getValue() != null && !entry.getValue().isJsonNull())
				if(!whereCond.isEmpty())
				{
					whereCond= whereCond.trim()+" and ";
				}

			if(entry.getValue().isJsonArray())
			{
				whereCond = whereCond + "c." + entry.getKey() + "IN :entry.getValue()";
			}
			else {
				if(likeSearch!=null && likeSearch) {
					StringBuilder val=new StringBuilder("%");
					val.append(entry.getValue().toString());
					val.append("%");
					String value= "lower('%"+entry.getValue().getAsString()+"%')";
					whereCond = whereCond + "Lower(c." + entry.getKey() + ") like " + value;
				}else{
					whereCond = whereCond + "c." + entry.getKey() + "=" + entry.getValue();
				}
			}
			qu = "Select c from " + clazz.getSimpleName() + " c " + " where " + whereCond; // and "+"";
		}
		if(whereCond.isEmpty()){
			qu = "Select c from " + clazz.getSimpleName() + " c ";

		}

		System.out.println("Query: "+qu);

		qu = qu.replace("\"", "'");
		return entityManager.createQuery(qu).getResultList();
	}

	public List<Object> findByLikeQuery(Class clazz, String json) {

		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject obj = element.getAsJsonObject(); // since you know it's a JsonObject
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();// will return members of your object
		String whereCond = "";
		for (Map.Entry<String, JsonElement> entry : entries) {
			if (entry.getValue() != null && !entry.getValue().isJsonNull()) {
				whereCond = whereCond + " AND lower(c." + entry.getKey() + ") like "
						+ entry.getValue().toString().toLowerCase();
			}
		}
		String qu = "Select c from " + clazz.getSimpleName() + " c " + " where " + whereCond; // and "+"";

		qu = qu.replace("\"", "'");
		return entityManager.createQuery(qu).getResultList();
	}


	/*Predikly Side */

	public List<Object> findByOneQuery(ItemsRequest itemsRequest) {
	try {
		String qu = "Select item from ItemMaster AS item "
				+ " INNER JOIN item.itemCategoryMaster AS itemCategory WITH itemCategory.active=true "
				+ " INNER JOIN itemCategory.itemTypeMaster AS itemType WITH itemType.active=true "
				+ " AND (:itemTypeId = 0l OR itemType.id = :itemTypeId) "
				+ " AND ((:requestingStoreId = 0l ) OR item.itemCategoryMaster.id IN (SELECT storeProduct.itemCategoryMaster.id FROM StoreProductMaster AS storeProduct "
				+ "	WHERE storeProduct.storeMaster.id = :requestingStoreId AND storeProduct.active=true )) "
				+ " AND ((:requestingStoreId = 0l ) OR item.id NOT IN (SELECT itemRestriction.itemMaster.id FROM ItemRestrictionMaster AS itemRestriction "
				+ "	WHERE itemRestriction.storeMaster.id = :requestingStoreId AND itemRestriction.active=true )) "

				+ " AND item.id IN (SELECT uom.itemMaster.id FROM UnitOfMeasurementMaster as uom "
				+ "	WHERE uom.active=true GROUP BY (uom.itemMaster.id))"
				+ " OR ((:issuingStoreId = 0l ) OR item.itemCategoryMaster.id IN (SELECT storeProduct.itemCategoryMaster.id FROM StoreProductMaster AS storeProduct "
				+ "	WHERE storeProduct.storeMaster.id = :issuingStoreId AND storeProduct.active=true )) "
				+ " AND ((:issuingStoreId = 0l ) OR item.id NOT IN (SELECT itemRestriction.itemMaster.id FROM ItemRestrictionMaster AS itemRestriction "
				+ "	WHERE itemRestriction.storeMaster.id = :issuingStoreId AND itemRestriction.active=true )) "
				+ " AND item.active=true "
				+ " AND item.pharmacy = false AND item.indent = false AND item.drugRecall= false "
				+ "ORDER BY item.desc ";
		; // and "+"";
		qu = qu.replace("\"", "'");
		Query query = entityManager.createQuery(qu);
		query.setParameter("itemTypeId", itemsRequest.getItemTypeId() != null ? itemsRequest.getItemTypeId() : 0L);
		query.setParameter("requestingStoreId", itemsRequest.getRequestingStoreId() != null ? itemsRequest.getRequestingStoreId() : 0L);
		query.setParameter("issuingStoreId", itemsRequest.getIssuingStoreId() != null ? itemsRequest.getIssuingStoreId() : 0L);
		return query.getResultList();
	}catch(Exception e){
		return null;
		}
	}

	public List<Object> findByInQuery(Class clazz, MultipleIdRequest multipleIdRequest) {

		String whereCond="";

		for(String id: multipleIdRequest.getIds()) {
			if(whereCond.length()==0) {
				whereCond += "'"+id+"'";
			}else{
				whereCond += ",'"+id+"'";
			}
		}
		String qu = "Select c from " + clazz.getSimpleName() + " as c " + " where active=true and id in ("+whereCond+")"; // and "+"";
		qu = qu.replace("\"", "'");
		return entityManager.createQuery(qu).getResultList();
	}

	public List<Object> findByInKeyQuery(Class clazz, String json) {

		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject obj = element.getAsJsonObject(); // since you know it's a JsonObject
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();// will return members of your object
		String whereCond = "";
		for (Map.Entry<String, JsonElement> entry : entries) {
			if (entry.getValue() != null && !entry.getValue().isJsonNull())
				whereCond = whereCond + " and " + entry.getKey() + " in (";
			for(int i=0 ; i<entry.getValue().getAsJsonArray().size(); i++) {
				JsonElement jsonElement = entry.getValue().getAsJsonArray().get(i);
				whereCond = whereCond +jsonElement.toString()+ (i!=entry.getValue().getAsJsonArray().size()-1 ? "," : ")");
			}

		}


		String qu = "Select c from " + clazz.getSimpleName() + " c " + " where active=true " + whereCond; // and "+"";
		qu = qu.replace("\"", "'");
		return entityManager.createQuery(qu).getResultList();
	}

	public List<Object> findByStoreQuery(StoresRequest storesRequest) {

		String qu = "Select new com.sdgt.medcare.master.model.ItemResponse(item.id, " +
				" item.code, item.desc, itemCategory.desc, itemGroup.desc, " +
				" sim.minQuantity, sim.maxQuantity,  sim.reorderQuantity, sim.reorderLevel, store.code, store.desc, " +
				" unit.desc, unit.code ) " +
				" From StoreItemMapperMaster AS sim " +
				" Inner Join sim.itemMaster AS item " +
				" INNER JOIN sim.storeMaster AS store " +
				" INNER JOIN store.unitMaster AS unit "+
				" INNER JOIN item.itemCategoryMaster AS itemCategory WITH itemCategory.active=true "
				+ " INNER JOIN itemCategory.itemGroupMaster AS itemGroup WITH itemGroup.active=true "
				+ " INNER JOIN itemCategory.itemTypeMaster AS itemType WITH itemType.active=true "
				+ " WHERE (:itemTypeId = 0l OR itemType.id = :itemTypeId) AND store.id IN (:storeIds) "
				+ " AND (item.itemCategoryMaster.id IN (SELECT storeProduct.itemCategoryMaster.id FROM StoreProductMaster AS storeProduct "
				+ "	WHERE storeProduct.storeMaster.id IN (:storeIds) AND storeProduct.active=true )) "
				+ " AND (item.id NOT IN (SELECT itemRestriction.itemMaster.id FROM ItemRestrictionMaster AS itemRestriction "
				+ "	WHERE itemRestriction.storeMaster.id IN (:storeIds) AND itemRestriction.active=true )) "
				+ " AND item.active=true AND item.replenishment = true  "
				+ "ORDER BY item.desc ";
		qu = qu.replace("\"", "'");
		Query query = entityManager.createQuery(qu);
		query.setParameter("itemTypeId", storesRequest.getItemTypeId()!= null ? storesRequest.getItemTypeId() : 0l);
		query.setParameter("storeIds", storesRequest.getStoreIds() != null ? storesRequest.getStoreIds() : 0l);
		return query.getResultList();
	}

	public List<Object> findByItemUomQuery(ItemUomRequest itemUomRequest) {

		String qu = " Select new com.sdgt.medcare.master.model.ItemUomResponse(item.id, item.code," +
				" item.desc, uomtype.id, uomtype.code, uomtype.desc, itemUnit.id, itemUnit.code, itemUnit.desc ) " +
				" From UnitOfMeasurementMaster as uom " +
				" INNER JOIN uom.itemMaster as item WITH item.active=true " +
				" INNER JOIN uom.uomTypeMaster as uomtype WITH uomtype.active=true " +
				" INNER JOIN uom.itemUnitMaster as itemUnit WITH itemUnit.active=true " +
				" WHERE uom.active=true AND item.code = :itemCode and uomtype.id = :uomTypeId ";
		qu = qu.replace("\"", "'");
		Query query = entityManager.createQuery(qu);
		query.setParameter("itemCode", itemUomRequest.getItemCode() != null ? itemUomRequest.getItemCode() : "");
		query.setParameter("uomTypeId", itemUomRequest.getUomTypeId() != null ? itemUomRequest.getUomTypeId() : 0l);
		return query.getResultList() ;
	}

	public List<Object> findByStockTakeItemListQuery(StockTakeItemRequest stockTakeItemRequest) {
		String qu = " SELECT new com.sdgt.medcare.master.model.ItemResponse(item.id, item.code, " +
				" item.desc, itemGroup.code, itemGroup.desc )" +
				" FROM ItemMaster as item " +
				" INNER JOIN item.itemCategoryMaster AS itemCategory WITH itemCategory.active=true " +
				" INNER JOIN itemCategory.itemGroupMaster AS itemGroup WITH itemGroup.active=true " +
				" INNER JOIN item.genericMaster AS generic WITH generic.active=true " +
				" WHERE (itemGroup.code IN (:itemGroupCode))" +
				" AND (itemCategory.id IN (SELECT storeProduct.itemCategoryMaster.id FROM StoreProductMaster AS storeProduct " +
				" LEFT JOIN storeProduct.storeMaster AS store WITH store.active=true " +
				" WHERE store.code IN (:storeCode) AND storeProduct.active=true )) " +
				" AND (item.id NOT IN (SELECT itemRestriction.itemMaster.id FROM ItemRestrictionMaster AS itemRestriction " +
				" LEFT JOIN itemRestriction.storeMaster AS store WITH store.active=true " +
				" AND store.code IN (:storeCode) AND itemRestriction.active=true )) " +
				" AND (0 = 0 OR (generic.id IN (SELECT storeGeneric.genericMaster.id FROM StoreGenericMapperMaster AS storeGeneric " +
				" LEFT JOIN storeGeneric.storeMaster as store WITH store.active=true " +
				" WHERE (storeGeneric.vital = :isVital) " +
				" AND (storeGeneric.essential = :isEssential) " +
				" AND (storeGeneric.desirable = :isDesirable) ))) " +
				" AND item.active=true " +
				" ORDER BY item.desc";

		qu = qu.replace("\"", "'");
		Query query = entityManager.createQuery(qu);
		query.setParameter("itemGroupCode", stockTakeItemRequest.getItemGroupCodes() != null ? stockTakeItemRequest.getItemGroupCodes() : "");
		query.setParameter("storeCode", stockTakeItemRequest.getStoreCode() != null ? stockTakeItemRequest.getStoreCode() : "");
		query.setParameter("isVital", stockTakeItemRequest.getIsVital());
		query.setParameter("isEssential", stockTakeItemRequest.getIsEssential());
		query.setParameter("isDesirable", stockTakeItemRequest.getIsDesirable());
		return query.getResultList() ;
	}


	private String checkFieldOperator(String value) {
		if (!value.contains("=") && !value.contains("like")) {
			return " = " + "'" + value + "'";
		}
		return value;
	}

	@Transactional
	public Object save(Object entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Transactional
	public void save(List entitys) {
		for (Object iterator : entitys) {
			// Object object = (Object) iterator.next();
			entityManager.persist(iterator);

		}
	}

	@Transactional
	public Object update(Object entity) {
		return entityManager.merge(entity);
	}

	@Transactional
	public void delete(Object entity) {
		entityManager.remove(entity);
	}

	@Transactional
	public void deleteById(Serializable entityId, Class clazz) {
		Object entity = findOne(clazz, entityId);
		delete(entity);
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
		return emf;
	}

    public List<Object> findByOneQueryForCssd(ItemsRequest itemsRequest) {
        try {
            String qu = "Select item from ItemMaster AS item "
                    + " INNER JOIN item.itemCategoryMaster AS itemCategory WITH itemCategory.active=true "
                    + " INNER JOIN itemCategory.itemTypeMaster AS itemType WITH itemType.active=true "
                    + " AND (:itemTypeId = 0l OR itemType.id = :itemTypeId) "
                    + " AND ((:requestingStoreId = 0l ) OR item.itemCategoryMaster.id IN (SELECT storeProduct.itemCategoryMaster.id FROM StoreProductMaster AS storeProduct "
                    + "	WHERE storeProduct.storeMaster.id = :requestingStoreId AND storeProduct.active=true )) "
                    + " AND ((:requestingStoreId = 0l ) OR item.id NOT IN (SELECT itemRestriction.itemMaster.id FROM ItemRestrictionMaster AS itemRestriction "
                    + "	WHERE itemRestriction.storeMaster.id = :requestingStoreId AND itemRestriction.active=true )) "

                    + " AND item.id IN (SELECT uom.itemMaster.id FROM UnitOfMeasurementMaster as uom "
                    + "	WHERE uom.active=true GROUP BY (uom.itemMaster.id))"
                    + " OR ((:issuingStoreId = 0l ) OR item.itemCategoryMaster.id IN (SELECT storeProduct.itemCategoryMaster.id FROM StoreProductMaster AS storeProduct "
                    + "	WHERE storeProduct.storeMaster.id = :issuingStoreId AND storeProduct.active=true )) "
                    + " AND ((:issuingStoreId = 0l ) OR item.id NOT IN (SELECT itemRestriction.itemMaster.id FROM ItemRestrictionMaster AS itemRestriction "
                    + "	WHERE itemRestriction.storeMaster.id = :issuingStoreId AND itemRestriction.active=true )) "
                    + " AND item.active=true "
                    + " AND item.cssd = true "
                    + "ORDER BY item.desc ";
            ; // and "+"";
            qu = qu.replace("\"", "'");
            Query query = entityManager.createQuery(qu);
            query.setParameter("itemTypeId", itemsRequest.getItemTypeId() != null ? itemsRequest.getItemTypeId() : 0L);
            query.setParameter("requestingStoreId", itemsRequest.getRequestingStoreId() != null ? itemsRequest.getRequestingStoreId() : 0L);
            query.setParameter("issuingStoreId", itemsRequest.getIssuingStoreId() != null ? itemsRequest.getIssuingStoreId() : 0L);
            return query.getResultList();
        }catch(Exception e){
            return null;
        }
    }

    public List<Object> findItemListByParameterQuery(ItemsParameterRequest itemsRequest) {
        String qu = " SELECT new com.sdgt.medcare.master.model.ItemResponse(item.id, " +
                " item.code, item.desc, itemGroup.desc, itemCategory.desc, " +
                " sim.minQuantity, sim.maxQuantity,  sim.reorderQuantity, sim.reorderLevel )" +
                " From StoreItemMapperMaster AS sim " +
                " Right Join sim.itemMaster AS item " +
                " INNER JOIN item.itemCategoryMaster AS itemCategory WITH itemCategory.active=true " +
                " INNER JOIN itemCategory.itemGroupMaster AS itemGroup WITH itemGroup.active=true " +
                " INNER JOIN item.genericMaster AS generic WITH generic.active=true " +
                " WHERE (:itemGroupCode = '' OR itemGroup.code IN (:itemGroupCode))" +
                " AND (:itemCategoryCodes = '' OR itemCategory.code IN (:itemCategoryCodes))" +
                " AND ('' = '' OR itemCategory.id IN (SELECT storeProduct.itemCategoryMaster.id FROM StoreProductMaster AS storeProduct " +
                " LEFT JOIN storeProduct.storeMaster AS store WITH store.active=true " +
                " WHERE store.code IN (:storeCode) AND storeProduct.active=true )) " +
                " AND ('' = '' OR item.id NOT IN (SELECT itemRestriction.itemMaster.id FROM ItemRestrictionMaster AS itemRestriction " +
                " LEFT JOIN itemRestriction.storeMaster AS store WITH store.active=true " +
                " AND store.code IN (:storeCode) AND itemRestriction.active=true )) " +
                " AND (:itemCodes = '' OR lower(item.code) LIKE (:itemCodes))" +
                " AND (:itemNames = '' OR lower(item.desc) LIKE (:itemNames))" +
                " AND item.active=true " +
                " ORDER BY item.desc";

        qu = qu.replace("\"", "'");
        Query query = entityManager.createQuery(qu);
        query.setParameter("itemGroupCode", (itemsRequest.getItemGroupCodes() != null && itemsRequest.getItemCategoryCodes().size() > 0) ? itemsRequest.getItemGroupCodes() : "");
        query.setParameter("itemCategoryCodes", (itemsRequest.getItemCategoryCodes() != null && itemsRequest.getItemCategoryCodes().size() > 0) ? itemsRequest.getItemCategoryCodes() : "");
        query.setParameter("storeCode", (itemsRequest.getStoreCodes() != null && itemsRequest.getStoreCodes().size() > 0) ?  itemsRequest.getStoreCodes() : "");
        query.setParameter("itemCodes", itemsRequest.getItemCodes() != null ? itemsRequest.getItemCodes().toLowerCase() + '%' : "");
        query.setParameter("itemNames", itemsRequest.getItemNames() != null ? itemsRequest.getItemNames().toLowerCase() + '%' : "");
        return query.getResultList() ;
    }

    /**
	 * Retrieves an entity by its id.
	 *
	 * @param o must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none
	 *         found7
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */

}
