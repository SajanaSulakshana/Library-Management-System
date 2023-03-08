package lk.ijse.library.dto;

public class BookDTO {
    private String id;
    private String name;
    private String category;
    private String isbm;
    private String author;
    private Double price;
    private String shelf;
    private String ordeid;

    public BookDTO() {
    }

    public BookDTO(String id, String name, String category, String isbm, String author, Double price, String shelf, String ordeid) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isbm = isbm;
        this.author = author;
        this.price = price;
        this.shelf = shelf;
        this.ordeid = ordeid;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsbm() {
        return isbm;
    }

    public void setIsbm(String isbm) {
        this.isbm = isbm;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getOrdeid() {
        return ordeid;
    }

    public void setOrdeid(String ordeid) {
        this.ordeid = ordeid;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", isbm='" + isbm + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", shelf='" + shelf + '\'' +
                ", ordeid='" + ordeid + '\'' +
                '}';
    }
}
