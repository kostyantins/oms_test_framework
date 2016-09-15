package academy.softserve.edu.tests.merchandiser;

import academy.softserve.edu.pageobjects.LogInPage;
import academy.softserve.edu.pageobjects.UserInfoPage;
import academy.softserve.edu.utils.DataProviders;
import academy.softserve.edu.utils.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class TestMerchandiserInfoDisplayed extends TestRunner {

    @Test(dataProvider = "testDataForMerchandiser", dataProviderClass = DataProviders.class)
    public void testMerchandiserInfoDisplayed(final String userName, final String userPassword) {
        loginPage = new LogInPage(driver);
        loginPage.doLogIn(userName, userPassword);
        Assert.assertTrue(!driver.findElement(UserInfoPage.FIRST_NAME_VALUE).getText().isEmpty() &&
                        !driver.findElement(UserInfoPage.LAST_NAME_VALUE).getText().isEmpty() &&
                        !driver.findElement(UserInfoPage.CUSTOMER_TYPE_VALUE).getText().isEmpty() &&
                        !driver.findElement(UserInfoPage.ROLE_VALUE).getText().isEmpty(),
                "\nFirst Name = " + driver.findElement(UserInfoPage.FIRST_NAME_VALUE).getText() +
                        "\nLast Name = " + driver.findElement(UserInfoPage.LAST_NAME_VALUE).getText() +
                        "\nCustomer Type = " + driver.findElement(UserInfoPage.CUSTOMER_TYPE_VALUE).getText() +
                        "\nRole = " + driver.findElement(UserInfoPage.ROLE_VALUE).getText() + "\n");
    }
}