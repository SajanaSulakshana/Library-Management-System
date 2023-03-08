package lk.ijse.library.dao;
import java.sql.SQLException;

public interface CrudDAO <T,ID>{
    boolean add(T obj) throws SQLException,ClassNotFoundException;
    T search(ID obj) throws SQLException,ClassNotFoundException;
    boolean delete(ID obj) throws SQLException,ClassNotFoundException;
    boolean update(T obj) throws SQLException,ClassNotFoundException;
}
