package lk.ijse.library.service.custom;

import lk.ijse.library.dto.ExpentureDTO;

import java.sql.SQLException;
import java.util.List;

public interface ExpentureService {
    boolean addExpenture(ExpentureDTO expentureDTO) throws SQLException, ClassNotFoundException;
    boolean updateExpenture(ExpentureDTO expentureDTO) throws SQLException, ClassNotFoundException;
    ExpentureDTO searchExpenture(String id) throws SQLException, ClassNotFoundException;
    boolean deleteExpenture(String id) throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastExpentureId() throws SQLException, ClassNotFoundException;
    List<ExpentureDTO> getAllExpenture() throws SQLException, ClassNotFoundException;

}
