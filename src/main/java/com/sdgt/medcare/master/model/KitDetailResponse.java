package com.sdgt.medcare.master.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KitDetailResponse {

    private Long id;
    private Long itemId;
    private String itemCode;
    private String itemName;
    private Long uomUnitId;
    private String uomUnitCode;
    private String uomUnit;
    private Long uomTypeId;
    private String uomTypeCode;
    private String uomType;
    private Integer quantity;

}
