package action_indicators_xp;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ActionIndicatorsTest {
    private WebDriver driver;
    private String url = "https://stock-service-doj0.onrender.com/";
    private Date initialDate;
    private Date finalDate;

    public ActionIndicatorsTest(){
        setup();
    }

    private void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    private Date stringToDate(String input, String format) throws ParseException {
        return new SimpleDateFormat(format, Locale.ENGLISH).parse(input);
    }

    @Given("I am in action indicators search page")
    public void given() {
        driver.get(url);
    }

    @When("insert {string} in action field")
    public void when(String action) {
        driver.findElement(By.id("id_nome")).sendKeys(action);
    }

    @And("insert {string} in initial date field")
    public void whenAnd1(String initialDate) throws ParseException {
        this.initialDate = stringToDate(initialDate, "MM-dd-yyyy");
        driver.findElement(By.id("id_data_inicial")).sendKeys(initialDate);
    }

    @And("insert {string} in final date field")
    public void whenAnd2(String finalDate) throws ParseException {
        this.finalDate = stringToDate(finalDate, "MM-dd-yyyy");
        driver.findElement(By.id("id_data_final")).sendKeys(finalDate);
    }

    @And("click in submit button")
    public void whenAnd3() {
        driver.findElement(By.cssSelector("button[type=submit]")).click();
    }

    @Then("the fields date, open, high, low, close and rsi should be in main table")
    public void scenario1Then() {
        By mainTableHeadersSelector = By.cssSelector(".container > .table-responsive:nth-child(2) th");
        List<String> mainTableHeaders = driver
            .findElements(mainTableHeadersSelector)
            .stream()
            .map(th -> th.getText().toLowerCase())
            .collect(Collectors.toList());
        assertTrue(mainTableHeaders.contains("date"));
        assertTrue(mainTableHeaders.contains("open"));
        assertTrue(mainTableHeaders.contains("high"));
        assertTrue(mainTableHeaders.contains("low"));
        assertTrue(mainTableHeaders.contains("close"));
        assertTrue(mainTableHeaders.contains("rsi"));
    }

    @And("first table line date should be greater than or equal initial date")
    public void scenario1ThenAnd1() throws ParseException {
        By firstLineDateSelector = By.cssSelector(".container > .table-responsive:nth-child(3) tr:first-child td:first-child");
        WebElement firstLineDateElement = driver.findElement(firstLineDateSelector);
        if(firstLineDateElement == null) return;
        Date firstLineDate = stringToDate(firstLineDateElement.getText(), "dd-MM-yyyy");
        assertTrue(firstLineDate.compareTo(this.initialDate) >= 0);
    }

    @And("last table line date should be less than or equal final date")
    public void scenario1ThenAnd2() throws ParseException {
        By lastLineDateSelector = By.cssSelector(".container > .table-responsive:nth-child(3) tr:last-child td:first-child");
        WebElement lastLineDateElement = driver.findElement(lastLineDateSelector);
        Date lastLineDate = stringToDate(lastLineDateElement.getText(), "dd-MM-yyyy");
        assertTrue(lastLineDate.compareTo(this.finalDate) <= 0);
    }

    @And("the fields medium return, volatility and var should be in top table")
    public void scenario1ThenAnd3() {
        By topTableHeadersSelector = By.cssSelector("#content .table-responsive th");
        List<String> topTableHeaders = driver
            .findElements(topTableHeadersSelector)
            .stream()
            .map(th -> th.getText().toLowerCase())
            .collect(Collectors.toList());
        assertTrue(topTableHeaders.contains("retorno médio"));
        assertTrue(topTableHeaders.contains("volatilidade"));
        assertTrue(topTableHeaders.contains("var (value-at-risk)"));
    }

    @Then("an error message should be displayed")
    public void scenario2Then(){
        String text = driver.findElement(By.className("container")).getText();
        assertTrue(text.contains("Empresa não encontrada ou falha no download dos dados!"));
    }

    @After
    public void exit() {
        driver.quit();
    }
}
