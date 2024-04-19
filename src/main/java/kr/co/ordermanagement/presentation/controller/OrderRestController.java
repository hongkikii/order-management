package kr.co.ordermanagement.presentation.controller;

import java.util.List;
import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private SimpleOrderService simpleOrderService;

    @Autowired
    OrderRestController(SimpleOrderService simpleOrderService) {
        this.simpleOrderService = simpleOrderService;
    }

    // 상품 주문 API
    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> create(@RequestBody List<OrderProductRequestDto> orderCreateRequestDtos) {
        OrderResponseDto orderCreateResponseDto = simpleOrderService.createOrder(orderCreateRequestDtos);
        return ResponseEntity.ok(orderCreateResponseDto);
    }
}
