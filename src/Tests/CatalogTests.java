package Tests;

import Main.Barcode;
import Main.Result;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogTests {
    private ArrayList<Result> results;
    private ArrayList<Barcode> barcodesCompany1;
    private ArrayList<Barcode> barcodesCompany2;
    private ArrayList<Barcode> barcodesCompany2NoMatch;
    private ArrayList<String> skus;
    private ArrayList<String> skusNoMatch;

    @BeforeEach
void init() {
    results= new ArrayList<Result>();
    results.add(new Result("165-rcy-500", "test product", "B"));
    results.add(new Result("165-rcy-650", " test product 2", "B"));
    results.add(new Result("165-rcy-500", "test product 3", "B"));
    results.add(new Result("165-rcy-400", " test product 3", "B"));

    barcodesCompany1 = new ArrayList<Barcode>();
    barcodesCompany1.add(new Barcode(00001,"165-rcy-500", "z2783613083817"));
    barcodesCompany1.add(new Barcode(00002,"546-tfr-750", "w3744746803743"));

    barcodesCompany2 = new ArrayList<Barcode>();
    barcodesCompany2.add(new Barcode(00001,"165-rcy-590", "z2783613083817"));
    barcodesCompany2.add(new Barcode(00002,"546-tfr-750", "w374474687777"));

    barcodesCompany2NoMatch = new ArrayList<Barcode>();
    barcodesCompany2NoMatch.add(new Barcode(00001,"165-rcy-999", "z2783671308381"));
    barcodesCompany2NoMatch.add(new Barcode(00002,"646-6fr-690", "w374474687777"));
    skus = new ArrayList<String>();
    skus.add("165-rcy-650");
    skusNoMatch = new ArrayList<String>();
    skusNoMatch.add("999-rcy-444");


    }
    @Test
    @DisplayName("Same SKU returns true")
    void testSkuEquality(){
        assertEquals(true, Result.skuCompare("165-rcy-650", "165-rcy-650"));
    }

    @Test
    @DisplayName("Diffrent SKU returns false")
    void testSkuUnequality(){
        assertEquals(false, Result.skuCompare("165-rcy-650", "165-rcy-555"));
    }

    @Test
    @DisplayName("test if product SKU is listed in results list")
    void testSkuResultsEquality(){
        assertEquals(true, Result.matchingSkuInResult("165-rcy-650", results));
    }
    @Test
    @DisplayName("test if product SKU is not listed in results list")
    void testSkuResultsUnequality(){
        assertEquals(false, Result.matchingSkuInResult("165-rcy-999", results));
    }

    @Test
    @DisplayName("test if product SKU is not listed in empty results")
    void testSkuResultsUnequalityEmptyList(){
        ArrayList<Result> results= new ArrayList<Result>();
        assertEquals(false, Result.matchingSkuInResult("165-rcy-650", results));
    }

    @Test
    @DisplayName("Test get list of Barcodes with Matching SDK from results list")
    void testGetListOfBarcodesWithMatchingSDK(){
        ArrayList<Barcode> expectedResult = new ArrayList<Barcode>();
        expectedResult.add(new Barcode(00001,"165-rcy-500", "z2783613083817"));
        ArrayList<Barcode> actualResult = Barcode.getlistOfBarcodesWithMatchingSDK(results, barcodesCompany1);
        assertTrue( expectedResult.size() == 1
        && expectedResult.get(0).getSku().equals( actualResult.get(0).getSku())
        && expectedResult.get(0).getBarcode() == actualResult.get(0).getBarcode()
        && expectedResult.get(0).getSupplierId() == actualResult.get(0).getSupplierId());
    }
    @Test
    @DisplayName("Test get list of Barcodes with no mtching SDK from results list")
    void testGetlistOfBarcodesWithNoMatchingSDK(){
        ArrayList<Barcode> expectedResult = new ArrayList<Barcode>();
        expectedResult.add(new Barcode(00001,"165-rcy-999", "z2783613083817"));
        ArrayList<Barcode> actualResult = Barcode.getlistOfBarcodesWithMatchingSDK(results, barcodesCompany1);
        assertFalse( expectedResult.size() == actualResult.size()
                && expectedResult.get(0).getSku().equals( actualResult.get(0).getSku())
                && expectedResult.get(0).getBarcode() == actualResult.get(0).getBarcode()
                && expectedResult.get(0).getSupplierId() == actualResult.get(0).getSupplierId());
    }

    @Test
    @DisplayName("Test get list of Barcodes with empty inputs")
    void testGetlistOfBarcodesWithNullInput(){
        ArrayList<Barcode> expectedResult = new ArrayList<Barcode>();
        ArrayList<Barcode> actualResult = Barcode.getlistOfBarcodesWithMatchingSDK(new ArrayList<Result>(), new ArrayList<Barcode>());
        assertTrue(actualResult.isEmpty());
    }

    @Test
    @DisplayName("Test get list of Barcodes with empty results input")
    void testGetListOfBarcodesWithEmptyResultInput(){
        ArrayList<Barcode> expectedResult = new ArrayList<Barcode>();
        ArrayList<Barcode> actualResult = Barcode.getlistOfBarcodesWithMatchingSDK(new ArrayList<Result>(), barcodesCompany1);
        assertTrue(actualResult.isEmpty());
    }

    @Test
    @DisplayName("Test get list of Barcodes with empty barcodes input")
    void testGetListOfBarcodesWithEmptyBarcodeInput(){
        ArrayList<Barcode> expectedResult = new ArrayList<Barcode>();
        ArrayList<Barcode> actualResult = Barcode.getlistOfBarcodesWithMatchingSDK(results, new ArrayList<Barcode>());
        assertTrue(actualResult.isEmpty());
    }

    @Test
    @DisplayName("Test get list of matching barcodes with matches")
    void getListOfMatchingBarcodesWithMatches(){
        ArrayList<String> actualResult = Barcode.getListOfMatchingBarcodes(barcodesCompany1, barcodesCompany2);
        String[] expectedResult = {"165-rcy-590"};
        assertArrayEquals(actualResult.toArray(),expectedResult);
    }

    @Test
    @DisplayName("Test get list of matching barcodes with no matches")
    void getListOfMatchingBarcodesWithNoMatches(){
        ArrayList<String> actualResult = Barcode.getListOfMatchingBarcodes(barcodesCompany1, barcodesCompany2NoMatch);
        String[] expectedResult = {};
        assertArrayEquals(actualResult.toArray(),expectedResult);
    }

    @Test
    @DisplayName("Test get list of matching barcodes with empty input")
    void getListOfMatchingBarcodesWithEmptyInputs(){
        ArrayList<String> actualResult = Barcode.getListOfMatchingBarcodes(new ArrayList<Barcode>(), new ArrayList<Barcode>());
        String[] expectedResult = {};
        assertArrayEquals(actualResult.toArray(),expectedResult);
    }

    @Test
    @DisplayName("Test get list of matching barcodes with barcode 1")
    void getListOfMatchingBarcodesWithEmptyBarcodes1(){
        ArrayList<String> actualResult = Barcode.getListOfMatchingBarcodes(new ArrayList<Barcode>(), barcodesCompany2);
        String[] expectedResult = {};
        assertArrayEquals(actualResult.toArray(),expectedResult);
    }

    @Test
    @DisplayName("Test get list of matching barcodes with barcode 2")
    void getListOfMatchingBarcodesWithEmptyBarcodes2(){
        ArrayList<String> actualResult = Barcode.getListOfMatchingBarcodes(barcodesCompany2, new ArrayList<Barcode>());
        String[] expectedResult = {};
        assertArrayEquals(actualResult.toArray(),expectedResult);
    }

    @Test
    @DisplayName("Test remove products from output list with products")
    void removeProductsFromOutputListProductsToRemove(){
        int size = results.size();
        results = Result.removeProducts(results, skus);
        assertTrue(results.size() == size - 1 &&
                !results.stream().anyMatch(x -> x.getSku().equals(skus.get(0))));
    }

    @Test
    @DisplayName("Test remove products from output list with no matching products")
    void removeProductsFromOutputList(){
        int size = results.size();
        results = Result.removeProducts(results, skusNoMatch);
        assertTrue(results.size() == size &&
                results.stream().anyMatch(x -> x.getSku().equals(skus.get(0))));
    }
    @Test
    @DisplayName("Test remove products from output list with empty inputs")
    void removeProductsFromOutputListWithEmptyInputs(){
        results = Result.removeProducts(new ArrayList<Result>(), new ArrayList<String>());
        assertTrue(results.isEmpty());
    }
    @Test
    @DisplayName("Test remove products from output list with empty results")
    void removeProductsFromOutputListWithEmptyResults(){
        results = Result.removeProducts(new ArrayList<Result>(), skus);
        assertTrue(results.size() == 0 &&
                !results.stream().anyMatch(x -> x.getSku().equals(skus.get(0))));
}
    @Test
    @DisplayName("Test remove products from output list with empty skus")
    void removeProductsFromOutputListWithEmptySkus(){
    int size = results.size();
    results = Result.removeProducts(results, new ArrayList<String>());
    assertTrue(results.size() == size );
}
}
