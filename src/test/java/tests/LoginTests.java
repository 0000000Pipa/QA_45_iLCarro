package tests;

import dto.UserDtoLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;

public class LoginTests extends ApplicationManager {

    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        new SearchPage(getDriver()).clickBtnLogin();
    }

    @Test
    public void loginPositiveTest() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("alexmed123@gmail.com")
                .password("Qwerty123!")
                .build();
        loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isPopUpLoginMessagePresent("Logged in success"));
    }
    @Test
    public void loginNegativeTest_wrongEmail() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("alexmed123@gmail.")
                .password("Qwerty123!")
                .build();
        loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.validateErrorMessage("It'snot look like email"));
    }
@Test
    public void loginNegativeTest_emptyPassword() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("alexmed123@gmail.com")
                .password("")
                .build();
        loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.validateErrorMessage("Password is required"));

    }


}
