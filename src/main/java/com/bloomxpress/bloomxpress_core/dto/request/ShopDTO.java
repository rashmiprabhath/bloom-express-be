package com.bloomxpress.bloomxpress_core.dto.request;

import com.bloomxpress.bloomxpress_core.enums.ShopStatus;
import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String description;
    private String pageLink;
    private String address;
    private String country;
    private String imageUrl;
    private Integer clicks;
    private ShopStatus status;
    private List<Long> categoryIds; // We use IDs to link categories
}
