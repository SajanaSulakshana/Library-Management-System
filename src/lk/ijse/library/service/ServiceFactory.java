package lk.ijse.library.service;

import javafx.scene.layout.AnchorPane;
import lk.ijse.library.service.custom.EmployeeSalaryService;
import lk.ijse.library.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    public ServiceFactory(){}



    public static ServiceFactory getInstance(ServiceType book, AnchorPane pane){
        if(serviceFactory==null)serviceFactory=new ServiceFactory();
        return serviceFactory;
    }
    public <T extends SuperService>T getService(ServiceType serviceType){
        switch (serviceType){
            case Book:return (T)new BookServiceImpl();
            case Donation:return (T)new DonationServiceImpl();
            case Salary:return (T)new EmployeeSalaryServiceImpl();
            case Employee:return (T)new EmployeeServiceImpl();
            case Expenture:return (T)new ExpentureServiceImpl();
            case Fine:return (T)new FineServiceImpl();
            case Income:return (T)new BookServiceImpl();
            case Issue:return (T)new IssueBookServiceImpl();
            case Member:return (T)new MemberServiceImpl();
            case Order:return (T)new OrderServiceImpl();
        }
        return null;
    }
}
