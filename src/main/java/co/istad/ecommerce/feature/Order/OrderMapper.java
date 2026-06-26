package co.istad.ecommerce.feature.Order;


import co.istad.ecommerce.feature.Order.dto.CreateOrderRequest;
import co.istad.ecommerce.feature.Order.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse mapOrderToOrderResponse(Order order);

    Order mapCreateOrderRequestToOrder(CreateOrderRequest createOrderRequest);
}
