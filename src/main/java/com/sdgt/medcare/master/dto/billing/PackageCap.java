package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
public class PackageCap extends BaseDTO {
    private List<PackageExclusion> packageExclusionList;
    private String groupConsumable;
    private String groupCode;
    private String groupDesc;
    private String subGroupCode;
    private String subGroupDesc;
    private String itemGroupCode;
    private String itemGroupDesc;
    private String itemCategoryCode;
    private String itemCategoryDesc;
    private Double capRate;
}
