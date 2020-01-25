package com.sdgt.medcare.master.dto.enums;

import lombok.Getter;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Getter
public enum PersistStatus {
    PARTIAL("PARTIAL"),
    COMPLETE("COMPLETE");

    private String status;

    PersistStatus(final String persistStatus) {
        status = persistStatus;
    }
}
