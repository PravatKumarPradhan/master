package com.sdgt.medcare.master.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemsParameterRequest {

//    private List<String> itemCodes;

    private List<String> storeCodes;

    private List<String> itemGroupCodes;

    private List<String> itemCategoryCodes;

    private String itemCodes;

    private String itemNames;

}
