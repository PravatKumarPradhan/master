package com.sdgt.medcare.master.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Uom {

    private String id;
    private Long uomTypeId;

    private Long uomUnitId;

    private Integer conversion;

    private Boolean ipUom;

    private Boolean opUom;

    private Boolean storeUom;
}
