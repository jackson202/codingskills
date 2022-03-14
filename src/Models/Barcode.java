package Models;

public class Barcode {

    //The numberical supplier Id
    private int supplierId;

    //Product SKU
    private String sku;

    //The Barcodes object barcode
    private String barcode;

    public Barcode(){}

    public Barcode(int supplierId, String sku, String barcode){
        this.supplierId = supplierId;
        this.sku = sku;
        this.barcode = barcode;
    }

    //returns Suppliers Id If no Id returns null
    public int getSupplierId() {
        return supplierId;
    }
    //returns Barcode If no barcode returns empty string
    public String getBarcode() {
        return barcode;
    }

    //returns Product SKU If no SKU returns empty string
    public String getSku() {
        return sku;
    }
}
