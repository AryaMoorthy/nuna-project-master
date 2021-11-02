
package GoogleTranslatorTests.Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import java.util.List;

/**
 *  Selenium helper convenience methods
 */

public class SeleniumHelper
{
    private static final Logger _logger = Logger.getLogger(SeleniumHelper.class);
    private WebDriver _webDriver;
    private WebDriverWait _wait;

    SeleniumHelper(WebDriver driver)
    {
        this._webDriver = driver;
        _wait = new WebDriverWait(driver, 30000);
    }

    void click(By by)
    {
        WebElement element = getElementBy(by);
        _logger.debug("Clicking locator: $by");
        WebElement preparedElement = _wait.until(ExpectedConditions.elementToBeClickable(element));
        preparedElement.click();
    }

    boolean isElementEnabled(By by)
    {
        List<WebElement> elements = _webDriver.findElements(by);
        boolean isEnabled = (elements.size() >= 1) && elements.get(0).isEnabled();
        return isEnabled;
    }

     void sendKeys(By by, String text)
    {
        WebElement element = getElementBy(by);
        waitUntilElementVisible(by);
        Assert.assertTrue(isElementEnabled(by), "Element is visible");
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }

    void pressTabKey(By by)
    {
        WebElement element = getElementBy(by);
        element.sendKeys(Keys.TAB);
    }

    void clearInputBox(By by)
    {
        WebElement inputBoxElement = getElementBy(by);
        inputBoxElement.clear();
    }

    String getText(By by)
    {
        WebElement element = getElementBy(by);
        _logger.debug("Getting text from locator: $by");
        return element.getText();
    }

    WebElement getElementBy(By by)
    {

        return _wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    void waitUntilElementVisible(By by)
    {
        _wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    void closeWindow()
    {
        _webDriver.close();
    }

    WebDriverWait getWait()
    {
        return _wait;
    }

    void setWait(WebDriverWait wait)
    {
        _wait = wait;
    }
}


