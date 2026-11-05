package com.communityhub.mobile;

import com.communityhub.mobile.pages.DashboardPage;
import com.communityhub.mobile.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {

    @Test
    void shouldLoginSuccessfully() throws Exception {

        ContextManager contextManager = new ContextManager(driver);
        contextManager.switchToWebView();

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        loginPage.enterUserName("admin");
        loginPage.enterPassword("admin123");

        loginPage.clickLoginButton();

        assertTrue(
                dashboardPage.isLogoutButtonDisplayed(),
                "Logout button should be visible after successful login"
        );
        assertTrue(loginPage.isLoginButtonInvisible());

    }

    @Test
    void shouldRejectLoginWithInvalidPassword() throws Exception {

        ContextManager contextManager = new ContextManager(driver);
        contextManager.switchToWebView();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUserName("admin");
        loginPage.enterPassword("admin123456");

        loginPage.clickLoginButton();

        assertEquals(
                "Invalid username or password",
                loginPage.getLoginLogoutMessageText()
        );
    }
}