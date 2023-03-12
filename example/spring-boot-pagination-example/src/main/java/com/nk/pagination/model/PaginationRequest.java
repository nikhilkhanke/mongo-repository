package com.nk.pagination.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationRequest {
    private Integer page;
    private Integer limit;
    private String filterOr;
    private String filterAnd;
    private String order;
}
