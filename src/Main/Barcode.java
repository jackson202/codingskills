package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Barcode {

    //The numerical  supplier id
    private int supplierId;

    //Product SKU
    private String sku;

    //The Barcodes object barcode
    private String barcode;

    public Barcode() {
    }

    public Barcode(int supplierId, String sku, String barcode) {
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
    public static ArrayList<Barcode> createBarcodeList(String barcodes) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(barcodes));
        scanner.nextLine();
        ArrayList<Barcode> barcodeList = new ArrayList<Barcode>();
        Barcode barcodeData;

        while (scanner.hasNextLine()) {
            String[] src = scanner.nextLine().split(",");
            barcodeData = new Barcode(Integer.parseInt(src[0]), src[1], src[2]);
            barcodeList.add(barcodeData);
        }

        scanner.close();
        return barcodeList;
    }

    //Creates a list of Barcodes present in the first company
    public static ArrayList<Barcode> getlistOfBarcodesWithMatchingSDK(ArrayList<Result> results, ArrayList<Barcode> barcodes) {
        ArrayList<Barcode> matchingBarcodes = new ArrayList<Barcode>();
        for (Result r : results) {
            for (Barcode barcodeA : barcodes) {
                if (barcodeA.getSku().equals(r.getSku())) {
                    matchingBarcodes.add(barcodeA);
                }
            }
        }
        return matchingBarcodes;
    }

    //Match the barcodes of the first company with the second
    public static ArrayList<String> getListOfMatchingBarcodes(ArrayList<Barcode> barcodesOfFirstCompanyToCheck, ArrayList<Barcode> barcodesOfSecondCompanyToCheck) {
        ArrayList<String> skusToRemove = new ArrayList<String>();
        for (Barcode code : barcodesOfFirstCompanyToCheck) {
            Barcode barcodeB = barcodesOfSecondCompanyToCheck.stream().filter(x -> x.getBarcode().equals(code.getBarcode())).findFirst().orElse(null);
            if (!Objects.isNull(barcodeB) && !barcodeB.getSku().equals(code.getSku())) {
                skusToRemove.add(barcodeB.getSku());
            }
        }
        return skusToRemove;
    }
}
