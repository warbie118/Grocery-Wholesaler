package grocerywholesaler.model;

/**
 * ProducePriceOnly model
 * Model is used when updating price of Produce
 */
public class ProducePriceOnly {
    private String name;
    private double price;

    public ProducePriceOnly() {
    }

    public ProducePriceOnly(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
