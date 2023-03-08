package lk.ijse.library.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dto.BookDTO;

import java.sql.SQLException;

public interface BookDAO extends CrudDAO<BookDTO,String> {
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastCourseId() throws SQLException, ClassNotFoundException;
    String getBookCount() throws SQLException, ClassNotFoundException;
    ObservableList<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException;
    String getMemberCount() throws SQLException, ClassNotFoundException;
    String LateDate(String bookRecordId) throws SQLException, ClassNotFoundException;
}
