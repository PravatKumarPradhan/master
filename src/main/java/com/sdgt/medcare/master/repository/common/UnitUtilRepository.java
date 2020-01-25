package com.sdgt.medcare.master.repository.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface UnitUtilRepository<T,ID extends Serializable > extends JpaRepository<T, ID>{

    // public Page<T> findByUnitMasterId(Long unitMaster);

    Page<T> findByUnitMasterId(Long unitMaster, Pageable pageable);

    //  Page<T> findByUnitMasterId(Class clazz, Long unitMaster, Pageable pageable);

    Page<T> findByUnitMasterCode(String unitMaster, Pageable pageable);

}
