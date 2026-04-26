package com.bloomxpress.bloomxpress_core.repository;

import com.bloomxpress.bloomxpress_core.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
