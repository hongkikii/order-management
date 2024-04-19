package kr.co.ordermanagement.presentation.controller;

import java.util.List;
import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.presentation.dto.ChangeStateRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private SimpleOrderService simpleOrderService;

    @Autowired
    OrderRestController(SimpleOrderService simpleOrderService) {
        this.simpleOrderService = simpleOrderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody List<OrderProductRequestDto> orderCreateRequestDtos) {
        OrderResponseDto orderResponseDto = simpleOrderService.createOrder(orderCreateRequestDtos);
        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        OrderResponseDto orderResponseDto = simpleOrderService.findById(orderId);
        return ResponseEntity.ok(orderResponseDto);
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> changeOrderState(@PathVariable Long orderId,
                                                             @RequestBody ChangeStateRequestDto changeStateRequestDto) {
        OrderResponseDto orderResponseDto = simpleOrderService.changeState(orderId, changeStateRequestDto);
        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByState(@RequestParam State state) {
        List<OrderResponseDto> orderResponseDtos = simpleOrderService.findByState(state);
        return ResponseEntity.ok(orderResponseDtos);
    }

    // 주문 취소 API
    @PatchMapping("/orders/{orderId}/cancel")
    public ResponseEntity<OrderResponseDto> cancelOrderById(@PathVariable Long orderId) {
        OrderResponseDto orderResponseDto = simpleOrderService.cancelOrderById(orderId);
        return ResponseEntity.ok(orderResponseDto);
    }
}
