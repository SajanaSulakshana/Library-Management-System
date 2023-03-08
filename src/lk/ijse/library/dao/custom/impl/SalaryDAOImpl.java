package lk.ijse.library.dao.custom.impl;

import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.SalaryDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.SalaryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryDAOImpl implements SalaryDAO {
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


   /* @Override
    public boolean delete(SalaryDTO id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From employee_salary where Employee_Id='"+id+"'")>0;

    }*/

    @Override
    public boolean update(SalaryDTO obj) throws SQLException, ClassNotFoundException {
        throw new RuntimeException("Update Not Available");
    }
}
