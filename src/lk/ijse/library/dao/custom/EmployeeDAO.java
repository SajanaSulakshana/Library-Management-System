package lk.ijse.library.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dto.EmployeeDTO;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<EmployeeDTO,String> {
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastEmployeeId() throws SQLException, ClassNotFoundException;
    ObservableList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;
    String getEmployeeCount() throws SQLException, ClassNotFoundException;


}
