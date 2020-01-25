package com.sdgt.medcare.master.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearchRequest {

    @NotNull(message = "Limit cannot be null")
    private Integer limit;

    @NotNull(message = "offset cannot be null")
    private Integer offset;

    private String itemName;

    private String itemCode;

    private Long generic;

    private Long itemType;

    private Long itemGroup;

    private Long itemCategory;

}
