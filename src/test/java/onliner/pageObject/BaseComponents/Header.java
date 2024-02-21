package onliner.pageObject.BaseComponents;

import framework.elements.Label;
import org.openqa.selenium.By;

public class Header {
    private static final String NAV_MENU_ITEM = "//span[@class='b-main-navigation__text' and text()='%s']";

    public void mainMenuNavigation(String item){
        Label lblNavMenuSection = new Label(By.xpath(String.format(NAV_MENU_ITEM,item)));
        lblNavMenuSection.clickAndWait();

    }
}
