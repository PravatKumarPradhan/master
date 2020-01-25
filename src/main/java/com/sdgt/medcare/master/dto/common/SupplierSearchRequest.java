package com.sdgt.medcare.master.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierSearchRequest {

    @NotNull(message = "Limit cannot be null")
    private Integer limit;

    @NotNull(message = "offset cannot be null")
    private Integer offset;

    private String supplierCode;

    private String supplierName;

}
