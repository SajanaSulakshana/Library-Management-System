package lk.ijse.library.dto;

public class FineDTO {
    private String bookRecordId;
    private int dateCount;
    private double fine;
    private String date;

    public FineDTO(String bookRecordId, int dateCount, double fine, String date) {
        this.bookRecordId = bookRecordId;
        this.dateCount = dateCount;
        this.fine = fine;
        this.date = date;
    }

    public String getBookRecordId() {
        return bookRecordId;
    }

    public void setBookRecordId(String bookRecordId) {
        this.bookRecordId = bookRecordId;
    }

    public int getDateCount() {
        return dateCount;
    }

    public void setDateCount(int dateCount) {
        this.dateCount = dateCount;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
