package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface DepartmentMasterRepository extends JpaRepository<DepartmentMaster, Long> {
    /**
     * Finds the {@link DepartmentMaster} by given dept code.
     *
     * @param code a valid code defined in {@link DepartmentMaster}.
     * @return the {@link DepartmentMaster} object.
     */
    DepartmentMaster findByCode(String code);
}
