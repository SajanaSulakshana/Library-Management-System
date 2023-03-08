package lk.ijse.library.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.ExpentureDTO;
import lk.ijse.library.service.custom.ExpentureService;
import lk.ijse.library.service.custom.impl.ExpentureServiceImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;

public class ExpentureFormController {

    public AnchorPane pane;
    public DatePicker dateDate;
    public TableView tblExpenture;
    public TableColumn clmExpentureNumber;
    public TableColumn clmDescription;
    public TableColumn clmDate;
    public TableColumn clmPayment;
    public JFXTextField txtExpentureNumber;
    public JFXTextField txtPayment;
    public JFXTextField txtDescription;
    private ExpentureService expentureService;


    public void initialize() throws SQLException, ClassNotFoundException {
        expentureService =new ExpentureServiceImpl();
        setNewExpentureId();
        setTableExpenture();

    }

    private void setTableExpenture() {
        clmExpentureNumber.setCellValueFactory(new PropertyValueFactory<ExpentureDTO, String>("invoiceNumber"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("description"));
        clmDate.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("date"));
        clmPayment.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("payment"));

        try{
            ObservableList<ExpentureDTO> expentureDTOS = (ObservableList<ExpentureDTO>) expentureService.getAllExpenture();
            tblExpenture.setItems(expentureDTOS);
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private void setNewExpentureId() throws SQLException, ClassNotFoundException {
        txtExpentureNumber.setText(expentureService.getNewModule());
    }

    public void txtExpentureNumberOnAction(ActionEvent actionEvent) {
        txtDescription.requestFocus();
    }

    public void txtPaymentOnAction(ActionEvent actionEvent) {

    }

    public void txtDescriptionOnAction(ActionEvent actionEvent) {
        dateDate.requestFocus();
    }

    public void addExpentureOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String invoiceNumber = txtExpentureNumber.getText();
        String description = txtDescription.getText();
        String date = String.valueOf(dateDate.getValue());
        double payment = Double.parseDouble(txtPayment.getText());

        ExpentureDTO expentureDTO = new ExpentureDTO(invoiceNumber, description, date, payment);
        boolean isAdded;

        isAdded = expentureService.addExpenture(expentureDTO);
        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, "Added Success").show();
            setTableExpenture();
            setNewExpentureId();

        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String invoiceNumber = txtExpentureNumber.getText();
        String description = txtDescription.getText();
        String date = String.valueOf(dateDate.getValue());
        double payment = Double.parseDouble(txtPayment.getText());

        ExpentureDTO expentureDTO =new ExpentureDTO(invoiceNumber,description,date,payment);
        boolean isUpdated= expentureService.updateExpenture(expentureDTO);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableExpenture();

        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        boolean isDeleted = expentureService.deleteExpenture(txtExpentureNumber.getText());
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
            setTableExpenture();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
        }
    }
    public void searchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        ExpentureDTO expentureDTO = expentureService.searchExpenture(txtExpentureNumber.getText());
        if(expentureDTO ==null){
            new Alert(Alert.AlertType.ERROR,"Bill Not Found").show();

        }else{
            txtDescription.setText(expentureDTO.getDescription());
            dateDate.setValue(LocalDate.parse(expentureDTO.getDate()));
           txtPayment.setText(String.valueOf(expentureDTO.getPayment()));

        }

    }

    public void printReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        printExpentureReports();
    }

    private void printExpentureReports() throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasdi = JRXmlLoader.load("E:\\Important Project\\Final Project In Library Management System\\src\\lk\\ijse\\library\\report\\ExpentureReport.jrxml");
        String sql = "SELECT * from expenture where Invoice_Number = '" + txtExpentureNumber.getText() + "'";
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);
        jasdi.setQuery(newQuery);

        JasperReport js = JasperCompileManager.compileReport(jasdi);
        JasperPrint jp = JasperFillManager.fillReport(js, null, DBConnection.getInstance().getConnection());

        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();
    }
}
