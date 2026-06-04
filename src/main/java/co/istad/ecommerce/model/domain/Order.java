package co.istad.ecommerce.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
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
    @Column(nullable = false)
    private Float discount;
    @Column(nullable = false)
    private String remark;
    @Column(nullable = false)
    private String status;
    private LocalDateTime orderedAt;


    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
}
