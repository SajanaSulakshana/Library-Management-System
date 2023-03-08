package lk.ijse.library.service.custom.impl;

import lk.ijse.library.dao.custom.FineDAO;
import lk.ijse.library.dao.custom.impl.FineDAOImpl;
import lk.ijse.library.dto.FineDTO;
import lk.ijse.library.service.custom.FineService;

import java.sql.SQLException;
import java.util.HashMap;

public class FineServiceImpl implements FineService {
    FineDAO fineDAO=new FineDAOImpl();
    @Override
    public boolean addFine(FineDTO fineDTO) throws SQLException, ClassNotFoundException {
        return fineDAO.addFine(fineDTO);
    }

    @Override
    public HashMap getIncomeByMonthlyFroChart(int year) throws SQLException, ClassNotFoundException {
        return fineDAO.getIncomeByMonthlyFroChart(year);
    }
}
