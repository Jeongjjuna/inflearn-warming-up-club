package day4;

import java.util.List;

public class Order {

    private final List<String> orders;
    private final List<String> userInfo;
    private final int totalPrice;

    public Order(List<String> orders, List<String> userInfo, int totalPrice) {
        this.orders = orders;
        this.userInfo = userInfo;
        this.totalPrice = totalPrice;
    }

    public boolean hasNoItem() {
        return orders.isEmpty();
    }

    public boolean isTotalPriceLessThanOrEqualTo(int price) {
        return totalPrice <= price;
    }

    public boolean isWithoutCustomerInfo() {
        return userInfo.isEmpty();
    }
}
