package lk.ijse.library.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.library.dto.IssueDTO;
import lk.ijse.library.service.custom.BookService;
import lk.ijse.library.service.custom.IssueBookService;
import lk.ijse.library.service.custom.impl.BookServiceImpl;
import lk.ijse.library.service.custom.impl.IssueBookServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class IssueBookFormController {
    public AnchorPane pane;
    public JFXTextField txtMemberId;
    public JFXTextField txtBookId;
    public TableView tblIssueBook;
    public TableColumn clmMemberId;
    public TableColumn clmBookId;
    public JFXTextField txtBookRecordId;
    public JFXDatePicker dateIssue;
    public JFXDatePicker dateReturn;
    public JFXComboBox cmbStutes;
    public TableColumn clmIssueBookId;
    public TableColumn clmIssueDate;
    public TableColumn clmReturnDate;
    public TableColumn clmStatus;
    private IssueBookService issueBookService;
    private BookService bookService;

    public void initialize() throws SQLException, ClassNotFoundException {
        issueBookService = new IssueBookServiceImpl();
        bookService = new BookServiceImpl();
        setData();
        setTableBooks();
        newIssueBookId();
    }

    private void newIssueBookId() throws SQLException, ClassNotFoundException {
        txtBookRecordId.setText(issueBookService.getNewIssueBook());
    }

    private void setData(){
       cmbStutes .getItems().add("Issued");
        cmbStutes.getItems().add("Returned");
    }


    public void searchbtnOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        IssueDTO issueDTO = issueBookService.searchRecord(txtBookRecordId.getText());
        if(issueDTO ==null){
            new Alert(Alert.AlertType.ERROR,"Record Not Found").show();

        }else{
            txtMemberId.setText(issueDTO.getMember_id());
            txtBookId.setText(issueDTO.getBook_id());
            dateIssue.setValue(LocalDate.parse(issueDTO.getIssue()));
            dateReturn.setValue(LocalDate.parse(issueDTO.getReturn1()));
            cmbStutes.setValue(issueDTO.getStutes());
        }
    }

    public void AddbtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String record_id=txtBookRecordId.getText();
        String member_id=txtMemberId.getText();
        String book_id=txtBookId.getText();
        String issue= String.valueOf(dateIssue.getValue());
        String return1= String.valueOf(dateReturn.getValue());
        String stutes= String.valueOf(cmbStutes.getValue());
        try {
            if(issueBookService.isAlreadyIssued(book_id)){
                new Alert(Alert.AlertType.ERROR,"Book Is Already issued Another Person").show();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        IssueDTO issuebook=new IssueDTO(record_id,member_id,book_id,issue,return1,stutes);
        boolean isAdded = false;

        try {
            isAdded = issueBookService.addRecordBook(issuebook);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
            try {
                setTableBooks();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Fuck").show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            newIssueBookId();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Order is Already Added").show();
        }
    }

    private void clearFields() {
        txtMemberId.clear();
        txtBookId.clear();
        dateIssue.setValue(null);
        dateReturn.setValue(null);
        cmbStutes.setValue(null);

    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

       String bookRecordId=txtBookRecordId.getText();

        String dateCount = bookService.LateDate(bookRecordId);
        System.out.println(dateCount);
        if(dateCount!=null){
            int dc = Integer.parseInt(dateCount);
            if(dc>0){
                Stage st = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FineForm.fxml"));
                Parent load = loader.load();
                FineFormController controller = loader.getController();
                controller.setLblFine(dc);
                controller.setRecordId(txtBookRecordId.getText());
                st.setScene(new Scene(load));
                st.initModality(Modality.APPLICATION_MODAL);
                st.initOwner(pane.getScene().getWindow());
                st.showAndWait();

            }
        }

        String memberId= txtMemberId.getText();
        String bookId=txtBookId.getText();
        String issue= String.valueOf(dateIssue.getValue());
        String returnBook= String.valueOf(dateReturn.getValue());
        String stutes= String.valueOf(cmbStutes.getValue());


        IssueDTO issueDTO1 =new IssueDTO(bookRecordId,memberId,bookId,issue,returnBook,stutes);
        boolean isUpdated= issueBookService.updateRecord(issueDTO1);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableBooks();
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Update Fail").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        boolean isDeleted = false;
        try {
            isDeleted = issueBookService.deleteIssueBookRecord(txtBookRecordId.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
            try {
                setTableBooks();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
        }
    }

    private void setTableBooks() throws SQLException, ClassNotFoundException {
        clmIssueBookId.setCellValueFactory(new PropertyValueFactory<IssueDTO,String>("recod_id"));
        clmMemberId.setCellValueFactory(new PropertyValueFactory<IssueDTO,String>("member_id"));
        clmBookId.setCellValueFactory(new PropertyValueFactory<IssueDTO,String>("book_id"));
        clmIssueDate.setCellValueFactory(new PropertyValueFactory<IssueDTO,String>("issue"));
        clmReturnDate.setCellValueFactory(new PropertyValueFactory<IssueDTO,String>("return1"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<IssueDTO,String>("stutes"));
        ObservableList<IssueDTO> issueDTOS = issueBookService.getAllRecords();
        tblIssueBook.setItems(issueDTOS);

    }

    public void txtIssueRecordIdOnAction(ActionEvent actionEvent) {
        txtMemberId.requestFocus();
    }

    public void txtBookIdOnAction(ActionEvent actionEvent) {
        tblIssueBook.requestFocus();
    }

    public void txtMemberIdOnAction(ActionEvent actionEvent) {
        txtBookId.requestFocus();
    }

    public void txtIssueDateOnAction(ActionEvent actionEvent) {
        dateReturn.requestFocus();
    }

    public void txtReturnDateOnAction(ActionEvent actionEvent) {
        cmbStutes.requestFocus();
    }
}
