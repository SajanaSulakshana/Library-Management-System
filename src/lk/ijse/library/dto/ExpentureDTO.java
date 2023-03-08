package lk.ijse.library.dto;

public class ExpentureDTO extends BookDTO {
    private String invoiceNumber;
    private String description;
    private String date;
    private double payment;

    public ExpentureDTO() {
    }

    public ExpentureDTO(String invoiceNumber, String description, String date, double payment) {
        this.invoiceNumber = invoiceNumber;
        this.description = description;
        this.date = date;
        this.payment = payment;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Expenture{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", payment=" + payment +
                '}';
    }
}
