package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

  @BeforeAll
  static void setup() {
    Configuration.baseUrl = "https://demoqa.com";
    RestAssured.baseURI = "https://demoqa.com";

    Configuration.browser = System.getProperty("browser", "chrome");
    Configuration.browserVersion = System.getProperty("browserVersion", "127.0");

    String remoteUrl = System.getProperty("remoteUrl");
    if (remoteUrl != null && !remoteUrl.isBlank()) {
      Configuration.remote = remoteUrl;
    }

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("selenoid:options", Map.of(
      "enableVNC", true,
      "enableVideo", true
    ));
    Configuration.browserCapabilities = capabilities;
  }

  @BeforeEach
  void addAllureListener() {
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
    closeWebDriver();
  }
}
