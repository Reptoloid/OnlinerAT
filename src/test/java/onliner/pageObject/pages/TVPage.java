package onliner.pageObject.pages;

import framework.BasePage;
import framework.elements.CheckBox;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static framework.Browser.getDriver;
import static framework.elements.BaseElments.refreshPage;

public class TVPage extends BasePage {
    SoftAssert softAssert = new SoftAssert(); //Можно добавить в BasePage
    String res;
    private static final String PAGE_LOCATOR ="//h1";
    private static final String MANUFACTURER ="//div[@class='catalog-form__checkbox-sign' and text()='%s']";
    private static final String PRICE_TO ="//input[@placeholder='%s']";
    private static final String RESOLUTION ="//div[@class='catalog-form__checkbox-sign' and text()='%s']";
    private static final TextBox CB_DIAGONAL_FROM = new TextBox(By.xpath("//div[@class='input-style input-style_primary input-style_small input-style_arrow_bottom catalog-form__input catalog-form__input_width_full input-style_placeholder']//select[@class='input-style__real']"));
    private static final TextBox TXB_DIAGONAL_TO = new TextBox(By.xpath("//div[2 and @class='input-style input-style_primary input-style_small input-style_arrow_bottom catalog-form__input catalog-form__input_width_full input-style_placeholder']//select[@class='input-style__real']"));

    private static final TextBox APPLIED_FILTER_MANUFACTURE = new TextBox(By.xpath("//div[@class='catalog-form__tag-list']//div[@class='button-style button-style_either button-style_small catalog-form__button catalog-form__button_tag' and text()='Samsung']"));

    private static final TextBox APPLIED_FILTER_RESOLUTION = new TextBox(By.xpath("//div[@class='catalog-form__tag-list']//div[@class='button-style button-style_either button-style_small catalog-form__button catalog-form__button_tag' and text()='1920x1080 (Full HD)']"));
    private static final String RESULT = "//div[@class='catalog-form__tag-list']//div[@class='button-style button-style_either button-style_small catalog-form__button catalog-form__button_tag' and text()='1920x1080 (Full HD)']";

    private static final List<WebElement> FILTERED_DIAGONAL_AND_RESOLUTION = getDriver().findElements(By.xpath("//div[@class='catalog-form__parameter-part catalog-form__parameter-part_1']/div[1]"));

    private static final TextBox SPECIAL_PRICE_OFFER = new TextBox(By.xpath("//div[@class='catalog-form__description catalog-form__description_huge-additional " +
            "catalog-form__description_font-weight_bold catalog-form__description_condensed-other catalog-form__description_error-alter']//span[2]"));

    private static final List<WebElement> PRICES = getDriver().findElements(By.xpath("//div[@class='catalog-form__description catalog-form__description_huge-additional " +
                "catalog-form__description_font-weight_bold catalog-form__description_condensed-other catalog-form__description_primary']"));
    private static final TextBox TXT_ITEM = new TextBox(By.xpath("//div[contains(@class,'catalog-form__description_base-additional')]/a"));

    private WebElement productItem;
    public static String productName;

    public TVPage() {
        super(By.xpath(PAGE_LOCATOR), "TV page");
    }

    public void setManufacturer(String item){
        CheckBox manufacturerCB  = new CheckBox(By.xpath(String.format(MANUFACTURER,item)));
        manufacturerCB.scrollIntoView();
        manufacturerCB.clickViaJS();

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
        CB_DIAGONAL_FROM.sendKeys(item);
    }
    public void setDiagonalTo(String item){
       TXB_DIAGONAL_TO.sendKeys(item);

    }
    public void sleep(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkManufacturerFilterApplied(String manufacture) {
        softAssert.assertEquals(APPLIED_FILTER_MANUFACTURE.getText(), manufacture ,
                "Excpected result: " + manufacture +". Actual result: " + APPLIED_FILTER_MANUFACTURE.getText());

    }

    private void checkResolutionFilterApplied(String resolution) {
        softAssert.assertEquals(APPLIED_FILTER_RESOLUTION.getText(), resolution ,
                "Excpected result: " + resolution +". Actual result: " + APPLIED_FILTER_RESOLUTION.getText());
    }


    private void checkIfManufacturerMatch(String manufacturer) {
        refreshPage();
        List<WebElement> listOFItems = TXT_ITEM.getElements();
        for (WebElement w : listOFItems) {
            softAssert.assertTrue(w.getText().contains(manufacturer),
                    "Expected result: " + manufacturer +". Actual result: " + APPLIED_FILTER_MANUFACTURE.getText());
        }
    }

    private void checkResolution(String resolution) {
        refreshPage();
        for (WebElement w : FILTERED_DIAGONAL_AND_RESOLUTION) {
            APPLIED_FILTER_RESOLUTION.getText().substring(APPLIED_FILTER_RESOLUTION.getText().lastIndexOf("\""));
            System.out.println(w.getText());
            softAssert.assertEquals(APPLIED_FILTER_RESOLUTION, resolution ,
                    "Excpected result: " + resolution +". Actual result: " + APPLIED_FILTER_RESOLUTION.getText());
        }
    }

    private void checkInches(double diagonalFrom, double diagonalTo) {
        for (WebElement w : FILTERED_DIAGONAL_AND_RESOLUTION) {
            double diagonal = Double.parseDouble(w.getText().substring(0, 2));
            softAssert.assertTrue(diagonal >= diagonalFrom & diagonal <= diagonalTo);
        }
    }

    private void checkSpecialOfferPrice(double priceTo) {
        double specialOfferPrice = Double.parseDouble(SPECIAL_PRICE_OFFER.getText().replaceAll("[\\s.а-я]", "").replaceAll(",", "."));
        softAssert.assertTrue(specialOfferPrice <= priceTo);
    }

    private void checkPrices(double priceTo) {
        for (WebElement w : PRICES) {
            double prices = Double.parseDouble(w.getText().replaceAll("[\\s.а-я]", "").replaceAll(",", "."));
            System.out.println(prices);
            softAssert.assertTrue(prices <= priceTo);
        }
    }

    public void validationOfAllFilters(String manufature, String resolution, double price,String diagonalFrom, String diagonalTo){
        checkManufacturerFilterApplied(manufature);
        checkResolutionFilterApplied(resolution);
        checkIfManufacturerMatch(manufature);
        checkResolution(resolution);
        //checkPrices(price);
        //checkInches(diagonalFrom, diagonalTo);
        softAssert.assertAll();

    }

}
