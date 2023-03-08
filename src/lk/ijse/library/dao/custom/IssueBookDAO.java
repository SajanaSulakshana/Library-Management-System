package lk.ijse.library.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dto.IssueDTO;

import java.sql.SQLException;

public interface IssueBookDAO extends CrudDAO<IssueDTO,String> {
    ObservableList<IssueDTO> getAllRecords() throws SQLException, ClassNotFoundException;
    String getIssueBookCount() throws SQLException, ClassNotFoundException;
    String getPendingReturnBooks() throws SQLException, ClassNotFoundException;
    String getNewIssueBook() throws SQLException, ClassNotFoundException;
    String getLastIssueBookId() throws SQLException, ClassNotFoundException;
    boolean isAlreadyIssued(String bId) throws SQLException, ClassNotFoundException;
}
