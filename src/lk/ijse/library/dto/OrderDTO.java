package lk.ijse.library.dto;



public class OrderDTO extends BookDTO {
    private String id;
    private String type;
    private double price;
    private String date;

    public OrderDTO(String string, String rstString, String s, String date) {
    }

    public OrderDTO(String id, String type, double price, String date) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}
