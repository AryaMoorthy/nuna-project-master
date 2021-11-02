package GoogleTranslatorTests.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTranslatorPageObjects {

    SeleniumHelper _seleniumHelper;

    public static final By SOURCE_LANGUAGE_DROPDOWN = By.xpath("//button[@aria-label='More source languages']");
    public static final By SEARCH_SOURCE_LANGUAGE_TEXT_BOX = By.xpath("//input[@aria-label='Search languages']");
    public static final By SOURCE_TEXT_AREA= By.xpath("//textarea[@aria-label='Source text']");

    public static final By TARGET_LANGUAGE_DROPDOWN = By.id("i13");
    public static final By TARGET_LANGUAGE_RESULTS= By.xpath("//div[@data-result-index='0']/div/span/span");

    public static final By SWAP_LANGUAGES= By.xpath("//button[starts-with(@aria-label,'Swap languages')]");

    public static final By KEY_BOARD= By.xpath("//a[@aria-label='Show the Input Tools menu']");
    public static final By KEYBOARD_DROP_DOWN_FIRST_ITEM = By.xpath("//ul[@class = 'ita-kd-dropdown-menu']/li[1]/span");
    public static final By SHIFT_KEY= By.id("K16");
    public static final By LETTER_H= By.id("K72");
    public static final By LETTER_I = By.id("K73");
    public static final By EXCLAMATION_KEY = By.id("K49");
    public static final By KEYBOARD_CLOSE = By.xpath("//div[@id = 'kbd']/div/div[2]");

    public GoogleTranslatorPageObjects(WebDriver webDriver) {
        this._seleniumHelper = new SeleniumHelper(webDriver);
    }

    public void clickSourceLanguageDropdown()
    {
        _seleniumHelper.click(SOURCE_LANGUAGE_DROPDOWN);
    }

    public void sendLanguageToSourceSearchInputBox(String language)
    {
        _seleniumHelper.click(SEARCH_SOURCE_LANGUAGE_TEXT_BOX);
        _seleniumHelper.sendKeys(SEARCH_SOURCE_LANGUAGE_TEXT_BOX, language);
    }

    public void sendLanguageToSourceTextArea(String text)
    {
        _seleniumHelper.click(SOURCE_TEXT_AREA);
        _seleniumHelper.sendKeys(SOURCE_TEXT_AREA, text);
    }

    public void clickTargetLanguageDropdown()
    {
        _seleniumHelper.click(TARGET_LANGUAGE_DROPDOWN);
    }

    public String getTextFromTargetResultsTextArea()
    {
       return  _seleniumHelper.getText(TARGET_LANGUAGE_RESULTS);
    }

    public void clickSwapLanguagesButton() throws InterruptedException {
        getWebdriverWait().until(ExpectedConditions.elementToBeClickable(SWAP_LANGUAGES));
        _seleniumHelper.click(SWAP_LANGUAGES);
        Thread.sleep(4000);
    }

    public void clearTextInSourceTextArea()
    {
        _seleniumHelper.clearInputBox(SOURCE_TEXT_AREA);
    }

    public void clickKeyBoard()
    {
        _seleniumHelper.click(KEY_BOARD);
    }

    public void selectFirstItemFromKeyBoardDropDown()
    {
        _seleniumHelper.click(KEYBOARD_DROP_DOWN_FIRST_ITEM);
    }

    public void typeHiUsingOnlineKeyBoard() throws InterruptedException {
        _seleniumHelper.click(SHIFT_KEY);
        _seleniumHelper.click(LETTER_H);
        _seleniumHelper.click(LETTER_I);
        _seleniumHelper.click(SHIFT_KEY);
        Thread.sleep(1000);
        _seleniumHelper.click(EXCLAMATION_KEY);
    }

    public void closeKeyBoard()
    {
        _seleniumHelper.click(KEYBOARD_CLOSE);
        _seleniumHelper.pressTabKey(SOURCE_TEXT_AREA);
    }

    public WebDriverWait getWebdriverWait() {
        return _seleniumHelper.getWait();
    }

    public void closeCurrentWindow() {
        _seleniumHelper.closeWindow();
    }

}
