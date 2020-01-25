package com.sdgt.medcare.master.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KitResponse {

    private Long id;
    private String kitCode;
    private String kitName;
    private List<KitDetailResponse> items;

    public KitResponse(Long id, String kitCode, String kitName) {
        this.id = id;
        this.kitCode = kitCode;
        this.kitName = kitName;
    }
}
