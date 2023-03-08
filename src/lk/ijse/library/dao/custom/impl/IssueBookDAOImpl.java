package lk.ijse.library.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.IssueBookDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.IssueDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueBookDAOImpl implements IssueBookDAO {
    @Override
    public boolean add(IssueDTO issuebook) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        return CrudUtil.execute("Insert into book_record values(?,?,?,?,?,?)",issuebook.getRecod_id(),issuebook.getMember_id(),issuebook.getBook_id(),issuebook.getIssue(),issuebook.getReturn1(),issuebook.getStutes());


    }

    @Override
    public IssueDTO search(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Select * from book_record where BookRecord_Id=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            IssueDTO issueDTO =new IssueDTO(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5),rst.getString(6));
            return issueDTO;
        }
        return  null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From book_record where BookRecord_Id='"+id+"'")>0;

    }

   /* @Override
    public boolean delete(IssueDTO id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From book_record where BookRecord_Id='"+id+"'")>0;

    }*/

    @Override
    public boolean update(IssueDTO issueDTO1) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update book_record set Member_Id=?,Book_Id=?,Issue_Date=?,Return_Date=?,Stutes=? where BookRecord_Id=?",
                issueDTO1.getMember_id(), issueDTO1.getBook_id(), issueDTO1.getIssue(), issueDTO1.getReturn1(), issueDTO1.getStutes(), issueDTO1.getRecod_id());

    }

    @Override
    public ObservableList<IssueDTO> getAllRecords() throws SQLException, ClassNotFoundException {
        ObservableList<IssueDTO> ob= FXCollections.observableArrayList();


        ResultSet rs = CrudUtil.execute("SELECT * from book_record");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ob.add(new IssueDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)));

        }
        return ob;
    }

    @Override
    public String getIssueBookCount() throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT count(BookRecord_Id) from book_record");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String getPendingReturnBooks() throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT count(Stutes) from book_record where Stutes = 'Issued'");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String getNewIssueBook() throws SQLException, ClassNotFoundException {
        String lastIssueBookId=getLastIssueBookId();
        if(lastIssueBookId==null){
            return "I-00000001";
        }else {
            String[] split=lastIssueBookId.split("[I][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newMemberId=String.format("I-%08d",lastDigits);
            return newMemberId;
        }
    }

    @Override
    public String getLastIssueBookId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT BookRecord_Id from book_record order by BookRecord_Id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public boolean isAlreadyIssued(String bId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * from book_record where book_id = ? and Stutes = 'Issued'", bId);
        if(rs.next()){
            return true;
        }
        return false;
    }
}
