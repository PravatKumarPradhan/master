package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.unit.DependentUnitServiceMapper;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Repository
public interface DependentUnitServiceMapperRepository extends JpaRepository<DependentUnitServiceMapper, Long> {
    /**
     * Find all dependent service by given parent service.
     *
     * @param unitServiceMapperCollection is the given parent service.
     * @return mapper data with dependent information.
     */
    Collection<DependentUnitServiceMapper> findAllByParentUnitServiceMapperIn(Collection<UnitServiceMapper> unitServiceMapperCollection);
}
