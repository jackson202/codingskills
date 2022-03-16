package Main;

import Resources.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Main {

    private static ArrayList<Product> catalogCompanyA;
    private static ArrayList<Product> catalogCompanyB;
    private static ArrayList<Barcode> barcodesCompanyA;
    private static ArrayList<Barcode> barcodesCompanyB;
    private static ArrayList<Supplier> supplierCompanyB;
    private static ArrayList<Supplier> supplierCompanyA;

    public static void main(String[] args) throws Exception {
        ArrayList<Result> results = new ArrayList<Result>();

        //Get list of Companies product catalogs
        catalogCompanyA = Product.createProductList(Constants.catalogA);
        catalogCompanyB = Product.createProductList(Constants.catalogB);

        //Get list of Companies barcodes
        barcodesCompanyA = Barcode.createBarcodeList(Constants.barcodesA);
        barcodesCompanyB = Barcode.createBarcodeList(Constants.barcodesB);

        //Get list of companies Suppliers
         supplierCompanyA = Supplier.createSupplierList(Constants.suppliersA);
         supplierCompanyB = Supplier.createSupplierList(Constants.suppliersB);

         //Add companies A products to results
         for (Product a : catalogCompanyA) {
            results.add(new Result(a.getSku(), a.getDescription(), Constants.companyAName));
         }
         //Add companies B products if SKUs are not matching
        for (Product b : catalogCompanyB) {
            if (!Result.matchingSkuInResult(b.getSku(), results)) {
                results.add(new Result(b.getSku(), b.getDescription(), Constants.companyBName));
            }
        }
        //Get a list products with matching barcodes
        ArrayList<String> skusToRemove =  Barcode.getListOfMatchingBarcodes(
                Barcode.getlistOfBarcodesWithMatchingSDK(results, barcodesCompanyA),
                barcodesCompanyB);

        //If product SKU with a matching SKU is in results list remove it
        Result.removeProducts(results, skusToRemove);

        //output results to csv file
        Result.createOutputfile(results);
    }
}