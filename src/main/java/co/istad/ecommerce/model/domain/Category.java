package co.istad.ecommerce.model.domain;


import co.istad.ecommerce.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
    private String description;
    private String icon;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.REMOVE)
    private List<Category> categories;

    @ManyToOne
    private Category parentCategory;
}
