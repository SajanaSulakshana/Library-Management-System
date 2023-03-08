package lk.ijse.library.controller;


import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.DonationDTO;
import lk.ijse.library.service.custom.DonationService;
import lk.ijse.library.service.custom.impl.DonationServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DonationFormController {
    public AnchorPane pane;
    public AnchorPane pane1;
    public JFXTextField txtDonationId;
    public JFXTextField txtDonationValue;
    public JFXTextField txtDonationName;
    public JFXDatePicker txtDonationDate;
    public TableView tblDonation;
    public TableColumn clmDonationId;
    public TableColumn clmDonationName;
    public TableColumn clmDonationValue;
    public TableColumn clmDonationDate;
    private DonationService donationService;

    public void initialize(){
        donationService = new DonationServiceImpl();
        setNewDonationId();
        setTableDonation();
    }

    private void setNewDonationId() {
        try {
            txtDonationId.setText(donationService.getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTableDonation() {
        clmDonationId.setCellValueFactory(new PropertyValueFactory<DonationDTO,String>("id"));
        clmDonationName.setCellValueFactory(new PropertyValueFactory<DonationDTO,String>("name"));
        clmDonationValue.setCellValueFactory(new PropertyValueFactory<DonationDTO,String>("value"));
        clmDonationDate.setCellValueFactory(new PropertyValueFactory<DonationDTO,String>("date"));
        try{
            ObservableList<BookDTO> donations= donationService.getAllDonation();
            tblDonation.setItems(donations);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void searchbtnOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        DonationDTO donationDTO = donationService.searchDonation(txtDonationId.getText());
        if(donationDTO ==null){
            new Alert(Alert.AlertType.ERROR,"Donation Not Found").show();
        }else{
            txtDonationName.setText(donationDTO.getName());
            txtDonationValue.setText(donationDTO.getValue());
            txtDonationDate.setValue(LocalDate.parse(donationDTO.getDate()));
        }
    }

    public void addbtnOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String id = txtDonationId.getText();
        String name = txtDonationName.getText();
        String value = txtDonationValue.getText();
        String date = String.valueOf(txtDonationDate.getValue());

        DonationDTO donationDTO = new DonationDTO(id, name, value, date);
        boolean isAdded;

        isAdded = donationService.addDonation(donationDTO);
        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, "Added Success").show();
            setNewDonationId();
            setTableDonation();
            clearFields();
        }
    }

    private void clearFields() {
        txtDonationId.clear();
        txtDonationName.clear();
        txtDonationValue.clear();
        //txtDonationDate.clear();
    }


    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtDonationId.getText();
        String name = txtDonationName.getText();
        String value = txtDonationValue.getText();
        String date = String.valueOf(txtDonationDate.getValue());

        DonationDTO donationDTO =new DonationDTO(id,name,value,date);
        boolean isUpdated= donationService.updateDonation(donationDTO);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isDeleted = donationService.deleteDonation(txtDonationId.getText());
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
        }
    }

    public void txtValueOnAction(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("[0-9][1-9.]*[0-9]+[1-9]*");
        Matcher m1=p1.matcher(txtDonationValue.getText());
        boolean b=m1.find();
        if (b){
            txtDonationValue.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtDonationValue.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }
}
