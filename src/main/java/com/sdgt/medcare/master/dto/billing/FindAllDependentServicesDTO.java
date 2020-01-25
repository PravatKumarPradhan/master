package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

import java.util.Collection;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
public class FindAllDependentServicesDTO extends BaseDTO {
    private Collection<String> serviceCodeCollection;
    private Collection<Long> unitServiceMapperIdCollection;
}
