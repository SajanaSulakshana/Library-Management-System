package lk.ijse.library.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.library.dao.CrudUtil;
import lk.ijse.library.dao.custom.DonationDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.DonationDTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonationDAOImpl implements DonationDAO {
    @Override
    public boolean add(DonationDTO donationDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            DBConnection.getInstance().getConnection().commit();
            return CrudUtil.execute("Insert into donetion values(?,?,?,?)", donationDTO.getId(), donationDTO.getName(), donationDTO.getValue(), donationDTO.getDate());
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public DonationDTO search(String id) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement stm=connection.prepareStatement("Select * from donetion where Donetion_Id=?");
        stm.setObject(1,id);
        ResultSet rst=stm.executeQuery();
        if(rst.next()){
            DonationDTO donationDTO =new DonationDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
            return donationDTO;
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From donetion where Donetion_Id='"+id+"'")>0;


    }

   /* @Override
    public boolean delete(DonationDTO id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From donetion where Donetion_Id='"+id+"'")>0;

    }*/

    @Override
    public boolean update(DonationDTO donationDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update donetion set Doneter_Name=?,Donetion_Value=?,Donetion_Date=? where Donetion_Id=?", donationDTO.getName(), donationDTO.getValue(), donationDTO.getDate(), donationDTO.getId());

    }

    @Override
    public String getNewModule() throws SQLException, ClassNotFoundException {
        String lastDonationId=getLastDonationId();
        if(lastDonationId==null){
            return "D-00000001";
        }else{
            String[] split=lastDonationId.split("[D][-]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newBookId=String.format("D-%08d",lastDigits);
            return newBookId;
        }
    }

    @Override
    public String getLastDonationId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Donetion_Id from donetion order by Donetion_Id DESC limit 1");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<BookDTO> getAllDonation() throws SQLException, ClassNotFoundException {
        ObservableList<BookDTO> ob= FXCollections.observableArrayList();


        ResultSet  rs = CrudUtil.execute("SELECT * from donetion");

        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ob.add(new DonationDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)));

        }
        return ob;
    }
}
