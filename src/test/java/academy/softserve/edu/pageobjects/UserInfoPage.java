package academy.softserve.edu.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserInfoPage extends PageObject {

    public static final By ADMINISTRATION_LINK =
            By.xpath("//ul[@id='nav']/descendant::a[@href='users.htm']");
    public static final By ITEM_MANAGEMENT_LINK =
            By.xpath("//ul[@id='nav']/descendant::a[@href='itemManagement.htm']");
    public static final By CUSTOMER_ORDERING_LINK =
            By.xpath("//ul[@id='nav']/descendant::a[@href='order.htm']");
    public static final By MERCHANDISER_ORDERING_LINK =
            By.xpath("//ul[@id='nav']/descendant::a[@href='order.htm']");
    public static final By VISITOR_EXTRA_LINK =
            By.xpath("//ul[@id='nav']//a[1]");
    public static final String USER_INFO_PAGE_URL =
            "http://192.168.56.101:8080/oms5/userInfo.htm";
    private final String info[] = {"//tr[1]/td[2]", "//tr[2]/td[2]", "//tr[3]/td[2]", "//tr[4]/td[2]"};

    public UserInfoPage(final WebDriver driver) {
        super(driver);
    }

    public final AdministrationPage clickAdministrationTab() {
        driver.findElement(ADMINISTRATION_LINK).click();
        return new AdministrationPage(driver);
    }

    public final CustomerOrderingPage clickCustomerOrderingTab() {
        driver.findElement(CUSTOMER_ORDERING_LINK).click();
        return new CustomerOrderingPage(driver);
    }

    public final ItemManagementPage clickItemManagementTab() {
        driver.findElement(ITEM_MANAGEMENT_LINK).click();
        return new ItemManagementPage(driver);
    }

    public final MerchandiserOrderingPage clickMerchandiserOrderingTab() {
        driver.findElement(MERCHANDISER_ORDERING_LINK).click();
        return new MerchandiserOrderingPage(driver);
    }
    public String[] fieldNotEmpty() {
        String [] getElements = new String[info.length];
        for (int i = 0; i<info.length;i++) {
          getElements[i] =  driver.findElement(By.xpath(info[i])).getText();
        }
        return getElements;
    }

}