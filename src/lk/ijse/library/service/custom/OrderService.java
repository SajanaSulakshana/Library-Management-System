package lk.ijse.library.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dto.OrderDTO;

import java.sql.SQLException;

public interface OrderService {
    boolean addOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    OrderDTO searchOrder(String id) throws SQLException, ClassNotFoundException;
    boolean updateOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
    ObservableList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;
    String getNewOrder() throws SQLException, ClassNotFoundException;
    String getLastOrderId() throws SQLException, ClassNotFoundException;
}
