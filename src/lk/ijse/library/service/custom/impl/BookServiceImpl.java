package lk.ijse.library.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.custom.BookDAO;
import lk.ijse.library.dao.custom.impl.BookDAOImpl;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.service.custom.BookService;

import java.sql.SQLException;

public class BookServiceImpl implements BookService {
    BookDAO bookDAO = new BookDAOImpl();
    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return bookDAO.getNewModule();
    }

    @Override
    public BookDTO searchBook(String id) throws SQLException, ClassNotFoundException {
        return bookDAO.search(id);
    }

    @Override
    public boolean updateBook(BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        return bookDAO.update(bookDTO);
    }

    @Override
    public boolean deleteBook(String id) throws SQLException, ClassNotFoundException {
        return bookDAO.delete(id);
    }

    @Override
    public boolean addBook(BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        return bookDAO.add(bookDTO);
    }

    @Override
    public ObservableList<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException {
        return bookDAO.getAllBooks();
    }

    @Override
    public String getLastCourseId() throws SQLException, ClassNotFoundException {
        return bookDAO.getLastCourseId();
    }

    @Override
    public String getBookCount() throws SQLException, ClassNotFoundException {
        return bookDAO.getBookCount();
    }

    @Override
    public String getMemberCount() throws SQLException, ClassNotFoundException {
        return bookDAO.getMemberCount();
    }

    @Override
    public String LateDate(String bookRecordId) throws SQLException, ClassNotFoundException {
        return bookDAO.LateDate(bookRecordId);
    }


}
