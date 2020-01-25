package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.TariffMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface TariffMasterRepository extends JpaRepository<TariffMaster, Long> {
    /**
     * Gets {@link TariffMaster} by given code.
     *
     * @param tariffCode a valid code from {@link TariffMaster}
     * @return the {@link TariffMaster} object.
     */
    TariffMaster findByCode(final String tariffCode);
}
