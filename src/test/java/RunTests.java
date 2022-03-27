import jdk.jshell.execution.Util;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;


public class RunTests {
    public static void main(String[] args) {
        JUnitCore jUnitCore = new JUnitCore();

        JUnitCore.main(
                "WebScrapperTest");

//        Result result = jUnitCore.run(WebScrapperTest.class);
//        System.out.printf("Test ran: %s, Failed: %s%n",
//                result.getRunCount(), result.getFailureCount());
    }


}
