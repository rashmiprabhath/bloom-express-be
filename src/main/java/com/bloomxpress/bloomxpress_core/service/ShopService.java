package com.bloomxpress.bloomxpress_core.service;

import com.bloomxpress.bloomxpress_core.dto.request.ShopDTO;
import com.bloomxpress.bloomxpress_core.entity.Shop;
import com.bloomxpress.bloomxpress_core.enums.ShopStatus;
import com.bloomxpress.bloomxpress_core.mapper.ShopMapper;
import com.bloomxpress.bloomxpress_core.repository.CategoryRepository;
import com.bloomxpress.bloomxpress_core.repository.ShopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;
    private final ShopMapper shopMapper;

    @Transactional
    public ShopDTO createShop(ShopDTO dto) {
        Shop shop = shopMapper.toEntity(dto);
        shop.setClicks(0); // Initialize clicks
        return shopMapper.toDTO(shopRepository.save(shop));
    }

    @Transactional
    public Optional<ShopDTO> updateShop(Long id, ShopDTO dto) {
        return shopRepository.findById(id).map(shop -> {
            shop.setName(dto.getName());
            shop.setEmail(dto.getEmail());
            shop.setPhoneNumber(dto.getPhoneNumber());
            shop.setDescription(dto.getDescription());
            shop.setAddress(dto.getAddress());
            shop.setCountry(dto.getCountry());

            if (dto.getCategoryIds() != null) {
                shop.setCategories(new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds())));
            }

            return shopMapper.toDTO(shopRepository.save(shop));
        });
    }

    @Transactional
    public Optional<ShopDTO> updateStatus(Long id, ShopStatus status) {
        return shopRepository.findById(id).map(shop -> {
            shop.setStatus(status);
            return shopMapper.toDTO(shopRepository.save(shop));
        });
    }

    @Transactional
    public Page<ShopDTO> getShops(Long categoryId, int page) {
        Pageable pageable = PageRequest.of(page, 12, Sort.by("name").ascending());
        return shopRepository.findAllByCategoryId(categoryId, pageable)
                .map(shopMapper::toDTO);
    }

    public Page<ShopDTO> getLatestShops(int page) {
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        Pageable pageable = PageRequest.of(page, 12, Sort.by("createdDate").descending());
        return shopRepository.findByCreatedDateAfter(threeMonthsAgo, pageable)
                .map(shopMapper::toDTO);
    }

    public Optional<ShopDTO> getShopById(Long id) {
        return shopRepository.findById(id).map(shopMapper::toDTO);
    }
}
