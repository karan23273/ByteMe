package Admin_Interface;

public class Food_Item {
    private String name;
    private int price;
    private boolean available;
    private String category;

    // Constructor
    public Food_Item(String name, int price, boolean available, String category) {
        this.name = name;
        this.price = price;
        this.available = available;
        this.category = category;
    }

    // Getter and Setter methods
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
