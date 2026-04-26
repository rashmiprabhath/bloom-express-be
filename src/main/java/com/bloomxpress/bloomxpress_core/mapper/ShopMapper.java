package com.bloomxpress.bloomxpress_core.mapper;

import com.bloomxpress.bloomxpress_core.dto.request.ShopDTO;
import com.bloomxpress.bloomxpress_core.entity.Shop;
import com.bloomxpress.bloomxpress_core.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShopMapper {

    private final CategoryRepository categoryRepository;

    public ShopDTO toDTO(Shop shop) {
        if (shop == null) return null;
        return ShopDTO.builder()
                .id(shop.getId())
                .name(shop.getName())
                .email(shop.getEmail())
                .phoneNumber(shop.getPhoneNumber())
                .description(shop.getDescription())
                .pageLink(shop.getPageLink())
                .address(shop.getAddress())
                .country(shop.getCountry())
                .imageUrl(shop.getImageUrl())
                .clicks(shop.getClicks())
                .status(shop.getStatus())
                .categoryIds(shop.getCategories().stream()
                        .map(c -> c.getId()).collect(Collectors.toList()))
                .build();
    }

    public Shop toEntity(ShopDTO dto) {
        if (dto == null) return null;
        Shop shop = Shop.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .description(dto.getDescription())
                .pageLink(dto.getPageLink())
                .address(dto.getAddress())
                .country(dto.getCountry())
                .imageUrl(dto.getImageUrl())
                .status(dto.getStatus())
                .build();

        if (dto.getCategoryIds() != null) {
            shop.setCategories(new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds())));
        }
        return shop;
    }
}
