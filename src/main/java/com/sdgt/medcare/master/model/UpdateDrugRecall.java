package com.sdgt.medcare.master.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDrugRecall {
    private String itemCode;
    private Boolean drugRecall;
}
