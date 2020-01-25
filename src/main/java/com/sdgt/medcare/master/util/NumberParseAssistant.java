package com.sdgt.medcare.master.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Json parser Assistant.
 *
 * <p>SD Global Technologies.
 * Copyright (c) 2019. All rights reserved.</p>
 *
 * @author Karthik Chandra
 */
@Component
public class NumberParseAssistant {
    /**
     * This class should not be instantiated.
     */
    private NumberParseAssistant(){}

    /**
     * Parse long value.
     *
     * @param longValue a given string
     * @return {@link Long} value. Returns 0 if longValue is blank. Else parse the langValue. Throws Exception if invalid number.
     */
    public static Long parseLong(final String longValue) {
        if(StringUtils.isBlank(longValue)) {
            return 0L;
        }

        return Long.parseLong(longValue);
    }
}
