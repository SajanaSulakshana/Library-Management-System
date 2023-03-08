package lk.ijse.library.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.custom.MemberDAO;
import lk.ijse.library.dao.custom.impl.MemberDAOImpl;
import lk.ijse.library.dto.MemberDTO;
import lk.ijse.library.service.custom.MemberService;

import java.sql.SQLException;

public class MemberServiceImpl implements MemberService {
    MemberDAO memberDAO = new MemberDAOImpl();
    @Override
    public MemberDTO searchMember(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.search(id);
    }

    @Override
    public boolean addMember(MemberDTO memberDTO) throws SQLException, ClassNotFoundException {
        return memberDAO.add(memberDTO);
    }

    @Override
    public boolean updateMember(MemberDTO memberDTO) throws SQLException, ClassNotFoundException {
        return memberDAO.update(memberDTO);
    }

    @Override
    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.delete(id);
    }

    @Override
    public String getNewMember() throws SQLException, ClassNotFoundException {
        return memberDAO.getNewMember();
    }

    @Override
    public String getLastMemberId() throws SQLException, ClassNotFoundException {
        return memberDAO.getLastMemberId();
    }

    @Override
    public ObservableList<MemberDTO> getAllMembers() throws SQLException, ClassNotFoundException {
        return memberDAO.getAllMembers();
    }
}
