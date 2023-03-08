package lk.ijse.library.dao.custom.impl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.BookDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class BookDAOImpl implements BookDAO {
    @Override
    public boolean add(BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            DBConnection.getInstance().getConnection().commit();
            return CrudUtil.execute("Insert into book values(?,?,?,?,?,?,?,?)", bookDTO.getId(), bookDTO.getName(), bookDTO.getCategory(), bookDTO.getIsbm(), bookDTO.getAuthor(), bookDTO.getPrice(), bookDTO.getShelf(), bookDTO.getOrdeid());
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public BookDTO search(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Select * from book where Book_Id=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            BookDTO bookDTO =new BookDTO(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5),rst.getDouble(6),rst.getString(7),rst.getString(8));
            return bookDTO;
        }
        return  null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From book where Book_Id='"+id+"'")>0;

    }

    @Override
    public boolean update(BookDTO bookDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update book set Book_Name=?,Book_Category=?,Book_ISBM=?,Book_Author=?,Book_Price=?,Book_Shelf=? where Book_Id=?",
                bookDTO.getName(), bookDTO.getCategory(), bookDTO.getIsbm(), bookDTO.getAuthor(), bookDTO.getPrice(), bookDTO.getShelf(), bookDTO.getId());


}

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastCourseId=getLastCourseId();
        if(lastCourseId==null){
            return "B-00000001";
        }else{
            String[] split=lastCourseId.split("[B][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newBookId=String.format("B-%08d",lastDigits);
            return newBookId;
        }
    }

    @Override
    public String getLastCourseId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Book_Id from book order by Book_Id DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String getBookCount() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT count(Book_Id) from book");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException {
        ObservableList<BookDTO> ob= FXCollections.observableArrayList();


        ResultSet  rs = CrudUtil.execute("SELECT * from book");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ob.add(new BookDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getDouble(6),
                    rs.getString(7),
                    rs.getString(8)));

        }
        return ob;
    }

    @Override
    public String getMemberCount() throws SQLException, ClassNotFoundException {
        ResultSet rs= CrudUtil.execute("SELECT count(Member_Id) from member");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String LateDate(String bookRecordId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT DATEDIFF(Now(),Return_Date) AS DateDiff from book_record where BookRecord_Id = ?",bookRecordId);
        if(rs.next()){
            return rs.getString(1);
        }
        return null;

    }

}
