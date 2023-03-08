package lk.ijse.library.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.custom.DonationDAO;
import lk.ijse.library.dao.custom.impl.DonationDAOImpl;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.DonationDTO;
import lk.ijse.library.service.custom.DonationService;

import java.sql.SQLException;

public class DonationServiceImpl implements DonationService {
    DonationDAO donationDAO = new DonationDAOImpl();
    @Override
    public boolean addDonation(DonationDTO donationDTO) throws SQLException, ClassNotFoundException {
        return donationDAO.add(donationDTO);
    }

    @Override
    public boolean updateDonation(DonationDTO donationDTO) throws SQLException, ClassNotFoundException {
        return donationDAO.update(donationDTO);
    }

    @Override
    public DonationDTO searchDonation(String id) throws SQLException, ClassNotFoundException {
        return donationDAO.search(id);
    }

    @Override
    public boolean deleteDonation(String id) throws SQLException, ClassNotFoundException {
        return donationDAO.delete(id);
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return donationDAO.getNewModule();
    }

    @Override
    public String getLastDonationId() throws SQLException, ClassNotFoundException {
        return donationDAO.getLastDonationId();
    }

    @Override
    public ObservableList<BookDTO> getAllDonation() throws SQLException, ClassNotFoundException {
        return donationDAO.getAllDonation();
    }
}
