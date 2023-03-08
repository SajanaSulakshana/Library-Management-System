package lk.ijse.library.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dto.EmployeeDTO;

import java.sql.SQLException;

public interface EmployeeService {
    boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
    EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
    ObservableList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;
    String getEmployeeCount() throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastEmployeeId() throws SQLException, ClassNotFoundException;

}
