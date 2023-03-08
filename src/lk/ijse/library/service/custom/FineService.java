package lk.ijse.library.service.custom;

import lk.ijse.library.dto.FineDTO;

import java.sql.SQLException;
import java.util.HashMap;

public interface FineService {
    boolean addFine(FineDTO fineDTO) throws SQLException, ClassNotFoundException;
    HashMap getIncomeByMonthlyFroChart(int year) throws SQLException, ClassNotFoundException;
}
