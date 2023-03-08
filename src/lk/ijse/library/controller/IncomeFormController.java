package lk.ijse.library.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.IncomeDTO;
import lk.ijse.library.service.custom.FineService;
import lk.ijse.library.service.custom.IncomeService;
import lk.ijse.library.service.custom.impl.FineServiceImpl;
import lk.ijse.library.service.custom.impl.IncomeServiceImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

public class IncomeFormController {
    public AnchorPane pane;
    public StackedBarChart stckdChart;
    public TableView tblIncome;
    public TableColumn clmMemberId;
    public TableColumn clmFine;
    public TableColumn clmDate;
    private IncomeService incomeService;
    private FineService fineService;

    public void initialize() throws SQLException, ClassNotFoundException {
        fineService=new FineServiceImpl();
        incomeService=new IncomeServiceImpl();
        setChart();
        setTableIncome();
    }

    public void printOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        printIncomeReport();
    }

    public void printIncomeReport() throws SQLException, ClassNotFoundException, JRException {
        JasperDesign jasdi = JRXmlLoader.load("E:\\Important Project\\Final Project In Library Management System\\src\\lk\\ijse\\library\\report\\FineReport.jrxml");
        String sql = "select m.member_id as member_id,m.Member_Name as Member_name,f.date as date, sum(f.fine) as total,Month(f.date)  as month,Year(f.date) as year from fine f  inner join book_record br on f.Record_Id = br.BookRecord_Id inner join member m on br.Member_Id = m.Member_Id group by m.Member_Id,month,year having month = "+LocalDate.now().getMonthValue()+" and year = '"+
                LocalDate.now().getYear()+"'";
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);
        jasdi.setQuery(newQuery);

        JasperReport js = JasperCompileManager.compileReport(jasdi);
        JasperPrint jp = JasperFillManager.fillReport(js, null, DBConnection.getInstance().getConnection());


        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();

    }

    public void setChart(){
        try {
            HashMap hm = fineService.getIncomeByMonthlyFroChart(LocalDate.now().getYear());
            XYChart.Series series = new XYChart.Series();
            series.setName(String.valueOf(LocalDate.now().getYear()));
            for(int i = 1 ; i <=12 ; i++){
                if(hm.get(i)==null){
                    series.getData().add(new XYChart.Data<>(Month.of(i).toString(),0));
                }else{
                    series.getData().add(new XYChart.Data<>(Month.of(i).toString(),Double.parseDouble
                            (String.valueOf(hm.get(i)))));
                }
            }
            stckdChart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setTableIncome() throws SQLException, ClassNotFoundException {
        clmMemberId.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("member_Id"));
        clmFine.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("fine"));
        clmDate.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("date"));

        ObservableList<IncomeDTO> incomeDTOS = incomeService.getAllIncome();
        tblIncome.setItems(incomeDTOS);
    }
}
