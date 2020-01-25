package com.sdgt.medcare.master.dto.otims;

import com.sdgt.medcare.master.dto.BaseDTO;
import lombok.Data;

@Data
public class CheckListDTO extends BaseDTO {

    private String checkListStatus;
    private String checkListType;
    private String checkListHeader;
    private String contentDetails;
    private String code;
    private String module;
    private boolean manditory;

}
