package onliner.pageObject.pages;

import framework.BasePage;
import framework.elements.CheckBox;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static framework.Browser.getDriver;
import static framework.Browser.waitForPageLoad;

public class TVPage extends BasePage {
    private static final String PAGE_LOCATOR ="//h1";
    private static final String MANUFACTURER ="//div[@class='catalog-form__checkbox-sign' and text()='%s']/../../input";
    private static final String PRICE_FROM ="//input[@placeholder='%s']";
    private static final String PRICE_TO ="//input[@placeholder='%s']";
    private static final String RESOLUTION ="//div[@class='catalog-form__checkbox-sign' and text()='%s']/../../input";
    private static final String DIAGONAL_FROM ="//option[@value = '%s']";
    private static final String DIAGONAL_TO ="//*[@id=\"container\"]/div/div/div/div/div[2]/div[1]/div/div/div[3]/div/div[2]/div[2]/div[15]/div/div[2]/div[2]/div[2]/div/div[2]/div/select/option[@value='%s']";
    private static final String NAME_OF_ITEM ="//a[@class='catalog-form__link catalog-form__link_primary-additional catalog-form__link_base-additional catalog-form__link_font-weight_semibold catalog-form__link_nodecor' and contains(text(),'Samsung')]";
    private static final List<String> PRODUCT_LIST = getDriver().findElements(By.xpath("//div[@class='catalog-form__offers-unit catalog-form__offers-unit_primary']"))
            .stream().map(e -> e.getText()).collect(Collectors.toList());
    private WebElement productItem;
    public static String productName;

    public TVPage() {
        super(By.xpath(PAGE_LOCATOR), "TV page");
    }

    public void setManufacturer(String item){
        CheckBox manufacturerCB  = new CheckBox(By.xpath(String.format(MANUFACTURER,item)));
        manufacturerCB.clickViaJS();

    }
    public void setPriceFrom(String item, String pricefroms){
        TextBox pricefrom  = new TextBox(By.xpath(String.format(PRICE_FROM,item)));
        pricefrom.sendKeys(pricefroms);
    }
    public void setPriceTo(String item, String pricetos){
        TextBox priceto  = new TextBox(By.xpath(String.format(PRICE_TO,item)));
        priceto.sendKeys(pricetos);
    }
    public void setResolution(String item){
        CheckBox resolute = new CheckBox(By.xpath(String.format(RESOLUTION, item)));
        resolute.clickViaJS();

    }
    public void setDiagonalFrom(String item){
        Label diagonalFrom  = new Label(By.xpath(String.format(DIAGONAL_FROM,item)));
        diagonalFrom.click();
    }
    public void setDiagonalTo(String item){
        Label diagonalTo  = new Label(By.xpath(String.format(DIAGONAL_TO,item)));
        diagonalTo.click();
    }
    public void sleep() throws InterruptedException {
        Thread.sleep(100000);
    }
    public WebElement productSelection() {
        Random random = new Random();
        int i = random.nextInt(1, PRODUCT_LIST.size())-1;
        productName = PRODUCT_LIST.get(i);
        System.out.println(productName);
        return productItem = getDriver().findElement(By.xpath(String.format(NAME_OF_ITEM, productName)));
    }

}
