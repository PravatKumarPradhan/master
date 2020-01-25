package com.sdgt.medcare.master.dto;

import com.core.base.rest.util.HttpUtils;
import com.core.constants.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
@ToString(exclude = "logger")
public class BaseDTO {
    @JsonIgnore
    private Logger logger = LoggerFactory.getLogger(BaseDTO.class);

    protected static final String INVALID_MESSAGE = "invalid request/response";

    private String unitCode;
    private String orgCode;
    private String createdBy;
    private boolean isFreeze;

    /**
     * Default Controller. Set required headers to DTO.
     */
    public BaseDTO() {
        unitCode = HttpUtils.getHeader(HttpHeaders.UNIT_CODE);
        orgCode = HttpUtils.getHeader(HttpHeaders.ORG_CODE);
        createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
    }

    /**
     * Make the DTO immutable.
     */
    public void freeze() {
        verify();
        isFreeze = true;
    }

    /**
     * Verify the dto.
     */
    public void verify() {
        if (StringUtils.isBlank(this.getUnitCode())) {
            logger.error("invalid unitCode");
            throw new IllegalArgumentException(INVALID_MESSAGE);
        }

        if (StringUtils.isBlank(this.getOrgCode())) {
            logger.error("invalid orgCode");
            throw new IllegalArgumentException(INVALID_MESSAGE);
        }

        if (StringUtils.isBlank(this.getCreatedBy())) {
            logger.error("invalid createdBy");
            throw new IllegalArgumentException(INVALID_MESSAGE);
        }
    }
}
