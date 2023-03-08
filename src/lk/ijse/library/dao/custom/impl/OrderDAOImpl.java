package lk.ijse.library.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.OrderDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        return CrudUtil.execute("Insert into oder values(?,?,?,?)", orderDTO.getId(), orderDTO.getType(), orderDTO.getPrice(), orderDTO.getDate());

    }

    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Select * from oder where Oder_Id=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            OrderDTO orderDTO =new OrderDTO(rst.getString(1),rst.getString(2), rst.getDouble(3), rst.getString(4));
            return orderDTO;
        }
        return  null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From oder where Oder_Id='"+id+"'")>0;


    }



    @Override
    public boolean update(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update oder set Order_Type=?,Oder_Price=?,Oder_Date=? where Oder_Id=?",
                orderDTO.getType(), orderDTO.getPrice(), orderDTO.getDate(), orderDTO.getId());
    }

    @Override
    public ObservableList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ObservableList<OrderDTO> ob= FXCollections.observableArrayList();


        ResultSet  rs = CrudUtil.execute("SELECT * from oder");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ob.add(new OrderDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4)));

        }
        return ob;
    }

    @Override
    public String getNewOrder() throws SQLException, ClassNotFoundException {
        String lastOrderId=getLastOrderId();
        System.out.println(lastOrderId);
        if(lastOrderId==null){
            return "O-00000001";
        }else {
            String[] split=lastOrderId.split("[O][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newOrderId=String.format("O-%08d",lastDigits);
            return newOrderId;
        }
    }

    @Override
    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Oder_Id from oder order by Oder_Id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
