package lk.ijse.library.dto;

public class IssueDTO {
    private String recod_id;
    private String member_id;
    private String book_id;
    private String issue;
    private String return1;
    private String stutes;

    public IssueDTO(String string, String rsString, String s, String string1, String rsString1, double aDouble, String s1, String string2) {
    }

    public IssueDTO(String recod_id, String member_id, String book_id, String issue, String return1, String stutes) {
        this.recod_id = recod_id;
        this.member_id = member_id;
        this.book_id = book_id;
        this.issue = issue;
        this.return1 = return1;
        this.stutes = stutes;
    }



    public String getRecod_id() {
        return recod_id;
    }

    public void setRecod_id(String recod_id) {
        this.recod_id = recod_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getReturn1() {
        return return1;
    }

    public void setReturn1(String return1) {
        this.return1 = return1;
    }

    public String getStutes() {
        return stutes;
    }

    public void setStutes(String stutes) {
        this.stutes = stutes;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "recod_id='" + recod_id + '\'' +
                ", member_id='" + member_id + '\'' +
                ", book_id='" + book_id + '\'' +
                ", issue='" + issue + '\'' +
                ", return1='" + return1 + '\'' +
                ", stutes='" + stutes + '\'' +
                '}';
    }
}
