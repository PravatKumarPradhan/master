package com.sdgt.medcare.master.dto.otims;

import com.sdgt.medcare.master.dto.BaseDTO;
import com.sdgt.medcare.master.entity.global.PreferenceCardDetailsMaster;
import com.sdgt.medcare.master.entity.global.PreferenceCardOtherDetailsMaster;
import lombok.Data;

import java.util.List;

@Data
public class PreferenceCardDTO extends BaseDTO {

    private String deptCode;
    private String procedureCode;
    private String cardAgainst;
    private String doctorId;
    private String code;
    private PreferenceCardOtherDetailsMaster preferenceCardOtherDetailsMaster;
    private List<PreferenceCardDetailsMaster> preferenceCardDetailsMaster;

}

