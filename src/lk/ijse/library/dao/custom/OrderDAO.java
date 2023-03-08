package lk.ijse.library.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dto.OrderDTO;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderDTO,String> {
    ObservableList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;
    String getNewOrder() throws SQLException, ClassNotFoundException;
    String getLastOrderId() throws SQLException, ClassNotFoundException;
}
