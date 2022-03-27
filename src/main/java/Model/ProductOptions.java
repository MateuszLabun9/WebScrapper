package Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductOptions {

    private String title;
    private String description;
    private String price;
    private String discount;
    private Float annualPrice;

    public ProductOptions(String title, String description, String price, String discount, Float annualPrice) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.annualPrice = annualPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Float getAnnualPrice() {
        return annualPrice;
    }

    public void setAnnualPrice(Float annualPrice) {
        this.annualPrice = annualPrice;
    }

    public JSONObject getJSONObject() { //Method that creates JSON object based on ProductOptions
        JSONObject object = new JSONObject();
        try {
            object.put("option title", title);
            object.put("description", description);
            object.put("price", price);
            object.put("discount", discount);
        } catch (JSONException e) {
            throw new JSONException("Error during creating JSON object");
        }
        return object;
    }


    @Override
    public String toString() {
        return "ProductOptions{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", annualPrice=" + annualPrice +
                '}';
    }
}
