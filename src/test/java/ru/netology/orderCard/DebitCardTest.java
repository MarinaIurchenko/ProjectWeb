package ru.netology.orderCard;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.*;

public class DebitCardTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll(){
        WebDriverManager.chromedriver().setup();



    }

    @BeforeEach
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");

    }

    @AfterEach
    public void setUpAfter(){
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestOrderCard() throws InterruptedException {
        open("http://localhost:9999/");
        $(cssSelector("[data-test-id=name] input")).sendKeys("Кузнецов Анатолий Николаевич");
        $(cssSelector("[data-test-id-phone] input")).sendKeys("+78673425109");
        $(cssSelector("[data-test-id=agreement]")).click();
        $(cssSelector("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближаейшее время";
        String actual = cssSelector("[data-test-id=name] .input_invalid .input__sub").findElement(driver).getText().trim();
        assertEquals(expected, actual);
        Thread.sleep(5000);


    }
}
