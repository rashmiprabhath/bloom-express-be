package com.bloomxpress.bloomxpress_core.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
}