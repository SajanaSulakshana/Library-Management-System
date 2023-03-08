package lk.ijse.library.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dto.SalaryDTO;

import java.sql.SQLException;

public interface EmployeeSalaryService {
    boolean addSalary(SalaryDTO employeeSalaryDTO) throws SQLException, ClassNotFoundException;
    SalaryDTO searchEmployee(String id) throws SQLException, ClassNotFoundException;
    ObservableList<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException;
    boolean deleteEmployeeSalary(String id) throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastEmSalaryId() throws SQLException, ClassNotFoundException;
}
