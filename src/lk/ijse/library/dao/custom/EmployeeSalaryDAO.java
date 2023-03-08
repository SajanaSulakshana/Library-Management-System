package lk.ijse.library.dao.custom;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dto.SalaryDTO;

import java.sql.SQLException;

public interface EmployeeSalaryDAO extends CrudDAO<SalaryDTO,String> {
    String getLastEmSalaryId() throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;
    ObservableList<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException;


}
