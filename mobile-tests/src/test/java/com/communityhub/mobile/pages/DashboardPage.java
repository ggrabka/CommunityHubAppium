package com.communityhub.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {




    private final By welcomeTextMessage = By.id("welcome-message");
    private final By logoutButton = By.id("logout-button");

    public DashboardPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isWelcomeMessageDisplayed() {
        return waitUntilVisible(welcomeTextMessage)
                .isDisplayed();
    }

    public boolean isLogoutButtonDisplayed() {
        return waitUntilVisible(logoutButton)
                .isDisplayed();
    }

    public boolean isLogoutButtonInvisible() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(logoutButton));
    }

    public void clickLogoutButton() {
        waitUntilClickable(logoutButton).click();
    }
}
