package lk.ijse.library.service.custom;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import lk.ijse.library.dto.BookDTO;

import java.sql.SQLException;

public interface BookService {
    String getNewModule() throws SQLException, ClassNotFoundException;
    BookDTO searchBook(String id) throws SQLException, ClassNotFoundException;
    boolean updateBook(BookDTO bookDTO) throws SQLException, ClassNotFoundException;
    boolean deleteBook(String id) throws SQLException, ClassNotFoundException;
    boolean addBook(BookDTO bookDTO) throws SQLException, ClassNotFoundException;
    ObservableList<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException;
    String getLastCourseId() throws SQLException, ClassNotFoundException;
    String getBookCount() throws SQLException, ClassNotFoundException;
    String getMemberCount() throws SQLException, ClassNotFoundException;
    String LateDate(String bookRecordId) throws SQLException, ClassNotFoundException;

}
