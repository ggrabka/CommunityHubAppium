package com.communityhub.mobile;

import com.communityhub.mobile.pages.DashboardPage;
import com.communityhub.mobile.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LogoutTest extends BaseTest {

    @BeforeEach
    void setUpAuthenticatedUser() throws Exception {
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

        assertTrue(dashboardPage.isLogoutButtonDisplayed()
                ,"Precondition failed: User was not authenticated");
    }

    @Test
    void shouldLogoutSuccessfully() throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.clickLogoutButton();

        assertTrue(loginPage.isLoginButtonDisplayed(),
                "Login button should be visible after logout");
        assertTrue(dashboardPage.isLogoutButtonInvisible(),
                "Logout button should not be visible after Logout");

        assertEquals(
                "You have been logged out.",
                loginPage.getLoginLogoutMessageText()
        );
    }
}
