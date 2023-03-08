package lk.ijse.library.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.MemberDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public boolean add(MemberDTO memberDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        return CrudUtil.execute("Insert into member values(?,?,?,?)", memberDTO.getId(), memberDTO.getName(), memberDTO.getAddress(), memberDTO.getContact());

    }

    @Override
    public MemberDTO search(String id) throws SQLException, ClassNotFoundException {
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement stm=connection.prepareStatement("SELECT * from member where Member_Id=?");
        stm.setObject(1,id);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            MemberDTO memberDTO =new MemberDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4));
            return memberDTO;
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From member where Member_Id='"+id+"'")>0;


    }
    @Override
    public boolean update(MemberDTO memberDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update member set Member_Name=?,Member_Address=?,Member_Contact=? where Member_Id=?",
                memberDTO.getName(), memberDTO.getAddress(), memberDTO.getContact(), memberDTO.getId());

    }

    @Override
    public String getNewMember() throws SQLException, ClassNotFoundException {
        String lastMemberId=getLastMemberId();
        if(lastMemberId==null){
            return "M-00000001";
        }else {
            String[] split=lastMemberId.split("[M][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newMemberId=String.format("M-%08d",lastDigits);
            return newMemberId;
        }
    }

    @Override
    public String getLastMemberId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Member_Id from member order by Member_Id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<MemberDTO> getAllMembers() throws SQLException, ClassNotFoundException {
        ObservableList<MemberDTO> ob= FXCollections.observableArrayList();

        ResultSet rs=CrudUtil.execute("SELECT * from member");

        while (true){
            try {
                if (!rs.next()) break;
            }catch (SQLException e){
                e.printStackTrace();
            }
            ob.add(new MemberDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)));
        }
        return ob;
    }
}
