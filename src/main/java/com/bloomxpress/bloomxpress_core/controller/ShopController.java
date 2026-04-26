package com.bloomxpress.bloomxpress_core.controller;

import com.bloomxpress.bloomxpress_core.dto.request.ShopDTO;
import com.bloomxpress.bloomxpress_core.enums.ShopStatus;
import com.bloomxpress.bloomxpress_core.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<ShopDTO> createShop(@RequestBody ShopDTO dto) {
        return new ResponseEntity<>(shopService.createShop(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopDTO> updateShop(@PathVariable Long id, @RequestBody ShopDTO dto) {
        return shopService.updateShop(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ShopDTO> updateStatus(@PathVariable Long id, @RequestParam ShopStatus status) {
        return shopService.updateStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 1. Get Shops List (with optional category filter and pagination)
    @GetMapping
    public ResponseEntity<Page<ShopDTO>> getShops(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(shopService.getShops(categoryId, page));
    }

    // 2. Get Latest Shops (last 3 months)
    @GetMapping("/latest")
    public ResponseEntity<Page<ShopDTO>> getLatestShops(
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(shopService.getLatestShops(page));
    }

    // 3. Get Shop by ID
    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> getShopById(@PathVariable Long id) {
        return shopService.getShopById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
