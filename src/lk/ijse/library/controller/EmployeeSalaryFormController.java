package lk.ijse.library.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.library.dto.SalaryDTO;
import lk.ijse.library.service.custom.EmployeeSalaryService;
import lk.ijse.library.service.custom.impl.EmployeeSalaryServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmployeeSalaryFormController {
    public AnchorPane pane;
    public AnchorPane pane1;
    public JFXTextField txtAmount;
    public DatePicker dteDate;
    public JFXTextField txtEmployeeId;
    public TableColumn clmEmployeeId;
    public TableColumn clmAmount;
    public TableColumn clmDate;
    public TableView tblEmployeeSalary;
    private EmployeeSalaryService employeeSalaryService;

    public void initialize() throws SQLException, ClassNotFoundException {
        employeeSalaryService = (EmployeeSalaryService) new EmployeeSalaryServiceImpl();
        getAllValuesTabel();
        clearFields();
        setNewSalaryId();
    }

    private void setNewSalaryId() throws SQLException, ClassNotFoundException {
        txtEmployeeId.setText(employeeSalaryService.getNewModule());
    }

    private void clearFields() {
        txtAmount.clear();
        dteDate.setValue(null);
    }

    private void getAllValuesTabel() throws SQLException, ClassNotFoundException {
        clmEmployeeId.setCellValueFactory(new PropertyValueFactory<SalaryDTO, String>("employee_id"));
        clmAmount.setCellValueFactory(new PropertyValueFactory<SalaryDTO, String>("salary"));
        clmDate.setCellValueFactory(new PropertyValueFactory<SalaryDTO, String>("date"));

        ObservableList<SalaryDTO> salaries = employeeSalaryService.getAllSalaries();
        tblEmployeeSalary.setItems(salaries);
    }

    public void searchbtnOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        SalaryDTO salaryDTO1 = employeeSalaryService.searchEmployee(txtEmployeeId.getText());
        if(salaryDTO1 ==null){
            new Alert(Alert.AlertType.ERROR,"Employee Not Found").show();

        }else{
            txtAmount.setText(salaryDTO1.getName());
            txtAmount.setText(String.valueOf(salaryDTO1.getSalary()));
            dteDate.setValue(LocalDate.parse(salaryDTO1.getDate()));
        }
    }



    public void addbtnOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String employee_id=txtEmployeeId.getText();
        double salary= Double.parseDouble(txtAmount.getText());
        String date= String.valueOf(dteDate.getValue());

        SalaryDTO salaryDTO1 =new SalaryDTO(employee_id, salary, date);
        boolean isAdded;

        isAdded = employeeSalaryService.addSalary(salaryDTO1);

        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
            getAllValuesTabel();
            clearFields();
            setNewSalaryId();

        }


    }


    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDeleted = employeeSalaryService.deleteEmployeeSalary(txtEmployeeId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                getAllValuesTabel();
                clearFields();
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
            }
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }

    public void txtOnActionAmount(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("[0-9][1-9.]*[0-9]+[1-9]*");
        Matcher m1=p1.matcher(txtAmount.getText());
        boolean b=m1.find();
        if (b){
            txtAmount.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtAmount.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }
}
