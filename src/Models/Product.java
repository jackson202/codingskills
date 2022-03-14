package Models;

//Data Object to represent a companies product
public class Product {

    //Products ID
    private String sku;

    //Products description
    private String description;

    public Product() {
    }

    public Product(String sku, String description) {
        this.sku = sku;
        this.description = description;
    }

    //Get Products sku, will return empty string if no Sku
    public String getSku() {
        return sku;
    }

    //Get Products Description will return empty string if no Description
    public String getDescription() {
        return description;
    }
}
