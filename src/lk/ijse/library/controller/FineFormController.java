package lk.ijse.library.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.library.dto.FineDTO;
import lk.ijse.library.service.custom.FineService;
import lk.ijse.library.service.custom.impl.FineServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class FineFormController {

    public JFXTextField txtFine;
    public Label lblTotal;
    public Label lblDateCount;
    public AnchorPane dashboardContext;
    private String recordId;
    private FineService fineService;

    public void initialize(){
        fineService=new FineServiceImpl();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        FineDTO fineDTO = new FineDTO(recordId,Integer.parseInt(lblDateCount.getText()),Double.parseDouble(lblTotal.getText()),
                String.valueOf(LocalDate.now()));
        try {
            boolean flag = fineService.addFine(fineDTO);
            if(flag){
                new Alert(Alert.AlertType.INFORMATION,"Fine Added Success").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fine Adding Fail").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Fine Adding Fail - Database Error").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Fine Adding Fail - Driver Error").show();
        }
    }

    public void setLblFine(int count){
        lblDateCount.setText(String.valueOf(count));
    }

    public void btnCalculateOnAction(ActionEvent actionEvent) {
        try{
            double total = Double.parseDouble(txtFine.getText()) * Integer.parseInt(lblDateCount.getText());
            lblTotal.setText(String.valueOf(total));
        }catch(NumberFormatException ex){
            new Alert(Alert.AlertType.ERROR,"Invalid Amount").show();
        }
    }

    public void setRecordId(String id){
        recordId=id;
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        setUi("IssueBookForm");

    }
    private void setUi(String ui) throws IOException {
        Stage stage=(Stage) dashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+ui+".fxml"))));
        stage.centerOnScreen();

    }

    
}
