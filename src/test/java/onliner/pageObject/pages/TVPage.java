package onliner.pageObject.pages;

import framework.BasePage;
import framework.elements.CheckBox;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;

import static framework.Browser.waitForPageLoad;

public class TVPage extends BasePage {
    private static final String PAGE_LOCATOR ="//h1";
    private static final String MANUFACTURER ="//div[@class='catalog-form__checkbox-sign' and text()='%s']/../../div[@class='i-checkbox__faux']";
    private static final String PRICE_FROM ="//input[@placeholder='%s']";
    private static final String PRICE_TO ="//input[@placeholder='%s']";
    private static final String RESOLUTION ="//*[@id=\"container\"]/div/div/div/div/div[2]/div[1]/div/div/div[3]/div/div[2]/div[2]/div[16]/div/div[2]/div[2]/div/ul/li[3]/label/div/div[1]";
    private static final String DIAGONAL_FROM ="//option[@value = '%s']";
    private static final String DIAGONAL_TO ="//*[@id=\"container\"]/div/div/div/div/div[2]/div[1]/div/div/div[3]/div/div[2]/div[2]/div[15]/div/div[2]/div[2]/div[2]/div/div[2]/div/select/option[@value='%s']";
    public TVPage() {
        super(By.xpath(PAGE_LOCATOR), "TV page");
    }

    public void setManufacturer(String item){
        CheckBox manufacturerCB  = new CheckBox(By.xpath(String.format(MANUFACTURER,item)));
        manufacturerCB.moveAndClickByAction();
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
        CheckBox resol = new CheckBox(By.xpath(String.format(RESOLUTION, item)));
        resol.click();
    }

    public void setDiagonalFrom(String item){
        Label diagonalFrom  = new Label(By.xpath(String.format(DIAGONAL_FROM,item)));
        diagonalFrom.click();
    }
    public void setDiagonalTo(String item){
        Label diagonalTo  = new Label(By.xpath(String.format(DIAGONAL_TO,item)));
        diagonalTo.click();
    }



}
