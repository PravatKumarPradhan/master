package com.sdgt.medcare.master.dto.billing;

import com.sdgt.medcare.master.dto.billing.common.CommonDiscountContractDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 */
@Data
@NoArgsConstructor
public class ContractDTO extends CommonDiscountContractDTO {

    public ContractDTO(final String id) {
        setMasterId(id);
    }
}
