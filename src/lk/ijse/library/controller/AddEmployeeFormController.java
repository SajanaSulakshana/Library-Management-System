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
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.EmployeeDTO;
import lk.ijse.library.service.custom.EmployeeService;
import lk.ijse.library.service.custom.impl.EmployeeServiceImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddEmployeeFormController{

    public AnchorPane pane;
    public JFXTextField txtEmployeeId;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtEmployeeName;
    public JFXTextField txtEmployeeNicno;
    public JFXTextField txtSalary;
    public JFXDatePicker dateEmployeeDate;
    public TableView tblEmployeeManage;
    public TableColumn clmEmployeeId;
    public TableColumn clmEmployeeNic;
    public TableColumn clmAddress;
    public TableColumn clmContact;
    public TableColumn clmSalary;
    public TableColumn clmDate;
    public TableColumn clmName;
    private EmployeeService employeeService;


    public void initialize() throws SQLException, ClassNotFoundException {
        employeeService = (EmployeeService) new EmployeeServiceImpl();
        setNewEmployeeId();
        setTableEmployee();
    }

    private void setNewEmployeeId() throws SQLException, ClassNotFoundException {
        txtEmployeeId.setText(employeeService.getNewModule());
    }

    private void setTableEmployee() {
        clmEmployeeId.setCellValueFactory(new PropertyValueFactory<EmployeeDTO, String>("id"));
        clmEmployeeNic.setCellValueFactory(new PropertyValueFactory<EmployeeDTO, String>("nic"));
        clmName.setCellValueFactory(new PropertyValueFactory<EmployeeDTO, String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("contact"));
        clmSalary.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("salary"));
        clmDate.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("date"));

        try {
            ObservableList<EmployeeDTO> employeeDTOS = employeeService.getAllEmployee();
            tblEmployeeManage.setItems(employeeDTOS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void searchbtnOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        EmployeeDTO employeeDTO = employeeService.searchEmployee(txtEmployeeId.getText());
        if (employeeDTO == null) {
            new Alert(Alert.AlertType.ERROR, "Employee Not Found").show();

        } else {
            txtEmployeeNicno.setText(employeeDTO.getName());
            txtEmployeeName.setText(employeeDTO.getName());
            txtAddress.setText(employeeDTO.getAddress());
            txtContact.setText(String.valueOf(employeeDTO.getContact()));
            txtSalary.setText(employeeDTO.getSalary() + "");
            dateEmployeeDate.setValue(LocalDate.parse(employeeDTO.getDate()));

        }
    }

    public void SavebtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtEmployeeId.getText();
        String nic = txtEmployeeNicno.getText();
        String name = txtEmployeeName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        double salary = Double.parseDouble(txtSalary.getText());
        String date = String.valueOf(dateEmployeeDate.getValue());

        EmployeeDTO employeeDTO = new EmployeeDTO(id, nic, name, address, contact, salary, date);
        boolean isAdded = false;

        try {
            isAdded = employeeService.addEmployee(employeeDTO);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Entry").show();
        } catch (ClassNotFoundException e) {
           // new Alert(Alert.AlertType.ERROR, "Added Fail").show();
        }

        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, "Added Success").show();
            setTableEmployee();
            clearFields();
            setNewEmployeeId();
        }
    }



    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtEmployeeId.getText();
        String nic=txtEmployeeNicno.getText();
        String name=txtEmployeeName.getText();
        String address=txtAddress.getText();
        int contact= Integer.parseInt(txtContact.getText());
        double salary=Double.parseDouble(txtSalary.getText());
        String date= String.valueOf(dateEmployeeDate.getValue());

        EmployeeDTO employeeDTO =new EmployeeDTO(id,nic,name,address,contact,salary,date);
        boolean isUpdated= employeeService.updateEmployee(employeeDTO);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableEmployee();

        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        boolean isDeleted = employeeService.deleteEmployee(txtEmployeeId.getText());
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
            setTableEmployee();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
        }
    }
    public void clearFields(){
        txtEmployeeId.clear();
        txtEmployeeNicno.clear();
        txtAddress.clear();
        txtContact.clear();
        txtSalary.clear();
        txtEmployeeName.clear();

    }

    public void txtEmployeeIdOnAction(ActionEvent actionEvent) {
        txtEmployeeNicno.requestFocus();
    }

    public void txtContactOnAction(ActionEvent actionEvent) {
        txtSalary.requestFocus();
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        txtContact.requestFocus();
    }

    public void txtEmployeeNicNoOnAction(ActionEvent actionEvent) {
        txtEmployeeName.requestFocus();
    }

    public void txtEmployeeNameOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void txtSalaryOnAction(ActionEvent actionEvent) {
        dateEmployeeDate.requestFocus();
    }

    public void printOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        printIncomeReport();
    }

    public void printIncomeReport() throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasdi = JRXmlLoader.load("E:\\Important Project\\Final Project In Library Management System\\src\\lk\\ijse\\library\\report\\EmployeeReport.jrxml");
        String sql = "SELECT * from employee where Employee_Id = '" + txtEmployeeId.getText() + "'";
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);
        jasdi.setQuery(newQuery);

        JasperReport js = JasperCompileManager.compileReport(jasdi);
        JasperPrint jp = JasperFillManager.fillReport(js, null, DBConnection.getInstance().getConnection());



        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();
    }

    public void txtOnKeyContact(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        Matcher m1=p1.matcher(txtContact.getText());
        boolean b=m1.find();
        if (b){
            txtContact.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtContact.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

    public void txtSalaryOnKey(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("[0-9][1-9.]*[0-9]+[1-9]*");
        Matcher m1=p1.matcher(txtSalary.getText());
        boolean b=m1.find();
        if (b){
            txtSalary.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtSalary.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

    public void txtNicOnRealsed(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("^[0-9]{9}[vVxX]$");
        Matcher m1=p1.matcher(txtEmployeeNicno.getText());
        boolean b=m1.find();
        if (b){
            txtEmployeeNicno.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtEmployeeNicno.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

    public void txtEmployeeNameKetOnAction(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$");
        Matcher m1=p1.matcher(txtEmployeeName.getText());
        boolean b=m1.find();
        if (b){
            txtEmployeeName.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtEmployeeName.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

    public void txtAddressOn(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("^[A-z0-9/, ]{6,30}$");
        Matcher m1=p1.matcher(txtAddress.getText());
        boolean b=m1.find();
        if (b){
            txtAddress.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtAddress.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

}
