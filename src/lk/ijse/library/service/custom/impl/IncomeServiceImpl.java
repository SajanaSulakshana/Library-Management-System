package lk.ijse.library.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.custom.IncomeDAO;
import lk.ijse.library.dao.custom.impl.IncomeDAOImpl;
import lk.ijse.library.dto.IncomeDTO;
import lk.ijse.library.service.custom.IncomeService;

import java.sql.SQLException;

public class IncomeServiceImpl implements IncomeService {
    IncomeDAO incomeDAO=new IncomeDAOImpl();
    @Override
    public ObservableList<IncomeDTO> getAllIncome() throws SQLException, ClassNotFoundException {
        return incomeDAO.getAllIncome();
    }
}
