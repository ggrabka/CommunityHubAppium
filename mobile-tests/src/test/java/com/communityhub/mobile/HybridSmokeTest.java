
package com.communityhub.mobile;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class HybridSmokeTest extends BaseTest{

    @Test
    void shouldSwitchBetweenNativeAndWebview() throws Exception {

        assertEquals("NATIVE_APP", driver.getContext());

        ContextManager contextManager = new ContextManager(driver);
        contextManager.switchToWebView();

        Boolean bodyPresent = new WebDriverWait(
                driver, Duration.ofSeconds(20))
                .until(d -> !d.findElements(
                        By.tagName("body")).isEmpty());

        assertTrue(bodyPresent, "WEBVIEW body not found");

       contextManager.switchToNative();
       assertEquals("NATIVE_APP", driver.getContext());
    }
}

