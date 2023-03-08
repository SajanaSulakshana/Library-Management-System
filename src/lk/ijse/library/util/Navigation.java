package lk.ijse.library.util;



import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.library.util.Routes;

import java.io.IOException;

public class Navigation {
   private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case Book:
                window.setTitle("Book Manage");
                initUI("AddBookForm.fxml");
                break;
            case Employee:
                window.setTitle("Employee Manage");
                initUI("AddEmployeeForm.fxml");
                break;
            case Member:
                window.setTitle("Member Manage");
                initUI("AddMemberForm.fxml");
                break;
            case DashBoard:
                window.setTitle("Dash Board");
                initUI("DashBoardForm.fxml");
                break;
            case Donation:
                window.setTitle("Donation");
                initUI("DonationForm.fxml");
                break;
            case Expenture:
                window.setTitle("Expenture");
                initUI("ExpentureForm.fxml");
                break;

            case Order:
                window.setTitle("Order");
                initUI("OrderForm.fxml");
                break;
            case Salary:
                window.setTitle("Employee Salary");
                initUI("EmployeeSalaryForm.fxml");
                break;
            case Issue:
                window.setTitle("Issue Book");
                initUI("IssueBookForm.fxml");
                break;
            case Income:
                window.setTitle("Income");
                initUI("IncomeForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/library/view/" + location)));
    }
}

