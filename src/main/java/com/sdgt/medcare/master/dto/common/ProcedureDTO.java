package com.sdgt.medcare.master.dto.common;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * SD Global Technologies.
 * <p>Copyright &copy; 2019. All rights reserved.
 * </p>
 *
 * @author Karthik Chandra
 * @author Pravat Kumar Pradhan
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProcedureDTO extends BaseDTO {
    private String procedureId;
    private String procedureDesc;
    private String procedureCode;
    private String cptName;
    private String cptCode;
  //  private String icdCode;
    private String departmentId;
    private String departmentCode;
    private String anaesthesiaTypeId;
    private String anaesthesiaTypeCode;
    private String operationTypeId;
    private String operationTypeCode;
    private String operationTheatreRoomId;
    private String operationTheatreRoomCode;
    private String duration;
    private String serviceId;
    private String serviceCode;
    private String unitServiceMapperId;
    private String procedureTypeId;
    private String procedureTypeCode;
    private Set<String> resourceDetailIds;
    private Set<String> resourceDetailCodes;
    private Set<ResourceDetailDTO> resourceDetails;
    private String theaterTypeId;
    private String theaterTypeCode;

    private boolean isTransactionSuccess = false;
    private String transactionPerformed; // create, update
    private String errorMessage;

    @Override
    public void verify() {
        super.verify();

        //TODO add verification
        if (StringUtils.isBlank(departmentId) && StringUtils.isBlank(departmentCode)) {
            errorMessage = "Either of department Id or code is required.";
        }
    }
}
