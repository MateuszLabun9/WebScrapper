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

    JSONArray jsonArray = new JSONArray();
    List<ProductOptions> productOptions = new ArrayList<ProductOptions>();

    public List<ProductOptions> scrapeOptions(String URL) throws IOException, Exception {

        NumberFormat format = NumberFormat.getCurrencyInstance();
        Document parsedDocument = Jsoup.connect(URL).get();
        Elements body = parsedDocument.select("div.row-subscriptions");

        for (Element e : body.select("div.col-xs-4, div.col-cs-4")) {
            String name = e.select("h3").text();
            String description = e.select(".package-description").text();
            String price = e.select(".price-big").text();

            Float annual = Float.parseFloat(format.parse(price).toString());
            if (name.contains("12 Months")) {
                annual = annual * 12;
            }
            String discount = e.select("p").text();

            ProductOptions options = new ProductOptions(name, description, price, discount, annual);
            productOptions.add(options);
        }

        Collections.sort(productOptions, new Comparator<ProductOptions>() {
            @Override
            public int compare(ProductOptions first, ProductOptions second) {
                return Float.compare(second.getAnnualPrice(), first.getAnnualPrice());
            }
        });


        for (ProductOptions elem : productOptions) {
            jsonArray.add(elem.getJSONObject());
        }

        try (FileWriter file = new FileWriter("results.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return productOptions;
    }

    public String getJsonArray() {
        return jsonArray.toString();
    }


}
