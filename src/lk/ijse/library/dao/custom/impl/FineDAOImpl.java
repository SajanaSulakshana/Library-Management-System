package lk.ijse.library.dao.custom.impl;

import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.FineDAO;
import lk.ijse.library.dto.FineDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class FineDAOImpl implements FineDAO {
    @Override
    public boolean addFine(FineDTO fineDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into fine values(?,?,?,?)", fineDTO.getBookRecordId(), fineDTO.getDateCount(),
                fineDTO.getFine(), fineDTO.getDate());

    }

    @Override
    public HashMap getIncomeByMonthlyFroChart(int year) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select sum(fine) as total,Month(date) as month,Year(date) as year from fine group" +
                " by month having year = ?", year);

        HashMap hm = new HashMap();
        while (rs.next()){
            hm.put(rs.getInt(2),rs.getDouble(1));
        }
        return hm;
    }
}
