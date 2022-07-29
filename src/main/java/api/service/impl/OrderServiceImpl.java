package api.service.impl;

import api.dao.OrderDao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import api.lib.Inject;
import api.lib.Service;
import api.model.Order;
import api.model.ShoppingCart;
import api.model.User;
import api.service.OrderService;
import api.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(shoppingCart.getUser());
        order.setTickets(new ArrayList<>(shoppingCart.getTickets()));
        shoppingCartService.clearShoppingCart(shoppingCart);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getByUser(user);
    }
}
