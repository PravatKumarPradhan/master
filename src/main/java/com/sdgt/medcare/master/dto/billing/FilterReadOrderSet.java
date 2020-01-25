package com.sdgt.medcare.master.dto.billing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgt.medcare.master.dto.BaseDTO;
import com.sdgt.medcare.master.entity.billing.OrderSetMaster;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * SD Global Technologies.
 * Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Setter
@Getter
@ToString(exclude = {"orderSetMasterList", "orderSetMenuItemList"})
public class FilterReadOrderSet extends BaseDTO {
    @JsonIgnore
    private Logger logger = LoggerFactory.getLogger(FilterReadOrderSet.class);

    private String code;
    private List<OrderSetMaster> orderSetMasterList;
    private List<OrderSetMenuItem> orderSetMenuItemList;

    /**
     * {@inheritDoc}
     */
    @Override
    public void verify() {
        super.verify();

        if (StringUtils.isBlank(this.getCode())) {
            logger.error("invalid code.");
            throw new IllegalArgumentException(INVALID_MESSAGE);
        }
    }
}
