package lk.ijse.library.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.custom.IssueBookDAO;
import lk.ijse.library.dao.custom.impl.IssueBookDAOImpl;
import lk.ijse.library.dto.IssueDTO;
import lk.ijse.library.service.custom.IssueBookService;

import java.sql.SQLException;

public class IssueBookServiceImpl implements IssueBookService {
    IssueBookDAO issueBookDAO=new IssueBookDAOImpl();
    @Override
    public boolean addRecordBook(IssueDTO issuebook) throws SQLException, ClassNotFoundException {
        return issueBookDAO.add(issuebook);
    }

    @Override
    public ObservableList<IssueDTO> getAllRecords() throws SQLException, ClassNotFoundException {
        return issueBookDAO.getAllRecords();
    }

    @Override
    public String getIssueBookCount() throws SQLException, ClassNotFoundException {
        return issueBookDAO.getIssueBookCount();
    }

    @Override
    public IssueDTO searchRecord(String id) throws SQLException, ClassNotFoundException {
        return issueBookDAO.search(id);
    }

    @Override
    public boolean updateRecord(IssueDTO issueDTO1) throws SQLException, ClassNotFoundException {
        return issueBookDAO.update(issueDTO1);
    }

    @Override
    public String getPendingReturnBooks() throws SQLException, ClassNotFoundException {
        return issueBookDAO.getPendingReturnBooks();
    }

    @Override
    public String getNewIssueBook() throws SQLException, ClassNotFoundException {
        return issueBookDAO.getNewIssueBook();
    }

    @Override
    public String getLastIssueBookId() throws SQLException, ClassNotFoundException {
        return issueBookDAO.getLastIssueBookId();
    }

    @Override
    public boolean deleteIssueBookRecord(String id) throws SQLException, ClassNotFoundException {
        return issueBookDAO.delete(id);
    }

    @Override
    public boolean isAlreadyIssued(String bId) throws SQLException, ClassNotFoundException {
        return issueBookDAO.isAlreadyIssued(bId);
    }
}
