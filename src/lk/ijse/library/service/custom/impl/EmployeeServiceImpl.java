package lk.ijse.library.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.custom.EmployeeDAO;
import lk.ijse.library.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.library.dto.EmployeeDTO;
import lk.ijse.library.service.custom.EmployeeService;

import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(employeeDTO);
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(employeeDTO);
    }

    @Override
    public ObservableList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllEmployee();
    }

    @Override
    public String getEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeCount();
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNewModule();
    }

    @Override
    public String getLastEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getLastEmployeeId();
    }
}
