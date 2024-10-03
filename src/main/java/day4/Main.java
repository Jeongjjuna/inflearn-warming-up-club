package day4;

import java.util.List;
import java.util.logging.Logger;

/**
 * [기존 코드]
 * public boolean validateOrder(Order order) {
 *     if (order.getItems().size() == 0) {
 *         log.info("주문 항목이 없습니다.");
 *         return false;
 *     } else {
 *         if (order.getTotalPrice() > 0) {
 *             if (!order.hasCustomerInfo()) {
 *                 log.info("사용자 정보가 없습니다.");
 *                 return false;
 *             } else {
 *                 return true;
 *             }
 *         } else if (!(order.getTotalPrice() > 0)) {
 *             log.info("올바르지 않은 총 가격입니다.");
 *             return false;
 *         }
 *     }
 *     return true;
 * }
 */
public class Main {

    private static final int INVALID_PRICE_THRESHOLD = 0;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        List<String> orders = List.of("주문1", "주문2", "주문2");
        List<String> userInfo = List.of("이름", "나이");
        int totalPrice = 10000;
        Order order = new Order(orders, userInfo, totalPrice);

        try {
            validateOrder(order);
        } catch (OrderException e) {
            LOGGER.info(e.getMessage());
        } catch (Exception e) {
            LOGGER.info("[ERROR] system error");
        }
    }

    public static void validateOrder(Order order) {
        validateOrderSize(order);
        validateOrderTotalPrice(order);
        validateOrderUserInfo(order);
    }

    private static void validateOrderSize(Order order) {
        if (order.hasNoItem()) {
            throw new OrderException("주문 항목이 없습니다.");
        }
    }
    private static void validateOrderTotalPrice(Order order) {
        if (order.isTotalPriceLessThanOrEqualTo(INVALID_PRICE_THRESHOLD)) {
            throw new OrderException("올바르지 않은 총 가격입니다.");
        }
    }
    private static void validateOrderUserInfo(Order order) {
        if (order.isWithoutCustomerInfo()) {
            throw new OrderException("사용자 정보가 없습니다.");
        }
    }
}


