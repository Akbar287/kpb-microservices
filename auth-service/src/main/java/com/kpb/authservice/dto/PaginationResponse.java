package com.kpb.authservice.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
public class PaginationResponse {
    private List<?> content;
    private Pageable pageable;
    private boolean last;
    private Long totalElements;
    private int totalPages;
    private int size;
    private int number;
    private Sort sort;
    private boolean first;
    private int numberOfElements;
    private boolean empty;
}
