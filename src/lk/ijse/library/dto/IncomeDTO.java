package lk.ijse.library.dto;

public class IncomeDTO {
private String member_Id;
private double fine;
private String date;

    public IncomeDTO() {
    }

    public IncomeDTO(String member_Id, double fine, String date) {
        this.member_Id = member_Id;
        this.fine = fine;
        this.date = date;
    }

    public String getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(String member_Id) {
        this.member_Id = member_Id;
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
