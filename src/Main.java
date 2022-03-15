import Models.Barcode;
import Models.Product;
import Models.Supplier;
import Resources.Constants;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{

        //Get list of Companies product catalogs
        List<Product> catalogCompanyA = Product.createProductList( Constants.catalogA);
        List<Product> catalogCompanyB = Product.createProductList( Constants.catalogB);

        //Get list of Companies barcodes
        List<Barcode> BarcodesCompanyA = Barcode.createBarcodeList( Constants.barcodesA);
        List<Barcode> BarcodesCompanyB = Barcode.createBarcodeList( Constants.barcodesB);

        //Get list of companies Suppliers
        List<Supplier> supplierCompanyA = Supplier.createSupplierList( Constants.suppliersA);
        List<Supplier> supplierCompanyB = Supplier.createSupplierList( Constants.suppliersB);
        
    }
    }
