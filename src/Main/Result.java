package Main;

import Resources.Constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Result {
    //Product SKU
    private String sku;

    //Product Description
    private String description;

    //Product Source
    private String source;

    public Result() {
    }

    public Result(String sku, String description, String source) {
        this.sku = sku;
        this.description = description;
        this.source = source;
    }

    //Get results description
    public String getDescription() {
        return description;
    }

    //Get results Sku
    public String getSku() {
        return sku;
    }

    public String getSource(){
        return source;
    }

    //Check if SKU match
    public  static boolean  skuCompare(String aSku, String bSKU)
    {
        return aSku.equals(bSKU);
    }
    //check if SKU is in result list
    public  static boolean  matchingSkuInResult(String Sku, ArrayList<Result> results)
    {
        return  results.stream().filter(x -> x.getSku().equals(Sku)).findFirst().orElse(null) != null;
    }

    //Remove SKUs with matching barcodes
    public static ArrayList<Result> removeProducts(ArrayList<Result> results, ArrayList<String> skusToRemove) {
        for ( String sku: skusToRemove) {
            results.removeIf(s -> s.getSku().equals(sku));
        }
        return results;
    }

    //Method to out put results to csv
    public static void createOutputfile(ArrayList<Result> results) throws IOException {
        try (PrintWriter writer = new PrintWriter(Constants.outputFile)) {

            StringBuilder sb = new StringBuilder();
            sb.append("SKU");
            sb.append(',');
            sb.append("Description");
            sb.append(',');
            sb.append("Source");
            sb.append('\n');

            for (Result result: results ) {
                sb.append(result.sku);
                sb.append(',');
                sb.append(result.getDescription());
                sb.append(',');
                sb.append(result.getSource());
                sb.append('\n');
            }

            writer.write(sb.toString());
        }
    }

}

