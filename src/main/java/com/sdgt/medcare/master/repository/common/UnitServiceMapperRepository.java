package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.UnitServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 *
 * @author Pravat Kumar Pradhan
 */
@Repository
public interface UnitServiceMapperRepository extends UnitUtilRepository<UnitServiceMapper, Long> , JpaSpecificationExecutor<UnitServiceMapper> {

    /**
     * Find {@link UnitServiceMapper} by given id or code.
     *
     * @param id a valid id. Nullable.
     * @param code a valid code. Nullable
     * @return {@link UnitServiceMapper} if found. Else null.
     */
    UnitServiceMapper findByIdOrCode(final Long id, final String code);

    /**
     * Find {@link UnitServiceMapper} by given service master and unit master
     *
     * @param serviceMaster given {@link ServiceMaster} against service code.
     * @return {@link UnitServiceMapper} list.
     */
    List<UnitServiceMapper> findAllByServiceMaster(final ServiceMaster serviceMaster);

    /**
     * Find {@link UnitServiceMapper} by given service master and unit master
     *
     * @param serviceMaster given {@link ServiceMaster} against service code.
     * @param unitMaster given {@link UnitMaster} against unit code.
     * @return {@link UnitServiceMapper}
     */
    UnitServiceMapper findByServiceMasterAndUnitMaster(final ServiceMaster serviceMaster, final UnitMaster unitMaster);

    /**
     * Finds the {@link UnitServiceMapper} list against given {@link UnitMaster}
     * and also filters by current date if it is in the valid date range of the service called.
     *
     * @param unitMaster a valid {@link UnitMaster} object.
     * @return a list of {@link UnitServiceMapper}
     */
    @Query("select usm from UnitServiceMapper usm where usm.unitMaster = :unitMaster and usm.serviceMaster IN (select s from ServiceMaster s where lower(s.desc) like lower(:subString)order by s.desc)" )
    List<UnitServiceMapper> findAllByUnitMaster(final @Param("unitMaster") UnitMaster unitMaster, final @Param("subString") String subString,
                                                final Pageable pageable);

    /**
     * Gets {@link UnitServiceMapper} by given code.
     *
     * @param unitServiceMapperCode a valid code from {@link UnitServiceMapper}
     * @return the {@link UnitServiceMapper} object.
     */
    UnitServiceMapper findByCode(final String unitServiceMapperCode);

    /**
     * Gets {@link UnitServiceMapper} by unit and service.
     *
     * @param unitMaster a valid {@link UnitMaster} object.
     * @param serviceMaster a valid {@link ServiceMaster} object.
     * @return the {@link UnitServiceMapper} object if found. Null if not found.
     */
    UnitServiceMapper findByUnitMasterAndServiceMaster(UnitMaster unitMaster, ServiceMaster serviceMaster);

    /**
     * Gets list of procedure type {@link UnitServiceMapper} for given unit code.
     * @param unitMaster a valid code of type {@link UnitMaster}.
     * @return list of {@link UnitServiceMapper} of type procedure.
     */
    @Query("select usm from UnitServiceMapper usm where usm.unitMaster = :unitMaster and usm.isProcedure = true")
    List<UnitServiceMapper> findAllProcedureService(final @Param("unitMaster") UnitMaster unitMaster);

    List<UnitServiceMapper> findByServiceMaster(ServiceMaster serviceMaster);
    List<UnitServiceMapper> findByServiceMasterAndActive(ServiceMaster serviceMaster, Boolean aBoolean);

   @Query("SELECT  DISTINCT usm.serviceMaster,usm  from UnitServiceMapper usm ")
    Page<UnitServiceMapper> findAllUnitServiceMapper(Pageable pageable);

    /*@Query("SELECT  DISTINCT usm.serviceMaster,sm.,sm.validityTo from UnitServiceMapper usm JOIN FETCH ServiceMaster sm ")
    Page<UnitServiceMapper> findAllUnitServiceMapper(Pageable pageable);*/






    Page<UnitServiceMapper> findAllByUnitMaster(UnitMaster unitMaster,Pageable pageable);


    List<UnitServiceMapper> findByUnitMaster(UnitMaster unitMaster);

    @Transactional
    @Modifying
    @Query ("update UnitServiceMapper usm set usm.active = false WHERE usm.id = :id ")
    void updateStatusForUnit(@Param("id") Long id);

}
