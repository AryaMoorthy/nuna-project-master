package GoogleTranslatorTests.Utils;

import GoogleTranslatorTests.GoogleTranslatorUITestBase;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoogleTranslatorPageHelper {

    Constants _constants = new Constants();
    static Logger _logger = Logger.getLogger(GoogleTranslatorUITestBase.class);


    public void launchGoogleTranslatorPage(WebDriver webdriver) {
        _logger.info("Launch google translator page");
        webdriver.get(_constants.LAUNCH_GOOGLE_TRANSLATE_PAGE);

        try {
            Assert.assertEquals(_constants.LAUNCH_GOOGLE_TRANSLATE_PAGE, webdriver.getCurrentUrl());
            _logger.info("Page launched as expected");
        } catch (Throwable pageNavigationError) {
            _logger.info("Didn't navigate to correct webpage, instead navigated to: " + webdriver.getCurrentUrl());
        }
    }

    public String returnTextForCorrespondingLanguage(String language) throws FileNotFoundException
    {
        Objects.requireNonNull(language);
        StringBuilder textInCorrespondingLanguage = new StringBuilder();

        JsonObject jsonObject = (JsonObject) new JsonParser()
                .parse(new FileReader(new File("src/main/resources/languageTranslation.json").getAbsolutePath()));

        JsonArray jsonArray = jsonObject.getAsJsonArray("languages");
        List<JsonElement> jsonElementsList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size() ; i++)
        {
            jsonElementsList.add(jsonArray.get(i));
        }
        for(JsonElement lan : jsonElementsList)
        {
            if(lan.getAsJsonObject().get("language").getAsString().equals(language))
            {
                textInCorrespondingLanguage.append(lan.getAsJsonObject().get("text").getAsString());
            }
        }
        return textInCorrespondingLanguage.toString();
    }
}
