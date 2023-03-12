package com.nk.pagination.model;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private int totalPages;
    private long totalItems;
    private int currentPage;
    private boolean first;
    private boolean last;
    private int itemsPerPage;
    private int pageSize;

    private List<T> items = new ArrayList<>();

    public void setPage(Page pg, List<T> list) {
        first = pg.isFirst();
        last = pg.isLast();
        currentPage = pg.getNumber() + 1;
        pageSize = pg.getSize();
        totalPages = pg.getTotalPages();
        totalItems = pg.getTotalElements();
        itemsPerPage = pg.getNumberOfElements();
        items = list;
    }
}
