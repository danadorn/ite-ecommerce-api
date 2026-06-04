package co.istad.ecommerce.model.repository;

import co.istad.ecommerce.model.domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
