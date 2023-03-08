package lk.ijse.library.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.ExpentureDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.ExpentureDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExpentureDAOImpl implements ExpentureDAO {

    @Override
    public boolean add(ExpentureDTO expentureDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        return CrudUtil.execute("Insert into expenture values(?,?,?,?)", expentureDTO.getInvoiceNumber(), expentureDTO.getDescription(), expentureDTO.getDate(), expentureDTO.getPayment());


    }

    @Override
    public ExpentureDTO search(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Select * from expenture where Invoice_Number=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            ExpentureDTO expentureDTO =new ExpentureDTO(rst.getString(1),rst.getString(2), rst.getString(3), rst.getDouble(4));
            return expentureDTO;
        }
        return  null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From expenture where Invoice_Number='"+id+"'")>0;


    }



    @Override
    public boolean update(ExpentureDTO expentureDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update expenture set Expenture_Description=?,Expenture_Date=?,Expenture_Payment=? where Invoice_Number=?",
                expentureDTO.getDescription(), expentureDTO.getDate(), expentureDTO.getPayment(), expentureDTO.getInvoiceNumber());
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastExpentureId=getLastExpentureId();
        if(lastExpentureId==null){
            return "E-00000001";
        }else{
            String[] split=lastExpentureId.split("[E][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newExpentureId=String.format("E-%08d",lastDigits);
            return newExpentureId;
        }
    }

    @Override
    public String getLastExpentureId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT  Invoice_Number from expenture order by  Invoice_Number DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public List<ExpentureDTO> getAllExpenture() throws SQLException, ClassNotFoundException {
        ObservableList<ExpentureDTO> ob= FXCollections.observableArrayList();


        ResultSet  rs = CrudUtil.execute("SELECT * from expenture");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ob.add(new ExpentureDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4)));

        }
        return ob;
    }
}
