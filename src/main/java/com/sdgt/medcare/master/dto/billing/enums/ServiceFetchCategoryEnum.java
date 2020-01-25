package com.sdgt.medcare.master.dto.billing.enums;

import lombok.Getter;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Getter
public enum ServiceFetchCategoryEnum {
    CODE("code"),
    NAME("name");

    private String category;

    ServiceFetchCategoryEnum(final String category) {
        this.category = category;
    }
}
