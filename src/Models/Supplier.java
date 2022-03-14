package Models;

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
}
