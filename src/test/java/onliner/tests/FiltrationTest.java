package onliner.tests;


import framework.BaseTest;
import onliner.pageObject.pages.CatalogPage;
import onliner.pageObject.pages.HomePage;
import onliner.pageObject.pages.TVPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class FiltrationTest extends BaseTest {

    @Test
    public void checkFiltration() throws InterruptedException {
        HomePage homePage = new HomePage();
        homePage.header.mainMenuNavigation("Каталог");

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.electronic_item_click("Электроника");
        catalogPage.tvandvideo_click("Телевидение");
        catalogPage.tv_click("Телевизоры");

        TVPage tvPage = new TVPage();
        tvPage.setManufacturer("Samsung");
        tvPage.setPriceFrom("от", "1000");
        tvPage.setPriceTo("до","2000");
        tvPage.setResolution("1920x1080 (Full HD)");
        tvPage.setDiagonalFrom("400");
        tvPage.setDiagonalTo("500");
        tvPage.sleep();
        tvPage.productSelection();
    }
}
