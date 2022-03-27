import Controler.OfferScrapingController;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;


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

        //JUnitCore.main("src/main/java/WebScraperApp.java");
        //JUnitCore jUnitCore = new JUnitCore();
       // Result result =  jUnitCore.run(WebScrapperTest.class);

    }
}

