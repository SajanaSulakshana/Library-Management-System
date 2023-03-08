package lk.ijse.library.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.library.dto.OrderDTO;
import lk.ijse.library.service.custom.OrderService;
import lk.ijse.library.service.custom.impl.OrderServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static java.lang.Double.parseDouble;

public class OrderFormController {



    public AnchorPane pane;
    public JFXTextField txtOrderId;
    public JFXTextField txtOrderPrice;
    public DatePicker dteDate;
    public ComboBox cmbOrderType;
    public TableView tblOrder;
    public TableColumn clmOrderId;
    public TableColumn clmDate;
    public TableColumn clmOrderType;
    public TableColumn clmOrderPrice;
    private OrderService orderService;

    public void initialize() throws SQLException, ClassNotFoundException {
        orderService = (OrderService) new OrderServiceImpl();
        setData();
        setTableOders();
        setNewOrderId();
    }


    public void searchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        OrderDTO orderDTO = orderService.searchOrder(txtOrderId.getText());
        if(orderDTO ==null){
            new Alert(Alert.AlertType.ERROR,"Order Not Found").show();

        }else{
            cmbOrderType.setValue(orderDTO.getType());
            txtOrderPrice.setText(orderDTO.getPrice()+"");
            dteDate.setValue(LocalDate.parse(orderDTO.getDate()));

        }

    }



    public void saveMemberOnAction(ActionEvent actionEvent)  {
        String id=txtOrderId.getText();
        String type= String.valueOf(cmbOrderType.getValue());
       double price= parseDouble(txtOrderPrice.getText());
       String date= String.valueOf(dteDate.getValue());

       OrderDTO orderDTO =new OrderDTO(id,type,price,date);
       boolean isAdded = false;

        try {
            isAdded= orderService.addOrder(orderDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
            try {
                setTableOders();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            clearFields();
            try {
                setNewOrderId();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Already Order Added").show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtOrderId.getText();
        String type= String.valueOf(cmbOrderType.getValue());
        double price= Double.parseDouble(txtOrderPrice.getText());
        String date= String.valueOf(dteDate.getValue());

        OrderDTO orderDTO =new OrderDTO(id,type,price,date);
        boolean isUpdated= orderService.updateOrder(orderDTO);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableOders();
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Update Fail").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        boolean isDeleted = orderService.deleteOrder(txtOrderId.getText());
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
            setTableOders();
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
        }
    }



    private void clearFields() {
        txtOrderPrice.clear();
        cmbOrderType.setValue(null);
        dteDate.setValue(null);

    }

    private void setNewOrderId() throws SQLException, ClassNotFoundException {
        txtOrderId.setText(orderService.getNewOrder());
    }

    private void setTableOders() throws SQLException, ClassNotFoundException {
        clmOrderId.setCellValueFactory(new PropertyValueFactory<OrderDTO,String>("id"));
        clmOrderType.setCellValueFactory(new PropertyValueFactory<OrderDTO,String>("type"));
        clmOrderPrice.setCellValueFactory(new PropertyValueFactory<OrderDTO,String>("price"));
        clmDate.setCellValueFactory(new PropertyValueFactory<OrderDTO,String>("date"));
        ObservableList<OrderDTO> orderDTOS = orderService.getAllOrders();
        tblOrder.setItems(orderDTOS);

    }

    private void setData(){
        cmbOrderType.getItems().add("Order");
        cmbOrderType.getItems().add("Donation");
    }

    public void txtOrderPriceOnAction(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("[0-9][1-9.]*[0-9]+[1-9]*");
        Matcher m1=p1.matcher(txtOrderPrice.getText());
        boolean b=m1.find();
        if (b){
            txtOrderPrice.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtOrderPrice.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

    public void cmbOrderTypeOnAction(ActionEvent actionEvent) {
        if (cmbOrderType.getSelectionModel().getSelectedIndex() == 0) {
            txtOrderPrice.setDisable(false);
        } else {
            txtOrderPrice.setDisable(true);
            txtOrderPrice.setText("0");
        }

    }
}
