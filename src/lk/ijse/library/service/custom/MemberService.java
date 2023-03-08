package lk.ijse.library.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dto.MemberDTO;

import java.sql.SQLException;

public interface MemberService {
    MemberDTO searchMember(String id) throws SQLException, ClassNotFoundException;
    boolean addMember(MemberDTO memberDTO) throws SQLException, ClassNotFoundException;
    boolean updateMember(MemberDTO memberDTO) throws SQLException, ClassNotFoundException;
    boolean deleteMember(String id) throws SQLException, ClassNotFoundException;
    String getNewMember() throws SQLException, ClassNotFoundException;
    String getLastMemberId() throws SQLException, ClassNotFoundException;
    ObservableList<MemberDTO> getAllMembers() throws SQLException, ClassNotFoundException;

}
