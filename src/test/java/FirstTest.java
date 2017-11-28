import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class FirstTest {

    protected static  final String BASE_URL = "https://github.com";
    protected static WebDriver webDriver;

    @BeforeClass
    public  static void setUp(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get(BASE_URL);
    }

    @AfterClass
    public static void tearDown(){
        webDriver.quit();
        webDriver = null;
    }

    @Ignore
    @Test
    public void githubShouldBeOpen(){
        Assert.assertTrue("Check site title",webDriver.getTitle().contains("GitHub"));
    }

    @Test
    public  void searchRepository(){
        WebElement inputField = webDriver.findElement(By.name("q"));
        inputField.click();
        inputField.sendKeys("maven");
        inputField.submit();
        WebElement firstResult = webDriver.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div[1]/div/div[1]/ul/div[1]/div[1]/h3/a/em"));
        Assert.assertEquals("maven", firstResult.getText());

    }
}
