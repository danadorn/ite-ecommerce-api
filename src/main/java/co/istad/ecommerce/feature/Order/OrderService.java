package co.istad.ecommerce.feature.Order;

import co.istad.ecommerce.feature.Order.dto.CreateOrderRequest;
import co.istad.ecommerce.feature.Order.dto.OrderResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {

    OrderResponse createNew(CreateOrderRequest createOrderRequest);

    Page<OrderResponse> findAll(int pageNumber, int pageSize);

    void softDeleteById(UUID id);

    void hardDeleteById(UUID id);

    void setPaymentStatus(UUID id, Boolean status);


}
