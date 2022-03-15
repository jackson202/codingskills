package Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Supplier {

    //Numerical ID of Suppliers
    private int id;
    //Name of supplier
    private String name;

    public Supplier() {}

    public Supplier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //returns Suppliers Ids returns null if empty id
    public int getId() {
        return id;
    }

    //returns Suppliers name returns empty string if no Name
    public String getName() {
        return name;
    }

    public static List<Supplier> createSupplierList(String suppliers) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(suppliers));
        scanner.nextLine();
        List<Supplier> supplierList = new ArrayList<Supplier>();
        Supplier supplier;

        while(scanner.hasNextLine()) {
            String[] src = scanner.nextLine().split(",");
            supplier = new Supplier(Integer.parseInt(src[0]), src[1]);
            supplierList.add(supplier);
        }

        scanner.close();
        return supplierList;
    }
}
