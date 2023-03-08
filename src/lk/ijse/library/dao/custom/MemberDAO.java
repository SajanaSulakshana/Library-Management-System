package lk.ijse.library.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dto.MemberDTO;

import java.sql.SQLException;

public interface MemberDAO extends CrudDAO<MemberDTO,String> {
    String getNewMember() throws SQLException, ClassNotFoundException;
    String getLastMemberId() throws SQLException, ClassNotFoundException;
    ObservableList<MemberDTO> getAllMembers() throws SQLException, ClassNotFoundException;
}
