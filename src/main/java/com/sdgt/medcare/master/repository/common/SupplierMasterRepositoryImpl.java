package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.dto.common.SupplierSearchRequest;
import com.sdgt.medcare.master.entity.global.SupplierMaster;
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
public class SupplierMasterRepositoryImpl implements SupplierMasterRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Object getAllSupplierList(SupplierSearchRequest searchRequest, PageRequest pageRequest) {
        Page<SupplierMaster> supplierMasterList;
        try {
            Query query = entityManager.createQuery("SELECT sm From SupplierMaster As sm " +
                    " Where sm.active = true" +
                    " AND (:supplierCode = '' OR lower(sm.code) LIKE :supplierCode) " +
                    " AND (:supplierName = '' OR lower(sm.desc) LIKE :supplierName) " +
                    " ORDER BY sm.desc", SupplierMaster.class);
            query.setParameter("supplierCode", searchRequest.getSupplierCode() != null ? searchRequest.getSupplierCode().toLowerCase() + '%' : "");
            query.setParameter("supplierName", searchRequest.getSupplierName()!= null ? searchRequest.getSupplierName().toLowerCase() + '%' : "");
            query.setMaxResults(pageRequest.getPageSize());
            query.setFirstResult((int)pageRequest.getOffset());
            supplierMasterList = new PageImpl<>(query.getResultList());
        } catch (Exception e) {
            throw e;
        }
        return supplierMasterList;
    }

}

