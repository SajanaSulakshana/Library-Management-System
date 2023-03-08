package lk.ijse.library.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.IncomeDAO;
import lk.ijse.library.dto.IncomeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeDAOImpl implements IncomeDAO {
    @Override
    public ObservableList<IncomeDTO> getAllIncome() throws SQLException, ClassNotFoundException {
        ObservableList<IncomeDTO> ob= FXCollections.observableArrayList();


        ResultSet rs = CrudUtil.execute("SELECT * from fine");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ob.add(new IncomeDTO(
                    rs.getString(1),
                    rs.getDouble(3),
                    rs.getString(4)));

        }
        return ob;
    }
}
