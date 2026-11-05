package com.communityhub.mobile;

import com.communityhub.mobile.pages.DashboardPage;
import com.communityhub.mobile.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LogoutTest extends BaseTest {

    @BeforeEach
    void setUpAuthenticatedUser() throws Exception {
        ContextManager contextManager = new ContextManager(driver);
        contextManager.switchToWebView();

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        loginPage.enterUserName("admin");
        loginPage.enterPassword("admin123");

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
