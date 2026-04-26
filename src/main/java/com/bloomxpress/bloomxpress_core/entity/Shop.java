package com.bloomxpress.bloomxpress_core.entity;

import com.bloomxpress.bloomxpress_core.enums.ShopStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.List;

@Entity
@Table(name = "shop")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(length = 45)
    private String email;

    @Column(name = "phone_number", length = 45)
    private String phoneNumber;

    private String description;

    @Column(name = "page_link", length = 45)
    private String pageLink;

    private String address;

    @Column(nullable = false, length = 45)
    private String country;

    @Column(name = "image_url", length = 45)
    private String imageUrl;

    private Integer clicks = 0;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private ShopStatus status;

    // Many-to-Many Relationship
    @ManyToMany
    @JoinTable(
            name = "shop_has_category",
            schema = "bloomxpress",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    // One-to-Many Relationship
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<ShopClickHistory> clickHistory;
}