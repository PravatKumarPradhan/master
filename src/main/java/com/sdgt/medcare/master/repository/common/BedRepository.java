/**
 *
 */
package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.org.UnitMaster;
import com.sdgt.medcare.master.entity.unit.BedMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pravat Kumar Pradhan
 *
 */

@Repository
public interface BedRepository extends UnitUtilRepository<BedMaster,Long>
{
    /**
     *
      @param wardMaster
      @param unitMaster
      @param bedCtegory
      @return the bed list based on the parameter
     */
    List <BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndBedStatusId(Long wardMaster, Long unitMaster, Long bedCtegory, Long bedStatus);

    /**
     *
      @param wardMaster
      @param unitMaste
      @return
     */
    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedStatusId(Long wardMaster, Long unitMaste, Long bedStatus);

    /**
     *
     @param wardMaster
     @param unitMaste
     @return
     */
    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedStatusIdAndIsVirtualFalse(Long wardMaster, Long unitMaste, Long bedStatus);


    /**
     *
     * @param wardMaster
     * @param unitMaster
     * @param pageble
     * @return
     */
    Page<BedMaster> findAllByWardMasterIdAndUnitMasterId(Long wardMaster, Long unitMaster, Pageable pageble);

    /**
     *
     * @param wardMaster
     * @param unitMaster
     * @return
     */
    List<BedMaster> findAllByWardMasterIdAndUnitMasterId(Long wardMaster, Long unitMaster);

    /**
     *
      @param code
      @return
     */
    @Query("select bm from BedMaster bm INNER JOIN WardMaster wm on bm.wardMaster=wm.id AND wm.isEr= true AND bm.bedStatus=1 WHERE bm.unitMasterCode= :code")
    List<BedMaster>findByWardMaster(@Param ("code") UnitMaster code);

    @Modifying
    @Query("update BedMaster bm set bm.bedStatus = 2 where bm.id= :id")
    BedMaster upadteBedMaster(@Param("id") BedMaster id);

    /**
     *
     * @param unitId
     * @return
     */
    List<BedMaster> findAllByUnitMasterId(Long unitId);

    /**
     *
     * @param unitId
     * @param blockId
     * @param pageble
     * @return
     */
    Page<BedMaster> findAllByUnitMasterIdAndBlockMasterId(Long unitId, Long blockId, Pageable pageble);

    /**
     *
     * @param unitId
     * @param blockId
     * @param floorId
     * @param pageble
     * @return
     */
    Page<BedMaster> findAllByUnitMasterIdAndBlockMasterIdAndFloorMasterId(Long unitId, Long blockId, Long floorId, Pageable pageble);

    /**
     *
     * @param unitId
     * @param blockId
     * @param floorId
     * @param wardId
     * @param pageble
     * @return
     */
    Page<BedMaster> findAllByUnitMasterIdAndBlockMasterIdAndFloorMasterIdAndWardMasterId(Long unitId, Long blockId, Long floorId, Long wardId, Pageable pageble);

    /**
     *
     * @param unitId
     * @param blockId
     * @param floorId
     * @param wardId
     * @param bedCategoryId
     * @param pageable
     * @return
     */
    Page<BedMaster> findAllByUnitMasterIdAndBlockMasterIdAndFloorMasterIdAndWardMasterIdAndBedCategoryId(Long unitId, Long blockId, Long floorId, Long wardId, Long bedCategoryId, Pageable pageable);

    /**
     *
     * @param unitId
     * @param statusId
     * @param pageable
     * @return
     */
    Page<BedMaster> findAllByUnitMasterIdAndBedStatusId(Long unitId, Long statusId, Pageable pageable);

    /**
     *
     * @param unitId
     * @param bedCategoryId
     * @param pageable
     * @return
     */
    Page<BedMaster> findAllByUnitMasterIdAndBedCategoryId(Long unitId, Long bedCategoryId, Pageable pageable);

    /**
     *
     * @param unitId
     * @param wardId
     * @param pageable
     * @return
     */
    Page<BedMaster> findAllByUnitMasterIdAndWardMasterId(Long unitId, Long wardId, Pageable pageable);

    /**
     *
     * @param unitId
     * @param floorId
     * @param pageable
     * @return
     */
    Page<BedMaster> findAllByUnitMasterIdAndFloorMasterId(Long unitId, Long floorId, Pageable pageable);

    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndIsVirtualFalse(Long wardMaster, Long unitId);

    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndIsVirtualFalse(Long wardMaster, Long unitId, Long bedCategory);

    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedStatusIdAndIsVirtualFalseAndIsBlockedFalse(Long wardMaster, Long unitId, long l);

    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndBedStatusIdAndIsVirtualFalseAndIsBlockedFalse(Long wardMaster, Long unitId, Long bedCategory, long l);

    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndBedStatusIdAndIsBlockedFalse(Long wardMaster, Long unitId, Long bedCategory, long l);

    List<BedMaster> findAllByUnitMasterIdAndIsVirtualFalse(Long unitId);

    List<BedMaster> findAllByUnitMasterIdAndBedStatusIdAndIsVirtualFalseAndIsBlockedFalse(Long unitId, long l);

    List<BedMaster> findAllByUnitMasterIdAndIsBlockedFalse(Long unitId);

    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndIsVirtualTrue(Long wardMaster, Long unitId);

    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedStatusIdAndIsVirtualTrueAndIsBlockedFalse(Long wardMaster, Long unitId, long l);

    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndIsVirtualTrue(Long wardMaster, Long unitId, Long bedCategory);

    List<BedMaster> findAllByWardMasterIdAndUnitMasterIdAndBedCategoryIdAndBedStatusIdAndIsVirtualTrueAndIsBlockedFalse(Long wardMaster, Long unitId, Long bedCategory, long l);
}


