package com.communityhub.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By userNameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.id("login-button");
    private final By loginMessage = By.id("login-message");


    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void enterUserName(String userName) {
        WebElement input = waitUntilVisible(userNameInput);
        input.clear();
        input.sendKeys(userName);
    }

    public void enterPassword(String password) {
        WebElement input = waitUntilVisible(passwordInput);
        input.clear();
        input.sendKeys(password);
    }

    public boolean isLoginButtonDisplayed() {
        WebElement button = waitUntilClickable(submitButton);
        return button.isDisplayed();
    }

    public boolean isLoginButtonInvisible() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(submitButton));
    }

    public void clickLoginButton() {
        waitUntilClickable(submitButton).click();
    }

    public String getLoginLogoutMessageText() {
        WebElement message = waitUntilVisible(loginMessage);
        return message.getText();
    }
}
