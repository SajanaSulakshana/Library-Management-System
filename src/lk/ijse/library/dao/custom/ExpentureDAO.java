package lk.ijse.library.dao.custom;

import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dto.ExpentureDTO;

import java.sql.SQLException;
import java.util.List;

public interface ExpentureDAO extends CrudDAO<ExpentureDTO,String> {
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastExpentureId() throws SQLException, ClassNotFoundException;
    List<ExpentureDTO> getAllExpenture() throws SQLException, ClassNotFoundException;
}
