package lk.ijse.library.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dto.IncomeDTO;

import java.sql.SQLException;

public interface IncomeService {
    ObservableList<IncomeDTO> getAllIncome() throws SQLException, ClassNotFoundException;
}
