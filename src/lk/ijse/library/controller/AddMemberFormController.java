package lk.ijse.library.controller;


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
import lk.ijse.library.dto.MemberDTO;
import lk.ijse.library.service.custom.MemberService;
import lk.ijse.library.service.custom.impl.MemberServiceImpl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddMemberFormController {


    public AnchorPane pane;
    public JFXTextField txtMemberId;
    public JFXTextField txtMemberContact;
    public JFXTextField txtMemberAddress;
    public JFXTextField txtMemberName;
    public TableView tblAddMember;
    public TableColumn clmMemberId;
    public TableColumn clmMemberName;
    public TableColumn clmAddress;
    public TableColumn clmContact;
    private MemberService memberService;

    public void initialize() throws SQLException, ClassNotFoundException {
        memberService = new MemberServiceImpl();
        setNewMemberId();
        setTableUsers();

    }
    private void setNewMemberId() throws SQLException, ClassNotFoundException {
        try {
            txtMemberId.setText(memberService.getNewMember());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            MemberDTO memberDTO = memberService.searchMember(txtMemberId.getText());
            if (memberDTO == null) {
                new Alert(Alert.AlertType.ERROR, "Member Not Found").show();
            } else {
                txtMemberName.setText(memberDTO.getName());
                txtMemberAddress.setText(memberDTO.getAddress());
                txtMemberContact.setText(memberDTO.getContact() + "");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddMemberFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddMemberFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMemberOnAction(ActionEvent actionEvent)  {
        String id = txtMemberId.getText();
        String name = txtMemberName.getText();
        String address = txtMemberAddress.getText();
        int contact = Integer.parseInt(txtMemberContact.getText());

        MemberDTO memberDTO = new MemberDTO(id, name, address, contact);
        boolean isAdded;

        try {
            isAdded = memberService.addMember(memberDTO);
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Added Success").show();
                setTableUsers();
                setNewMemberId();
                clearFields();
            }else{

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Already Member Added").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    private void clearFields() {
        txtMemberAddress.clear();
        txtMemberContact.clear();
        txtMemberName.clear();
    }


    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtMemberId.getText();
        String name = txtMemberName.getText();
        String address = txtMemberAddress.getText();
        int contact = Integer.parseInt(txtMemberContact.getText());

        MemberDTO memberDTO = new MemberDTO(id, name, address, contact);
        boolean isAdded = memberService.updateMember(memberDTO);
        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, "Update Success").show();
            setTableUsers();
        }else{
            new Alert(Alert.AlertType.ERROR, "Update Fail").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        boolean isDeleted = false;
        try {
            isDeleted = memberService.deleteMember(txtMemberId.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
            try {
                setTableUsers();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Delete Fail").show();
        }
    }

    public void setTableUsers() throws ClassNotFoundException {
        clmMemberId.setCellValueFactory(new PropertyValueFactory<MemberDTO,String>("id"));
        clmMemberName.setCellValueFactory(new PropertyValueFactory<MemberDTO,String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<MemberDTO,String>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<MemberDTO,String>("contact"));
        try{
            ObservableList<MemberDTO> memberDTOS = memberService.getAllMembers();
            tblAddMember.setItems(memberDTOS);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void txtMemberIdOnAction(ActionEvent actionEvent) {
        txtMemberName.requestFocus();
    }

    public void txtContactOnAction(ActionEvent actionEvent) {
        Pattern p1=Pattern.compile("^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$");
        Matcher m1=p1.matcher(txtMemberName.getText());
        boolean b=m1.find();
        if (b){
            txtMemberName.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
           txtMemberName.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        txtMemberContact.requestFocus();
    }

    public void txtMemberNameOnAction(ActionEvent actionEvent) {
        txtMemberAddress.requestFocus();

    }

    public void txtOnKeyRealsed(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        Matcher m1=p1.matcher(txtMemberContact.getText());
        boolean b=m1.find();
        if (b){
            txtMemberContact.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtMemberContact.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

    public void txtAddressAction(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("^[A-z0-9/, ]{6,30}$");
        Matcher m1=p1.matcher(txtMemberAddress.getText());
        boolean b=m1.find();
        if (b){
            txtMemberAddress.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtMemberAddress.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

    public void txtMemberName(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$");
        Matcher m1=p1.matcher(txtMemberName.getText());
        boolean b=m1.find();
        if (b){
            txtMemberName.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtMemberName.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }
}

