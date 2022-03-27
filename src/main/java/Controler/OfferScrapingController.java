package Controler;

import Model.ProductOptions;
import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class OfferScrapingController {

    JSONArray jsonArray = new JSONArray();  //Create new JSONArray
    List<ProductOptions> productOptions = new ArrayList<ProductOptions>();  //Create List of Productoptions

    public List<ProductOptions> scrapeOptions(String URL) throws IOException, Exception {

        NumberFormat format = NumberFormat.getCurrencyInstance();  //Number format used for extracting value
        Document parsedDocument = Jsoup.connect(URL).get(); //Connection to URL
        Elements body = parsedDocument.select("div.row-subscriptions");  //Selecting proper div element

        for (Element e : body.select("div.col-xs-4, div.col-cs-4")) {  //For loop to select all necessary data
            String name = e.select("h3").text();  //Scraping name of package
            String description = e.select(".package-description").text(); //Scraping description of package
            String price = e.select(".price-big").text(); //Scraping price of package

            Float annual = Float.parseFloat(format.parse(price).toString()); //Annual used to sort by annual price
            if (name.contains("12 Months")) {  //If price is per Month
                annual = annual * 12; //Multiply by 12 to get annual price
            }
            String discount = e.select("p").text(); //Scraping discount of package

            //Create ProductOptions object with scraped data
            ProductOptions options = new ProductOptions(name, description, price, discount, annual);
            productOptions.add(options); //Add this object to List
        }

        //Sort List of productOptions by annual price of package
        Collections.sort(productOptions, new Comparator<ProductOptions>() {
            @Override
            public int compare(ProductOptions first, ProductOptions second) {
                return Float.compare(second.getAnnualPrice(), first.getAnnualPrice());
            }
        });

        //Generate JSONArray based on productOptions List
        for (ProductOptions elem : productOptions) {
            jsonArray.add(elem.getJSONObject());
        }

        //Save Generated JSONArray to .json file
        try (FileWriter file = new FileWriter("results.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return productOptions;
    }

    //Method used in test
    public String getJsonArray() {
        return jsonArray.toString();
    }


}
