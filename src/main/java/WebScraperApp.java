import Controler.OfferScrapingController;

import java.io.IOException;


public class WebScraperApp {

    public static void main(String[] args) {
        String URL = "https://wltest.dns-systems.net/";

        OfferScrapingController offerScrapingController = new OfferScrapingController();

        try {
            offerScrapingController.scrapeOptions(URL);
        } catch (IOException e) {
            System.out.println("Cannot Reach Specific Page " + URL);
        } catch (Exception e) {
            System.out.println("Error, operation failed");
        }

    }
}

