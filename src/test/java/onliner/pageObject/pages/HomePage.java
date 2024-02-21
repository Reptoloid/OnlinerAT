package onliner.pageObject.pages;
import org.openqa.selenium.By;

public class HomePage extends BaseOnlinerPage {

    public static final String PAGE_LOCATOR ="//img[@class='onliner_logo']";
    public HomePage() {
        super(By.xpath(PAGE_LOCATOR), "Onliner page ");
    }
}
