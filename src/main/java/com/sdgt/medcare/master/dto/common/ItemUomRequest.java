package com.sdgt.medcare.master.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemUomRequest {

    private String itemCode;

    private String uomTypeId;

    public String getItemCode() {
        return itemCode;
    }

    public String getUomTypeId() {
        return uomTypeId;
    }
}
