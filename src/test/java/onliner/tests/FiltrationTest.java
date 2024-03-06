package onliner.tests;


import framework.BaseTest;
import onliner.pageObject.pages.CatalogPage;
import onliner.pageObject.pages.HomePage;
import onliner.pageObject.pages.TVPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FiltrationTest extends BaseTest {
    @Test
    @Parameters({"manufacturer" , "resolution" , "priceTo", "diagonalFrom", "diagonalTo"})
    public void checkFiltration(String manufacturer, String resolution, double priceTo, double diagonalFrom, double diagonalTo) {
        HomePage homePage = new HomePage();
        homePage.header.mainMenuNavigation("Каталог");

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.electronic_item_click("Электроника");
        catalogPage.tvandvideo_click("Телевидение");
        catalogPage.tv_click("Телевизоры");

        TVPage tvPage = new TVPage();
        tvPage.setManufacturer(manufacturer);
        tvPage.setPriceTo("до",priceTo);
        tvPage.setResolution(resolution);
        tvPage.setDiagonalFrom(diagonalFrom+"\"");
        tvPage.setDiagonalTo(diagonalTo+"\"");
        tvPage.validationOfAllFilters(manufacturer,resolution,priceTo,diagonalFrom,diagonalTo);

    }
}
