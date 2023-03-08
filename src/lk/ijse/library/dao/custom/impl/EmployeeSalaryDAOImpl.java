package lk.ijse.library.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.EmployeeSalaryDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.SalaryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeSalaryDAOImpl implements EmployeeSalaryDAO {
    @Override
    public boolean add(SalaryDTO employeeSalaryDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        return CrudUtil.execute("Insert into employee_salary values(?,?,?)", employeeSalaryDTO.getEmployee_id(), employeeSalaryDTO.getSalary(), employeeSalaryDTO.getDate());

    }

    @Override
    public SalaryDTO search(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Select * from employee_salary where Employee_Id=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            SalaryDTO salaryDTO1 =new SalaryDTO(rst.getString(1),rst.getDouble(2), rst.getString(3));
            return salaryDTO1;
        }
        return  null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From employee_salary where Employee_Id='"+id+"'")>0;

    }

    @Override
    public boolean update(SalaryDTO obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastEmSalaryId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Employee_Id from employee_salary order by Employee_Id DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastEmSalaryId=getLastEmSalaryId();
        if(lastEmSalaryId==null){
            return "E-00000001";
        }else{
            String[] split=lastEmSalaryId.split("[E][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newBookId=String.format("E-%08d",lastDigits);
            return newBookId;
        }
    }

    @Override
    public ObservableList<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException {
        ObservableList<SalaryDTO> ob= FXCollections.observableArrayList();


        ResultSet  rs = CrudUtil.execute("SELECT * from employee_salary");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ob.add(new SalaryDTO(
                    rs.getString(1),
                    rs.getDouble(2),
                    rs.getString(3)));

        }
        return ob;
    }
}
