package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * SD Global Technologies. Copyright &copy; 2019. All rights reserved.
 *
 * @author Karthik Chandra
 */
@Data
public class BatchTariffRate extends BaseDTO {
    private String financialClassCode;
    private String tariffCode;
    private String visitTypeCode;
    private List<String> serviceCodeList;
}
