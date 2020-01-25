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
public class ItemsRequest {

    private Long itemTypeId;

    private Long requestingStoreId;

    private Long issuingStoreId;

    private Long organizationMasterId;

    private Long unitMasterId;
}
