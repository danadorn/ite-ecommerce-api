package co.istad.ecommerce.feature.Order.impl;

//import co.istad.ecommerce.feature.Order.*;
//import co.istad.ecommerce.feature.Order.dto.CreateOrderRequest;
//import co.istad.ecommerce.feature.Order.dto.OrderResponse;
//import co.istad.ecommerce.feature.Product.Product;
//import co.istad.ecommerce.feature.Product.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class OrderServiceImpl implements OrderService {
//    private final OrderRepository orderRepository;
//    private final ProductRepository productRepository;
//    private final OrderMapper orderMapper;
//
//
//    @Override
//    public OrderResponse createNew(CreateOrderRequest createOrderRequest) {
//
//        final Order order = orderMapper.mapCreateOrderRequestToOrder(createOrderRequest);
//
//        List<OrderLine> orderLines = new ArrayList<>();
//        //validate order list
//        boolean isValidOrder = createOrderRequest.orderLines().stream()
//                .allMatch(orderLineDto -> {
//                    Optional<Product> productOptional = productRepository
//                            .findByCode(orderLineDto.code());
//                    if(productOptional.isPresent()){
//                        OrderLine orderLine = new OrderLine();
//                        orderLine.setProduct(productOptional.get());
//                        orderLine.setQty(orderLineDto.qty());
//                        orderLine.setOrder(order);
//                        orderLines.add(orderLine);
//                        return true;
//                    }
//                    return false;
//                });
//
//        if (!isValidOrder){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Invalid order Line");
//        }
//
//        order.setCustomerId("ISTAD");
//
//        order.setOrderLines(orderLines);
//        order.setOrderedAt(LocalDateTime.now());
//        order.setStatus(false);
//        order.setIsDeleted(false);
//
//        Order saveOrder = orderRepository.save(order);
//
//
//
//        return orderMapper.mapOrderToOrderResponse(saveOrder);
//    }
//
//}



import co.istad.ecommerce.feature.Category.Category;
import co.istad.ecommerce.feature.Order.*;
import co.istad.ecommerce.feature.Order.dto.CreateOrderRequest;
import co.istad.ecommerce.feature.Order.dto.OrderResponse;
import co.istad.ecommerce.feature.Product.Product;
import co.istad.ecommerce.feature.Product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;


    @Override
    public OrderResponse createNew(CreateOrderRequest createOrderRequest) {

        final Order order = orderMapper.mapCreateOrderRequestToOrder(createOrderRequest);

        List<OrderLine> orderLines = new ArrayList<>();
        //validate order list
        boolean isValidOrder = createOrderRequest.orderLines().stream()
                .allMatch(orderLineDto -> {
                    Optional<Product> productOptional = productRepository
                            .findByCode(orderLineDto.code());
                    if(productOptional.isPresent()){
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(productOptional.get());
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setUnitPrice(orderLineDto.unitPrice());
                        orderLine.setOrder(order);
                        orderLines.add(orderLine);
                        return true;
                    }
                    return false;
                });

        if (!isValidOrder){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid order Line");
        }



        order.setCustomerId("ISTAD");

        order.setOrderLines(orderLines);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(false);
        order.setIsDeleted(false);



        Order saveOrder = orderRepository.save(order);


        return orderMapper.mapOrderToOrderResponse(saveOrder);
    }

    @Override
    public Page<OrderResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        return orderRepository.findAll(pageRequest)
                .map(orderMapper::mapOrderToOrderResponse);
    }

    @Override
    public void softDeleteById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order has not been found"));
        order.setIsDeleted(true);
        orderRepository.save(order);
    }

    @Override
    public void hardDeleteById(UUID id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order has not been found"));
        orderRepository.deleteById(id);
    }

    @Override
    public void setPaymentStatus(UUID id, Boolean status) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order has not been found")
        );
        order.setStatus(status);
    }


}
