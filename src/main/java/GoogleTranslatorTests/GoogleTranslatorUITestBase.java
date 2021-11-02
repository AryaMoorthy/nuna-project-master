package GoogleTranslatorTests;


import GoogleTranslatorTests.Utils.Constants;
import GoogleTranslatorTests.Utils.GoogleTranslatorPageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.log4testng.Logger;

import java.util.Random;

public class GoogleTranslatorUITestBase {
    public WebDriver _firefoxDriver;
    static Constants _constants;
    static Logger _logger = Logger.getLogger(GoogleTranslatorUITestBase.class);
    Random _random;
    public GoogleTranslatorPageHelper _googleTranslatorPageHelper;

    @BeforeClass
    public void init() {
        _firefoxDriver = new FirefoxDriver();
        _constants = new Constants();
        _random = new Random();
        _googleTranslatorPageHelper = new GoogleTranslatorPageHelper();
        _googleTranslatorPageHelper.launchGoogleTranslatorPage(_firefoxDriver);

        //implement any other generic initialization details here.
    }
}
