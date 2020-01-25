package com.sdgt.medcare.master.repository.billing;

import com.sdgt.medcare.master.entity.billing.contract.ContractMaster;
import com.sdgt.medcare.master.entity.org.CompanyMaster;
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
public interface ContractMasterRepository extends JpaRepository<ContractMaster, Long> {
    /**
     * Find {@link ContractMaster} by code.
     *
     * @param code a valid code.
     * @return {@link ContractMaster} if found. Else null.
     */
    ContractMaster findByCode(final String code);

    /**
     * Find {@link ContractMaster} by CompanyMaster.
     *
     * @param companyMaster a valid {@link CompanyMaster}.
     * @return list of {@link ContractMaster} if found. Else null.
     */
    Collection<ContractMaster> findByCompany(CompanyMaster companyMaster);
}
