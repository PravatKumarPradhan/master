package com.sdgt.medcare.master.repository.billing;

import com.sdgt.medcare.master.entity.billing.OrderSetMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface OrderMasterSetRepository extends JpaRepository<OrderSetMaster, Long> {
    /**
     * Find {@link OrderSetMaster} by code.
     *
     * @param code a valid code.
     * @return {@link OrderSetMaster} if found. Else null.
     */
    OrderSetMaster findByCode(final String code);

    /**
     * Find list of {@link OrderSetMaster} against given unit.
     *
     * @param unitMaster a valid {@link UnitMaster}
     * @return  list of {@link OrderSetMaster}.
     */
    List<OrderSetMaster> findAllByUnitMaster(final UnitMaster unitMaster);

    /**
     * Find list of {@link OrderSetMaster} against given organization.
     *
     * @param organizationMaster a valid {@link OrganizationMaster}
     * @return  list of {@link OrderSetMaster}.
     */
    List<OrderSetMaster> findAllByOrganizationMaster(final OrganizationMaster organizationMaster);

    /**
     * Find {@link OrderSetMaster} against given code, unit and organization.
     *
     * @param code a valid code.
     * @param unitMaster a valid {@link UnitMaster}
     * @param organizationMaster a valid {@link OrganizationMaster}
     * @return  list of {@link OrderSetMaster}.
     */
    @Query("select osm from OrderSetMaster osm where osm.code=:code and osm.unitMaster=:unitMaster and osm.organizationMaster=:organizationMaster")
    OrderSetMaster findByCodeByUnitMasterByOrganizationMaster(final @Param("code") String code, final @Param("unitMaster") UnitMaster unitMaster,
                                                              final @Param("organizationMaster") OrganizationMaster organizationMaster);
}
