package academy.softserve.edu.tests.administrator;

import academy.softserve.edu.domains.User;
import academy.softserve.edu.enums.Regions;
import academy.softserve.edu.enums.Roles;
import academy.softserve.edu.utils.DBHandler;
import academy.softserve.edu.utils.TestRunner;
import academy.softserve.edu.utils.TestUtil;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EditUserTest extends TestRunner {

    private static final String NEW_USER_LAST_NAME = "Tsoni";
    private static final String NEW_USER_PASSWORD = "1qaz2wsx";
    private static final String NEW_USER_EMAIL = "google@gmail.com";
    private static final Regions NEW_REGION = Regions.WEST;

    private User testUser;
    private int testUserId;

    @BeforeTest
    public final void createTestUser() {

        testUserId = TestUtil.createValidUserInDB();
        testUser = DBHandler.getUserById(testUserId);
    }

    @BeforeMethod
    public final void setUpTests() {

        userInfoPage = logInPage.logInAs(Roles.ADMINISTRATOR);

        administrationPage = userInfoPage
                .clickAdministrationTab()
                .clickLastUserPaginationButton();

        editUserPage = administrationPage.clickEditUserById(testUserId);
    }

    @Test
    public final void testAreEditFieldsFilledCorrectly() {

        Assert.assertTrue(editUserPage
                        .getIdentificationOfEditUserPage()
                        .isDisplayed(),
                "Page is not switched to EditUserPage !");

        Assert.assertTrue(editUserPage
                        .getFirstNameTextField()
                        .getAttribute("value")
                        .equals(testUser.getFirstName()),
                "User First name does not equals to actual !");

        Assert.assertTrue(editUserPage
                        .getLastNameTextField()
                        .getAttribute("value")
                        .equals(testUser.getLastName()),
                "User Last name does not equal to actual !");

        Assert.assertTrue(editUserPage
                        .getNewPasswordTextField()
                        .getAttribute("value")
                        .isEmpty(),
                "Password field is not empty !");

        Assert.assertTrue(editUserPage
                        .getConfirmPasswordTextField()
                        .getAttribute("value")
                        .isEmpty(),
                "Confirm password field is not empty !");

        Assert.assertTrue(editUserPage
                        .getEmailAddressTextField()
                        .getAttribute("value")
                        .equals(testUser.getEmail()),
                "User Email does not equal to actual !");

        Assert.assertTrue(new Select(editUserPage
                        .getRoleDropdown())
                        .getFirstSelectedOption()
                        .getText()
                        .equalsIgnoreCase(testUser.getRoleName()),
                "User Role does not equal to actual !");

        Assert.assertTrue(new Select(editUserPage
                        .getRegionDropdown())
                        .getFirstSelectedOption()
                        .getText()
                        .equalsIgnoreCase(testUser.getRegionName()),
                "User Region does not equal to actual !");
    }

    @Test
    public final void testEditUserAndClickSave() {

        Assert.assertTrue(editUserPage
                        .getIdentificationOfEditUserPage()
                        .isDisplayed(),
                "Page is not switched to EditUserPage !");

        editUserPage
                .getLastNameTextField()
                .clear();
        editUserPage
                .getLastNameTextField()
                .sendKeys(NEW_USER_LAST_NAME);
        editUserPage
                .getNewPasswordTextField()
                .sendKeys(NEW_USER_PASSWORD);
        editUserPage
                .getConfirmPasswordTextField()
                .sendKeys(NEW_USER_PASSWORD);
        editUserPage
                .getRegionDropdown()
                .sendKeys(NEW_REGION.toString());
        editUserPage
                .clickSaveChangesButton();

        testUser = DBHandler.getUserById(testUserId);

        Assert.assertTrue(testUser
                        .getLastName()
                        .equals(NEW_USER_LAST_NAME),
                "Last name does not equal to changed one");

        Assert.assertTrue(testUser
                        .getPassword()
                        .equals(NEW_USER_PASSWORD),
                "User password does not equal to changed one");

        Assert.assertTrue(testUser
                        .getRegionName()
                        .equalsIgnoreCase(NEW_REGION.toString()),
                "User Region does not equal to changed one");

        Assert.assertTrue(administrationPage
                        .identificationOfAdministratorPage()
                        .isDisplayed(),
                "Page is not returned to AdministratorPage after saving changes!");
    }

    @Test
    public final void testEditUserAndClickCancel() {

        Assert.assertTrue(editUserPage
                        .getIdentificationOfEditUserPage()
                        .isDisplayed(),
                "Page is not switched to EditUserPage !");

        editUserPage.getEmailAddressTextField().clear();
        editUserPage.getEmailAddressTextField().sendKeys(NEW_USER_EMAIL);
        editUserPage.clickCancelButton();

        Assert.assertTrue(DBHandler
                        .getLastUser()
                        .equals(testUser),
                "User is unexpected changed !");

        Assert.assertTrue(administrationPage
                        .identificationOfAdministratorPage()
                        .isDisplayed(),
                "Page is not returned to AdministratorPage after saving changes!");
    }

    @AfterTest
    public final void deleteTestUser() {
        DBHandler.deleteUser(testUserId);
    }

}
