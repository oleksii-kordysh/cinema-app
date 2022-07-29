package api.dao;

import java.util.List;
import api.model.Order;
import api.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getByUser(User user);
}
