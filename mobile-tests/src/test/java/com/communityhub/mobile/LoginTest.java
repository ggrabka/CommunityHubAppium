package com.communityhub.mobile;

import com.communityhub.mobile.pages.DashboardPage;
import com.communityhub.mobile.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {

    @Test
    void shouldLoginSuccessfully() throws Exception {

        // 1. Switch to WebView
        ContextManager contextManager = new ContextManager(driver);
        contextManager.switchToWebView();

        // 2. Create LoginPage using existing driver
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        // 3. Enter credentials
        loginPage.enterUserName("admin");
        loginPage.enterPassword("admin123");

        // 4. Click login
        loginPage.clickLoginButton();

        // 5. Verify successful authentication
        assertTrue(
                dashboardPage.isLogoutButtonDisplayed(),
                "Logout button should be visible after successful login"
        );
        assertTrue(loginPage.isLoginButtonInvisible());

    }

    @Test
    void shouldRejectLoginWithInvalidPassword() throws Exception {

        // 1. Switch to WebView
        ContextManager contextManager = new ContextManager(driver);
        contextManager.switchToWebView();

        // 2. Create LoginPage using existing driver
        LoginPage loginPage = new LoginPage(driver);

        // 3. Enter credentials
        loginPage.enterUserName("admin");
        loginPage.enterPassword("admin123456");

        // 4. Click login
        loginPage.clickLoginButton();

        // 5. Verify successful authentication
        assertEquals(
                "Invalid username or password",
                loginPage.getLoginLogoutMessageText()
        );
    }
}