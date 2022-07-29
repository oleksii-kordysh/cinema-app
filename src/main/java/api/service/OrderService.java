package api.service;

import java.util.List;
import api.model.Order;
import api.model.ShoppingCart;
import api.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
