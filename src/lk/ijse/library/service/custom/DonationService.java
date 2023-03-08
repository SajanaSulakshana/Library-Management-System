package lk.ijse.library.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.DonationDTO;

import java.sql.SQLException;

public interface DonationService {
    boolean addDonation(DonationDTO donationDTO) throws SQLException, ClassNotFoundException;
    boolean updateDonation(DonationDTO donationDTO) throws SQLException, ClassNotFoundException;
    DonationDTO searchDonation(String id) throws SQLException, ClassNotFoundException;
    boolean deleteDonation(String id) throws SQLException, ClassNotFoundException;
    String getNewModule() throws SQLException, ClassNotFoundException;
    String getLastDonationId() throws SQLException, ClassNotFoundException;
    ObservableList<BookDTO> getAllDonation() throws SQLException, ClassNotFoundException;
}
