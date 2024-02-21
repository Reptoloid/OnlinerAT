package onliner.pageObject.pages;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;

public class CatalogPage extends BasePage {

    private static final String PAGE_LOCATOR ="//div[@class='catalog-navigation__title']";
    private static final String ELECTRONICS_BUTTON = new String("//span[@class='catalog-navigation-classifier__item-title-wrapper' and text()='%s']");
    private static final String TVANDVIDEO_BUTTON = new String("//div[@class='catalog-navigation-list__aside-title' and contains(text(),'%s')]");
    private static final String TV_BUTTON = new String("//a[@href='https://catalog.onliner.by/tv']//span[@class='catalog-navigation-list__dropdown-title' and text()=' %s ']");


    public CatalogPage() {
        super(By.xpath(PAGE_LOCATOR), "Catalog list");
    }

    public void electronic_item_click(String item){
        Label lblNavCatalogSection = new Label(By.xpath(String.format(ELECTRONICS_BUTTON,item)));
        lblNavCatalogSection .click();
    }

    public void tvandvideo_click(String item){
        Label lblNavCatalogSection  = new Label(By.xpath(String.format(TVANDVIDEO_BUTTON,item)));
        lblNavCatalogSection .click();
    }
    public void tv_click(String item){
        Label lblNavCatalogSection  = new Label(By.xpath(String.format(TV_BUTTON,item)));
        lblNavCatalogSection .click();
    }
}
