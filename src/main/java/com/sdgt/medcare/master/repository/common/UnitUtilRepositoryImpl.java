package com.sdgt.medcare.master.repository.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author Pravat Kumar Pradhan
 *
 * @param <T>
 * @param <ID>
 */
public class UnitUtilRepositoryImpl <T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements UnitUtilRepository<T, ID> {

    private EntityManager entityManager;

    public UnitUtilRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
	super(entityInformation, entityManager);
	this.entityManager = entityManager;
    }

    @Transactional
    public List<T> findByAttributeContainsText(String unitCode, String attributeName, String text) {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<T> query = builder.createQuery(getDomainClass());
	Root<T> root = query.from(getDomainClass());
		query.select(root)
				.where(builder.like(root.get(attributeName), "%" + text + "%"))
				.where(builder.equal(root.get("unitMaster"), unitCode));
	TypedQuery<T> q = entityManager.createQuery(query);
	return q.getResultList();
    }


    /**
     * @param unitMaster
     * @return unitMaster Based on UnitId
     */
    @Override
    @Transactional
    public Page<T> findByUnitMasterId(Long unitMaster,Pageable pageable) {
	// TODO Auto-generated method stub

	CriteriaBuilder builder=entityManager.getCriteriaBuilder();
	CriteriaQuery<T> query=builder.createQuery(getDomainClass());
	Root<T> root=query.from(getDomainClass());
	query.select(root).where(builder.equal(root.get("unitMaster"),unitMaster));
	TypedQuery<T> q = entityManager.createQuery(query);
	return (Page<T>) q.getResultList();


    }

    /**
     * @param unitMaster
     * @return unitMaster Based on UnitCode
     */

    @Override
    @Transactional
    public Page<T> findByUnitMasterCode(String unitMaster, Pageable pageable) {
	CriteriaBuilder builder=entityManager.getCriteriaBuilder();
	CriteriaQuery<T> query=builder.createQuery(getDomainClass());
	Root<T> root=query.from(getDomainClass());
	query.select(root).where(builder.equal(root.get("unitMaster"),unitMaster));
	TypedQuery<T> q = entityManager.createQuery(query);
	return (Page<T>) q.getResultList();

    }



}


