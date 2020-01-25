package com.sdgt.medcare.master.repository.common;

import com.sdgt.medcare.master.entity.global.TheatreRoomMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Repository
public interface TheatreRoomMasterRepository extends JpaRepository<TheatreRoomMaster, Long> {
    /**
     * Get {@link TheatreRoomMaster} by given code.
     *
     * @param code a valid from {@link TheatreRoomMaster}
     * @return the {@link TheatreRoomMaster} object if found. Else null.
     */
    TheatreRoomMaster findByCode(final String code);
}
