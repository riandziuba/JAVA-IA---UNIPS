package core;

public class Product {
    int code;
    String description;
    float price;
    int amount;

    public Product(int code, String description, float price, int amount) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        if (price < 0) throw new IllegalArgumentException("Price can't be negative");
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
