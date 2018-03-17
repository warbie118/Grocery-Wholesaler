package model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class ProduceModel {
    @Id
    private String id;
    @NotNull(message = "Produce must be given a name")
    private String name;
    @NotNull(message = "Produce must be given a price")
    private int price;
    @NotNull(message = "Produce must be given a stock level")
    private int stock;
    private String updated;

    public ProduceModel(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public ProduceModel(String name, int price, int stock, String updated) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
