package com.bloomxpress.bloomxpress_core.repository;

import com.bloomxpress.bloomxpress_core.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    // Query to filter by category ID if provided, else return all
    @Query("SELECT DISTINCT s FROM Shop s JOIN s.categories c " +
            "WHERE (:categoryId IS NULL OR c.id = :categoryId)")
    Page<Shop> findAllByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    // Fetch shops created after a specific date
    Page<Shop> findByCreatedDateAfter(LocalDateTime date, Pageable pageable);
}
