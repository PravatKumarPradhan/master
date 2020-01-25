package com.sdgt.medcare.master.model;

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
public class MarkupDetailSaveRequest {

    private Long id;

    private Double minimumRate;

    private Double maximumRate;

    private Boolean anyRate;

    private Double markupPercentage;

    private Double markupRate;

    private Double fixedSellingPrice;

}
