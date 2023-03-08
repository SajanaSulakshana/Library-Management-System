package lk.ijse.library.controller;
import javafx.scene.paint.Paint;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFormController {

    public JFXTextField userNametxt;
    public JFXPasswordField passwordTxt;
    public Label wrongLbl;
    public JFXButton logBtn;
    public AnchorPane pane;

    public void logOnAction(ActionEvent actionEvent) throws IOException {
         if (actionEvent.getSource()==logBtn) {
                String username = userNametxt.getText();
                String password = passwordTxt.getText();
                if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                    System.out.println("login success");
                    Stage window = (Stage)pane.getScene().getWindow();
                    window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));


                } else if
                (userNametxt.getText().isEmpty() && passwordTxt.getText().isEmpty()) {
                    wrongLbl.setText("Please enter your data.");


                } else {
                    wrongLbl.setText("Wrong username or password!");
                }

            }
    }
    public void txtUserNameAction(javafx.scene.input.KeyEvent keyEvent) {
       /* Pattern p1=Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}");
        Matcher m1=p1.matcher(userNametxt.getText());
        boolean b=m1.find();
        if (b){
            userNametxt.setUnFocusColor(Paint.valueOf("#2ecc71"));
        }else{
            userNametxt.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }*/
    }
}
