package lk.ijse.library.service.custom.impl;

import lk.ijse.library.dao.custom.ExpentureDAO;
import lk.ijse.library.dao.custom.impl.ExpentureDAOImpl;
import lk.ijse.library.dto.ExpentureDTO;
import lk.ijse.library.service.custom.ExpentureService;

import java.sql.SQLException;
import java.util.List;

public class ExpentureServiceImpl implements ExpentureService {
    ExpentureDAO expentureDAO=new ExpentureDAOImpl();
    @Override
    public boolean addExpenture(ExpentureDTO expentureDTO) throws SQLException, ClassNotFoundException {
        return expentureDAO.add(expentureDTO);
    }

    @Override
    public boolean updateExpenture(ExpentureDTO expentureDTO) throws SQLException, ClassNotFoundException {
        return expentureDAO.update(expentureDTO);
    }

    @Override
    public ExpentureDTO searchExpenture(String id) throws SQLException, ClassNotFoundException {
        return expentureDAO.search(id);
    }

    @Override
    public boolean deleteExpenture(String id) throws SQLException, ClassNotFoundException {
        return expentureDAO.delete(id);
    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        return expentureDAO.getNewModule();
    }

    @Override
    public String getLastExpentureId() throws SQLException, ClassNotFoundException {
        return expentureDAO.getLastExpentureId();
    }

    @Override
    public List<ExpentureDTO> getAllExpenture() throws SQLException, ClassNotFoundException {
        return expentureDAO.getAllExpenture();
    }
}
