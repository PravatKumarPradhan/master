package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.GroupMaster;
import com.sdgt.medcare.master.entity.org.ServiceMaster;
import com.sdgt.medcare.master.entity.org.SubGroupMaster;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface ServiceMasterRepository extends MastersBaseRepository<ServiceMaster, Long> , JpaSpecificationExecutor<ServiceMaster> {
    /**
     * Get {@link ServiceMaster}
     * @param code a valid code.
     * @return {@link ServiceMaster} if found. Else null.
     */
    ServiceMaster findByCodeIgnoreCase(final String code);

    /**
     * Get {@link ServiceMaster} list
     * @param codes valid codes.
     * @return {@link ServiceMaster} if found. Else null.
     */
    Collection<ServiceMaster> findAllByCode(final Collection<String> codes);


    /**
     * Get list {@link ServiceMaster}
     * @param groupMaster a valid {@link GroupMaster}.
     * @return list of {@link ServiceMaster} if found. Else null.
     */
    List<ServiceMaster> findByGroupMaster(final GroupMaster groupMaster);

    /**
     * Get list {@link ServiceMaster}
     * @param groupMaster a valid {@link GroupMaster}.
     * @param subGroupMaster a valid
     * @return list of {@link ServiceMaster} if found. Else null.
     */
    List<ServiceMaster> findByGroupMasterAndSubGroupMaster(final GroupMaster groupMaster, final SubGroupMaster subGroupMaster);

    @Query("select sm from ServiceMaster sm where code in :codeList")
    List<ServiceMaster> findList(@Param("codeList") List<String> codeList);

    @Query("select t.groupMaster.id from ServiceMaster t  where " +
            " t.code =:servicecode ")
    Long  findGroupByServiceCode(@Param("servicecode") String servicecode);

    @Query("select t.subGroupMaster.id from ServiceMaster t  where " +
            " t.code =:servicecode ")
    Long  findSubGroupByServiceCode(@Param("servicecode") String servicecode);

    @Query("select t.isDoctorShare from ServiceMaster t  where " +
            " t.code =:servicecode ")
    boolean  findDoctorShareServiceByServiceCode(@Param("servicecode") String servicecode);


}
