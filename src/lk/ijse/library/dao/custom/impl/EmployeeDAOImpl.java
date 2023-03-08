package lk.ijse.library.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.EmployeeDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean add(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            DBConnection.getInstance().getConnection().commit();
            return CrudUtil.execute("Insert into employee values(?,?,?,?,?,?,?)", employeeDTO.getId(), employeeDTO.getNic(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getContact(), employeeDTO.getSalary(), employeeDTO.getDate());
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Select * from employee where Employee_Id=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            EmployeeDTO employeeDTO =new EmployeeDTO(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getInt(5),rst.getDouble(6),rst.getString(7));
            return employeeDTO;
        }
        return  null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From employee where Employee_Id='"+id+"'")>0;

    }


    @Override
    public boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update employee set Employee_Nic=?,Employee_Name=?,Employee_Address=?,Employee_Contact=?,Employee_Salary=?,Employee_Date=? where Employee_Id=?",
                employeeDTO.getNic(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getContact(), employeeDTO.getSalary(), employeeDTO.getDate());
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastEmployeeId=getLastEmployeeId();
        if(lastEmployeeId==null){
            return "E-00000001";
        }else{
            String[] split=lastEmployeeId.split("[E][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newEmployeeId=String.format("E-%08d",lastDigits);
            return newEmployeeId;
        }
    }

    @Override
    public String getLastEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Employee_Id from employee order by Employee_Id DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ObservableList<EmployeeDTO> ob= FXCollections.observableArrayList();


        ResultSet  rs = CrudUtil.execute("SELECT * from employee");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ob.add(new EmployeeDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getDouble(6),
                    rs.getString(7)));

        }
        return ob;
    }

    @Override
    public String getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT count(Employee_Id) from employee");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
