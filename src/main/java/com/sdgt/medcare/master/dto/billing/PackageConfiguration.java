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
public class PackageConfiguration extends BaseDTO {
    private String tariffCode;
    private String visitTypeCode;
    private String financialClassCode;
    private Double packageRate;
    private Double packageDiscountRate;
    private Double packageRoundOffRate;
    private Double packageRevisedRate;
    private List<PackageCapDetailDTO> packageCapDetailList;

    private List<PackageItemServiceDetailDTO> packageItemServiceDetailList;
}
