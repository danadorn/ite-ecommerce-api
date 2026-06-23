package co.istad.ecommerce.feature.Order;


import co.istad.ecommerce.feature.Product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_lines")
@Setter
@Getter
@NoArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
//    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
//    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private BigDecimal unitPrice;
}
