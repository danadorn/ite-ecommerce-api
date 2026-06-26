package co.istad.ecommerce.feature.Order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String customerId;
    @Column(nullable = false)
    private String address;
    @Column(nullable = true)
    private Float discount;
    @Column(nullable = false)
    private String remark;
    @Column(nullable = false)
    private Boolean status;
    private LocalDateTime orderedAt;
    @Column(nullable = false)
    private Boolean isDeleted;


    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST) // save from order repo to order_line repo
    private List<OrderLine> orderLines;
}
