package lk.ijse.library.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.custom.EmployeeSalaryDAO;
import lk.ijse.library.dao.custom.impl.EmployeeSalaryDAOImpl;
import lk.ijse.library.dto.SalaryDTO;
import lk.ijse.library.service.custom.EmployeeSalaryService;

import java.sql.SQLException;

public class EmployeeSalaryServiceImpl implements EmployeeSalaryService {
    EmployeeSalaryDAO employeeSalaryDAO=new EmployeeSalaryDAOImpl();
    @Override
    public boolean addSalary(SalaryDTO employeeSalaryDTO) throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.add(employeeSalaryDTO);
    }

    @Override
    public SalaryDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.search(id);
    }

    @Override
    public ObservableList<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.getAllSalaries();
    }

    @Override
    public boolean deleteEmployeeSalary(String id) throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.delete(id);
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.getNewModule();
    }

    @Override
    public String getLastEmSalaryId() throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.getLastEmSalaryId();
    }
}
