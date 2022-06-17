import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class selenium4 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
//        WebDriverManager.chromedriver().setup();

//        System.setProperty("webdriver.chrome.driver", "C:/webdriver/chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "C:/webdriver/geckodriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9015");

        WebDriver driver = new ChromeDriver(options);

        WebElement p1 = driver.findElement(By.xpath("//*[text()='App Inventory']"));
        p1.click();

        WebElement b1 = driver.findElement(By.xpath("//*[text()='+']"));
        b1.click();

        System.out.println(driver.getCurrentUrl());

//        driver.get("https://www.google.com");
//        driver.manage().window().maximize();
    }
}
