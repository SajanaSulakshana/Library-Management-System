package lk.ijse.library.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dto.IncomeDTO;

import java.sql.SQLException;

public interface IncomeDAO {
    ObservableList<IncomeDTO> getAllIncome() throws SQLException, ClassNotFoundException;
}
