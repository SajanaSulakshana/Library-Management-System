package lk.ijse.library.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dto.IssueDTO;

import java.sql.SQLException;

public interface IssueBookService {
    boolean addRecordBook(IssueDTO issuebook) throws SQLException, ClassNotFoundException;
    ObservableList<IssueDTO> getAllRecords() throws SQLException, ClassNotFoundException;
    String getIssueBookCount() throws SQLException, ClassNotFoundException;
    IssueDTO searchRecord(String id) throws SQLException, ClassNotFoundException;
    boolean updateRecord(IssueDTO issueDTO1) throws SQLException, ClassNotFoundException;
    String getPendingReturnBooks() throws SQLException, ClassNotFoundException;
    String getNewIssueBook() throws SQLException, ClassNotFoundException;
    String getLastIssueBookId() throws SQLException, ClassNotFoundException;
    boolean deleteIssueBookRecord(String id) throws SQLException, ClassNotFoundException;
    boolean isAlreadyIssued(String bId) throws SQLException, ClassNotFoundException;
}
