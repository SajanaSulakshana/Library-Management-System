package lk.ijse.library.dto;

public class DonationDTO extends BookDTO {
    private String id;
    private String name;
    private String value;
    private String date;

    public DonationDTO() {
    }

    public DonationDTO(String id, String name, String value, String date) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
