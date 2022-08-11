package api.service;

import api.model.Order;
import api.model.ShoppingCart;
import api.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
