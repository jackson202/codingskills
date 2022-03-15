package Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    //Create a list of barcodes used by a Company
    public static List<Barcode> createBarcodeList(String barcodes) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(barcodes));
        scanner.nextLine();
        List<Barcode> barcodeList = new ArrayList<Barcode>();
        Barcode barcodeData;

        while(scanner.hasNextLine()) {
            String[] src = scanner.nextLine().split(",");
            barcodeData = new Barcode(Integer.parseInt(src[0]), src[1], src[2]);
            barcodeList.add(barcodeData);
        }

        scanner.close();
        return barcodeList;
    }
}
