package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.inventory.KitDetailMaster;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = false)
public class KitMasterRepositoryImpl implements KitMasterRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<KitDetailMaster> getDetailsByKitId(Long kitId) {
        List<KitDetailMaster> kitDetailList = new ArrayList<>();
        try {
            Query query = entityManager.createQuery(" Select kitD From KitDetailMaster AS kitD " +
                    "INNER JOIN kitD.kitMaster AS kit " +
                    "INNER JOIN kitD.itemMaster AS item " +
                    "INNER JOIN kitD.uomTypeMaster AS uomType " +
                    "INNER JOIN kitD.itemUnitMaster AS uomunit " +
                    "WHERE kitD.active=true " +
                    "AND kit.id = :kitId", KitDetailMaster.class);
            query.setParameter("kitId", kitId);
            kitDetailList = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return kitDetailList;
    }
}
