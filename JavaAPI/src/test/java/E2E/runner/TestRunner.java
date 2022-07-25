package E2E.runner;

import E2E.poms.chat.ChatPage;
import E2E.poms.group.GroupJunctionPOM;
import E2E.poms.group.GroupPage;
import E2E.poms.homepage.UserHomePage;
import E2E.poms.IndividualGroupPOM;
import E2E.poms.RegLoginSearchPOM;
import E2E.poms.UserProfile;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    glue = "E2E.steps",
    plugin = { "pretty","html:src/test/java/resources/reports/html-reports.html" })//, tags = "@danlol")
public class TestRunner {

    public static WebDriver driver;
    public static WebDriverWait explicitWait;

    // POMs
    public static ChatPage chatPage;
    public static RegLoginSearchPOM rlsPom;
    public static UserProfile userProfile;
    public static UserHomePage userHomePage;
    public static GroupPage groupPage;
    public static GroupJunctionPOM groupJunctionPOM;
    public static IndividualGroupPOM individualGroupPOM;

    @BeforeClass
    public static void setup() {
        File file = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--allow-running-insecure-content");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // POMs
        chatPage = new ChatPage(driver);
        rlsPom = new RegLoginSearchPOM(driver);
        userProfile = new UserProfile(driver);
        userHomePage = new UserHomePage(driver);
        groupPage = new GroupPage(driver);
        groupJunctionPOM = new GroupJunctionPOM(driver);
        individualGroupPOM = new IndividualGroupPOM(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(6));
        System.out.println("Set up complete!");
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
        System.out.println("teardown complete!");

    }

}