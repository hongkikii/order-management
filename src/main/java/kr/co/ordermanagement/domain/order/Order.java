package kr.co.ordermanagement.domain.order;

import java.util.List;
import kr.co.ordermanagement.domain.product.Product;

public class Order {
    private Long id;
    private List<Product> orderedProducts;
    private Integer totalPrice;
    private String state;

    public Order(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
        this.totalPrice = calculateTotalPrice(orderedProducts);
        this.state = "CREATED";
    }

    public Long getId() {
        return id;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getState() {
        return state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }

    private Integer calculateTotalPrice(List<Product> orderedProducts) {
        return orderedProducts
                .stream()
                .mapToInt(orderedProduct -> orderedProduct.getPrice() * orderedProduct.getAmount())
                .sum();
    }

    public void changeStateForce(String state) {
        this.state = state;
    }

    public boolean sameState(String state) {
        return this.state.equals(state);
    }

    public void cancel() {
        if (!this.state.equals("CREATED")) {
            throw new RuntimeException("이미 취소되었거나 취소할 수 없는 주문상태입니다.");
        }
        this.state = "CANCELED";
    }
}
