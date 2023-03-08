package lk.ijse.library.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.custom.OrderDAO;
import lk.ijse.library.dao.custom.impl.OrderDAOImpl;
import lk.ijse.library.dto.OrderDTO;
import lk.ijse.library.service.custom.OrderService;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    OrderDAO orderDAO=new OrderDAOImpl();

    @Override
    public boolean addOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.add(orderDTO);
    }

    @Override
    public OrderDTO searchOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.search(id);
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.update(orderDTO);
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public ObservableList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        return orderDAO.getAllOrders();
    }

    @Override
    public String getNewOrder() throws SQLException, ClassNotFoundException {
        return orderDAO.getNewOrder();
    }

    @Override
    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getLastOrderId();
    }
}
