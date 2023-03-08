package lk.ijse.library.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.library.service.ServiceFactory;
import lk.ijse.library.service.ServiceType;
import lk.ijse.library.service.custom.BookService;
import lk.ijse.library.service.custom.EmployeeService;
import lk.ijse.library.service.custom.IssueBookService;
import lk.ijse.library.service.custom.impl.BookServiceImpl;
import lk.ijse.library.service.custom.impl.EmployeeServiceImpl;
import lk.ijse.library.service.custom.impl.IssueBookServiceImpl;
import lk.ijse.library.util.Navigation;
import lk.ijse.library.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBoardFormController {


    public AnchorPane pane;
    public Label txtTime;
    public AnchorPane dashboardContext;
    public Label lblBookCount;
    public Label lblNoOfMember;
    public Label lblIssueBook;
    public Label lblEmployeeCount;
    public Label lblIssue;
    public Label lblReturn;
    public Label lblFine;
    private BookService bookService;
    private EmployeeService employeeService;
    private IssueBookService issueBookService;




    public void bookOnAction(ActionEvent actionEvent) throws IOException {
       ServiceFactory.getInstance(ServiceType.Book, pane);
        Navigation.navigate(Routes.Book, pane);
    }

    public void donationOnAction(ActionEvent actionEvent) throws IOException {
        ServiceFactory.getInstance(ServiceType.Donation, pane);
        Navigation.navigate(Routes.Donation, pane);
    }

    public void salaryOnAction(ActionEvent actionEvent) throws IOException {
        ServiceFactory.getInstance(ServiceType.Salary, pane);
        Navigation.navigate(Routes.Salary, pane);
    }

    public void expentureOnAction(ActionEvent actionEvent) throws IOException {
        ServiceFactory.getInstance(ServiceType.Expenture, pane);
        Navigation.navigate(Routes.Expenture, pane);
    }

    public void incomeOnAction(ActionEvent actionEvent) throws IOException {
        ServiceFactory.getInstance(ServiceType.Income, pane);
        Navigation.navigate(Routes.Income, pane);
    }



    public void employeeOnAction(ActionEvent actionEvent) throws IOException {
        ServiceFactory.getInstance(ServiceType.Employee, pane);
        Navigation.navigate(Routes.Employee, pane);
    }

    public void memberOnAction(ActionEvent actionEvent) throws IOException {
        ServiceFactory.getInstance(ServiceType.Member, pane);
        Navigation.navigate(Routes.Member, pane);

    }

    public void orderOnAction(ActionEvent actionEvent) throws IOException {
        ServiceFactory.getInstance(ServiceType.Order, pane);
        Navigation.navigate(Routes.Order, pane);
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        bookService = new BookServiceImpl();
        employeeService = new EmployeeServiceImpl();
        issueBookService = new IssueBookServiceImpl();
        setDateAndTime();
        visibleMemberCount();
        visibleIssueBookCount();
        visibleEmployeeCount();
        visiblePendingBooks();
        visibleFinePrice();
        try {
            lblBookCount.setText(bookService.getBookCount());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void visibleFinePrice() throws SQLException, ClassNotFoundException {
        //lblFine.setText(FineModel.getFinePrice());
    }

    private void visiblePendingBooks() throws SQLException, ClassNotFoundException {
        lblReturn.setText(issueBookService.getPendingReturnBooks());
    }

    private void visibleEmployeeCount() {
        try {
            lblEmployeeCount.setText(employeeService.getEmployeeCount());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void visibleIssueBookCount() throws SQLException, ClassNotFoundException {
        lblIssueBook.setText(issueBookService.getIssueBookCount());
    }

    private void visibleMemberCount() throws SQLException, ClassNotFoundException {
        lblNoOfMember.setText(bookService.getMemberCount());

    }
    private void setDateAndTime() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"+" --- "+"HH:mm:ss");
                    txtTime.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    private void setUi(String ui) throws IOException {
        Stage stage=(Stage) dashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+ui+".fxml"))));
        stage.centerOnScreen();

    }


    public void closeOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void dashBoardonaction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }

    public void issuebookOnAction(ActionEvent actionEvent) throws IOException {
        ServiceFactory.getInstance(ServiceType.Issue, pane);
        Navigation.navigate(Routes.Issue, pane);
    }
}

