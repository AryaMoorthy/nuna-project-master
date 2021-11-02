
package GoogleTranslatorTests;

import GoogleTranslatorTests.Utils.Constants;
import GoogleTranslatorTests.Utils.GoogleTranslatorPageHelper;
import GoogleTranslatorTests.Utils.GoogleTranslatorPageObjects;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class GoogleTranslatorUITests extends GoogleTranslatorUITestBase {

    GoogleTranslatorPageObjects _googleTranslatorPageObjects;
    GoogleTranslatorPageHelper _googleTranslatorPageHelper;
    Constants _constants;

    @BeforeClass
    public void setUp() {
        _googleTranslatorPageHelper = new GoogleTranslatorPageHelper();
        _googleTranslatorPageObjects = new GoogleTranslatorPageObjects(_firefoxDriver);
        _constants = new Constants();
    }

    @Test (description = "Verify a text in German is transaltaed to Spanish")
    void verifyGermanTextIsTranslatedToSpanish() throws FileNotFoundException {
        _logger.info("Click Source language drop down and select " + _constants.SOURCE_LANGUAGE);
        _googleTranslatorPageObjects.clickSourceLanguageDropdown();
        _googleTranslatorPageObjects.sendLanguageToSourceSearchInputBox(_constants.SOURCE_LANGUAGE);

        _logger.info("Get the text to be translated from languageTranslation.json and send to source text area");
        String sourceTextToBeTranslated = _googleTranslatorPageHelper.returnTextForCorrespondingLanguage(_constants.SOURCE_LANGUAGE);
        _googleTranslatorPageObjects.sendLanguageToSourceTextArea(sourceTextToBeTranslated);

        _logger.info("Verify the text in " + _constants.SOURCE_LANGUAGE + "is translated to corresponding target language: " + _constants.TARGET_LANGUAGE);
        _googleTranslatorPageObjects.clickTargetLanguageDropdown();
        Assert.assertEquals(_googleTranslatorPageObjects.getTextFromTargetResultsTextArea(),
                _googleTranslatorPageHelper.returnTextForCorrespondingLanguage(_constants.TARGET_LANGUAGE),
                "Language translation from source to target was not as expected");
    }

    @Test(description = "Verify Swap language button using German and Spanish", dependsOnMethods = "verifyGermanTextIsTranslatedToSpanish")
    void verifySwapLanguageButton() throws FileNotFoundException, InterruptedException {
        _logger.info("Get the current text in target text area and click language swap button");
        String targetTextBeforeSwap = _googleTranslatorPageObjects.getTextFromTargetResultsTextArea();
        _googleTranslatorPageObjects.clickSwapLanguagesButton();

        _logger.info("Verify target language has changed and expected text is in the target area");
        String expectedTargetTextAfterSwap = _googleTranslatorPageHelper.returnTextForCorrespondingLanguage(_constants.SOURCE_LANGUAGE);
        Assert.assertNotEquals(_googleTranslatorPageObjects.getTextFromTargetResultsTextArea(), targetTextBeforeSwap,
                "Assert text in the target area has changed");
        Assert.assertEquals(_googleTranslatorPageObjects.getTextFromTargetResultsTextArea(), expectedTargetTextAfterSwap,
                "Verify target text is as expected");
    }

    @Test(description = "Verify a text in German is transaltaed to Spanish", dependsOnMethods = "verifySwapLanguageButton")
    void verifyUserCanClearExistingContentsAndTypeNewTextUsingOnlineKeyBoard() {
        _logger.info("Clear the source text area and open the online keyboard");
        _googleTranslatorPageObjects.clearTextInSourceTextArea();
        _googleTranslatorPageObjects.clickKeyBoard();
        _googleTranslatorPageObjects.selectFirstItemFromKeyBoardDropDown();

        _logger.info("Type " + _constants.EXPECTED_NEW_TEXT_TYPED_USING_KEYBOARD + "using keyboard and close keyboard");
        _googleTranslatorPageObjects.typeHiUsingOnlineKeyBoard();
        _googleTranslatorPageObjects.closeKeyBoard();

        _logger.info("Verify above typed text is present in the target area");
        Assert.assertEquals(_googleTranslatorPageObjects.getTextFromTargetResultsTextArea(), _constants.EXPECTED_NEW_TEXT_TYPED_USING_KEYBOARD);
    }

    @AfterClass
    void closeBrowserWindow() {
        _googleTranslatorPageObjects.closeCurrentWindow();
    }

}
