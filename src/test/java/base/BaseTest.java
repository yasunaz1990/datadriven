package base;

import com.jayway.jsonpath.JsonPath;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp() {
        driver.close();
        driver.quit();
    }


    protected void pause(int second) {
        try{
            Thread.sleep(second * 1000);
        }
        catch (InterruptedException ignore) {
            // DO NOTHING
        }
    }

    protected String getData(String q) {
        // 1. json file ---> string
        String filePath = System.getProperty("user.dir") + "/data/testdata.json";
        String content = "";
        try{
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        }
        catch (IOException ignore) {
           // DO NOTHING
        }
        // 2. Jsonpath ---> extract
        return JsonPath.read(content, q);
    }

}
