package lk.ijse.library.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.DonationDTO;

import java.sql.SQLException;

public interface DonationDAO extends CrudDAO<DonationDTO,String> {
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastDonationId() throws SQLException, ClassNotFoundException;
    ObservableList<BookDTO> getAllDonation() throws SQLException, ClassNotFoundException;
}
