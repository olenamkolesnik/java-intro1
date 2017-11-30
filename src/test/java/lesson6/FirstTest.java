package lesson6;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

@RunWith(JUnit4.class)
public class FirstTest {

    protected static  final String BASE_URL = "http://automationpractice.com/index.php";
    protected static WebDriver webDriver;

    @BeforeClass
    public  static void setUp(){
        webDriver = new ChromeDriver();
       // webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

        Assert.assertThat("Check site title.", webDriver.getTitle(), containsString("Store"));
        Assert.assertTrue("Check site title",webDriver.getTitle().contains("GitHub"));
    }
@Ignore
    @Test
    public  void searchRepository(){

        WebElement SearchField = webDriver.findElement(By.id("search_query_top"));
        SearchField.sendKeys("dress");
        assertThat(textToBePresentInElementLocated(By.xpath("//*[@id=\"index\"]/div[2]/ul/li[1]/strong"), "Dress"));

    }
    @Test
    public  void searchRepository1(){
        MainPage mainPage = new MainPage();
        mainPage.enterQuery("Dress");
        assertThat(textToBePresentInElement(mainPage.advice, "Dress"));
        mainPage.enterQuery("shirt");
        assertThat(textToBePresentInElement(mainPage.advice, "shirt"));
    }



    public void assertThat(ExpectedCondition<Boolean> condition){
        (new WebDriverWait(webDriver, 5)).until(condition);
    }

    class MainPage{
        @FindBy(id = "search_query_top")
        private WebElement searchField;

        @FindBy(xpath = "//*[@id=\"index\"]/div[2]/ul/li[1]/strong")
        private WebElement advice;

        MainPage(){
            PageFactory.initElements(webDriver, this);
        }

        void enterQuery(String query) {
            searchField.clear();
            searchField.sendKeys(query);
        }



    }
}
