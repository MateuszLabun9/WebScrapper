import Controler.OfferScrapingController;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebScrapperTest {

    OfferScrapingController offerScrapingController = new OfferScrapingController();


    //Parametrized test that evaluates URL
    @ParameterizedTest
    @ValueSource(strings = {"https://wltest.dns-systems.net/", "https://wltest.dns-systems/"})
    public void testUrl(String URL) {
        try {
            offerScrapingController.scrapeOptions(URL);
        } catch (Exception e) {
            System.out.println("Test: wrong URL");
        }
    }

    //Test that rvaluates result of generated JSON
    @Test
    public void testResultJSON() {
        try {
            String URL = "https://wltest.dns-systems.net/";
            String testResult = "[{\"option title\":\"Optimum: 2 GB Data - 12 Months\",\"price\":\"£15.99\",\"description\":\"2GB data per month including 40 SMS (5p / minute and 4p / SMS thereafter)\",\"discount\":\"\"},{\"option title\":\"Optimum: 24GB Data - 1 Year\",\"price\":\"£174.00\",\"description\":\"Up to 12GB of data per year including 480 SMS (5p / MB data and 4p / SMS thereafter)\",\"discount\":\"Save £17.90 on the monthly price\"},{\"option title\":\"Standard: 1GB Data - 12 Months\",\"price\":\"£9.99\",\"description\":\"Up to 1 GB data per month including 35 SMS (5p / MB data and 4p / SMS thereafter)\",\"discount\":\"\"},{\"option title\":\"Standard: 12GB Data - 1 Year\",\"price\":\"£108.00\",\"description\":\"Up to 12GB of data per year including 420 SMS (5p / MB data and 4p / SMS thereafter)\",\"discount\":\"Save £11.90 on the monthly price\"},{\"option title\":\"Basic: 500MB Data - 12 Months\",\"price\":\"£5.99\",\"description\":\"Up to 500MB of data per month including 20 SMS (5p / MB data and 4p / SMS thereafter)\",\"discount\":\"\"},{\"option title\":\"Basic: 6GB Data - 1 Year\",\"price\":\"£66.00\",\"description\":\"Up to 6GB of data per year including 240 SMS (5p / MB data and 4p / SMS thereafter)\",\"discount\":\"Save £5.86 on the monthly price\"}]";
            offerScrapingController.scrapeOptions(URL);
            assertEquals(testResult, offerScrapingController.getJsonArray());

        } catch (Exception e) {
            System.out.println("Test: JSON");
        }
    }


}
