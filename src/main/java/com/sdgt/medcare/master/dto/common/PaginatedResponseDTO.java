package com.sdgt.medcare.master.dto.common;

import java.util.List;

public class PaginatedResponseDTO {

    private Long totalCount;

    private Long offset;

    private Long limit;

    private List<Object> response;

    public PaginatedResponseDTO(Long totalCount, Long offset, Long limit, List<Object> response) {
        this.totalCount = totalCount;
        this.offset = offset;
        this.limit = limit;
        this.response = response;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public List<Object> getResponse() {
        return response;
    }

    public void setResponse(List<Object> response) {
        this.response = response;
    }


}
