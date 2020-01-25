package com.sdgt.medcare.master.dto.common;

import lombok.Getter;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Getter
public enum StatusEnum {
    ACTIVE("ACTIVE"),
    IN_ACTIVE("IN_ACTIVE");

    private String status;

    StatusEnum(final String status) {
        this.status = status;
    }
}
