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
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.service.custom.BookService;
import lk.ijse.library.service.custom.impl.BookServiceImpl;

import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddBookFormController extends Component {
   
    public JFXTextField txtBookId;
    public JFXTextField txtName;
    public JFXTextField txtPrice;
    public JFXTextField txtCategory;

    public AnchorPane pane;


    public TableColumn clmBookId;
    public TableColumn clmBookName;
    public TableColumn clmCategory;
    public TableColumn clmPrice;
    public JFXTextField txtIsbm;
    public JFXTextField txtAuthor;
    public JFXTextField txtShelf;
    public JFXTextField txtOrderId;
    public TableView tblAddBook;
    public TableColumn clmIsbm;
    public TableColumn clmAuthor;
    public TableColumn clmShelf;
    public TableColumn clmOrderId;
    private BookService bookService;

    public void initialize() throws SQLException, ClassNotFoundException {
        bookService = new BookServiceImpl();
        txtBookId.setText(bookService.getNewModule());

        setNewBookId();
        setTableUsers();
    }
    public void setNewBookId(){
        try {
            txtBookId.setText(bookService.getNewModule());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void searchbtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            BookDTO bookDTO = bookService.searchBook(txtBookId.getText());
            if(bookDTO ==null){
                new Alert(Alert.AlertType.ERROR,"Book Not Found").show();

            }else{
                txtName.setText(bookDTO.getName());
                txtCategory.setText(bookDTO.getCategory());
                txtIsbm.setText(bookDTO.getIsbm());
                txtAuthor.setText(bookDTO.getAuthor());
                txtPrice.setText(bookDTO.getPrice()+"");
                txtShelf.setText(bookDTO.getShelf());
                txtOrderId.setText(bookDTO.getOrdeid());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddBookFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddBookFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtBookId.getText();
        String name=txtName.getText();
        String category=txtCategory.getText();
        String isbm=txtIsbm.getText();
        String author=txtAuthor.getText();
        double price=Double.parseDouble(txtPrice.getText());
        String shelf=txtShelf.getText();
        String orderid=txtOrderId.getText();

        BookDTO bookDTO =new BookDTO(id,name,category,isbm,author,price,shelf,orderid);
        boolean isUpdated= bookService.updateBook(bookDTO);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableUsers();

        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = bookService.deleteBook(txtBookId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                setTableUsers();
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
            }
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }

    public void addBtnOnAction(ActionEvent actionEvent) {

        String id=txtBookId.getText();
        String name=txtName.getText();
        String category=txtCategory.getText();
        String isbm=txtIsbm.getText();
        String author=txtAuthor.getText();
        double price=Double.parseDouble(txtPrice.getText());
        String shelf=txtShelf.getText();
        String orderid=txtOrderId.getText();
        BookDTO bookDTO =new BookDTO(id, name, category,isbm,author, price,shelf,orderid);
        boolean isAdded;

        try {
            isAdded = bookService.addBook(bookDTO);

            if(isAdded){
                new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
                setNewBookId();
                setTableUsers();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"Adding Failed").show();
            }
        } catch (ClassNotFoundException ex) {
            new Alert(Alert.AlertType.ERROR,ex.getMessage()).show();

        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR,ex.getMessage()).show();
        }
    }

    private void clearFields() {
        txtName.clear();
        txtCategory.clear();
        txtIsbm.clear();
        txtAuthor.clear();
        txtPrice.clear();
        txtShelf.clear();
        txtOrderId.clear();
    }

    public void setTableUsers() throws SQLException, ClassNotFoundException {
        clmBookId.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("id"));
        clmBookName.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("name"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("category"));
        clmIsbm.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("isbm"));
        clmAuthor.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("author"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("price"));
        clmShelf.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("shelf"));
        clmOrderId.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("ordeid"));
       try{
           ObservableList<BookDTO> bookDTOS = bookService.getAllBooks();
           tblAddBook.setItems(bookDTOS);
       }catch(SQLException e){
           e.printStackTrace();
       }

   }

    public void txtBookIdOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void txtPriceOnAction(ActionEvent actionEvent) {
        txtShelf.requestFocus();
    }

    public void txtCategoryOnAction(ActionEvent actionEvent) {
        txtIsbm.requestFocus();
    }

    public void txtBookNameOnAction(ActionEvent actionEvent) {
        txtCategory.requestFocus();
    }

    public void txtIsbmOnAction(ActionEvent actionEvent) {
        txtAuthor.requestFocus();
    }

    public void txtAuthorOnAction(ActionEvent actionEvent) {
        txtPrice.requestFocus();
    }

    public void txtShelfOnAction(ActionEvent actionEvent) {
        txtOrderId.requestFocus();
    }


    public void txtOnKeyPrice(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("[0-9][1-9.]*[0-9]+[1-9]*");
        Matcher m1=p1.matcher(txtPrice.getText());
        boolean b=m1.find();
        if (b){
            txtPrice.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtPrice.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }
    public void txtOnKeyauthor(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$");
        Matcher m1=p1.matcher(txtAuthor.getText());
        boolean b=m1.find();
        if (b){
            txtAuthor.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtAuthor.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }

    }

    public void txtCategoryKeyRealsed(KeyEvent keyEvent) {
        Pattern p1=Pattern.compile("^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$");
        Matcher m1=p1.matcher(txtCategory.getText());
        boolean b=m1.find();
        if (b){
            txtCategory.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtCategory.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }
}
