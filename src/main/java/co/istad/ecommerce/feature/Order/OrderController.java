package co.istad.ecommerce.feature.Order;

import co.istad.ecommerce.feature.Order.dto.CreateOrderRequest;
import co.istad.ecommerce.feature.Order.dto.OrderResponse;
import co.istad.ecommerce.feature.Product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponse createNew(@Valid @RequestBody CreateOrderRequest createOrderRequest) {

        return orderService.createNew(createOrderRequest);
    }

    @GetMapping()
    public Page<OrderResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return orderService.findAll(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteById(@PathVariable UUID id) {
        orderService.softDeleteById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDeleteById(@PathVariable UUID id) {
        orderService.hardDeleteById(id);
    }


    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public void setPaymentStatus(@PathVariable UUID id, @RequestBody Boolean status){
        orderService.setPaymentStatus(id, status);
    }


}
