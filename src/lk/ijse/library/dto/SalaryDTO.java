package lk.ijse.library.dto;

public class SalaryDTO extends EmployeeDTO {
    private String employee_id;
    private double salary;
    private String date;

    public SalaryDTO() {
    }

    public SalaryDTO(String employee_id, double salary, String date) {
        this.employee_id = employee_id;
        this.salary = salary;
        this.date = date;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "employee_id='" + employee_id + '\'' +
                ", salary=" + salary +
                ", date='" + date + '\'' +
                '}';
    }
}
