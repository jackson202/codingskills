package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    //Create a catalog list of companies products
    public static ArrayList<Product> createProductList(String catalog) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(catalog));
        scanner.nextLine();
        ArrayList<Product> catalogList = new ArrayList<Product>();
        Product product;

        while(scanner.hasNextLine()) {
            String[] src = scanner.nextLine().split(",");
            product = new Product(src[0], src[1]);
            catalogList.add(product);
        }

        scanner.close();
        return catalogList;
    }
}
