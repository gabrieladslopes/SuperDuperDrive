package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupLoginAccessTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testRedirectUnauthorizedAccess() throws InterruptedException {
        driver.get("http://localhost:" + port + "/home");
        assertEquals("http://localhost:" + port + "/login", driver.getCurrentUrl());

        driver.get("http://localhost:" + port + "/login");
        assertEquals("http://localhost:" + port + "/login", driver.getCurrentUrl());

        driver.get("http://localhost:" + port + "/signup");
        assertEquals("http://localhost:" + port + "/signup", driver.getCurrentUrl());
    }

    @Test
    public void testSignUpLoginAccess() throws InterruptedException {
        String firstName = "John";
        String lastName = "Robert";
        String username = "john";
        String password = "12345";

        driver.get("http://localhost:" + port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signUp(firstName, lastName, username, password);

        driver.get("http://localhost:" + port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        assertEquals("http://localhost:" + port + "/home", driver.getCurrentUrl());

        HomePage homePage = new HomePage(driver);
        homePage.logout();
        driver.get("http://localhost:" + port + "/home");

        assertEquals("http://localhost:" + port + "/login", driver.getCurrentUrl());
    }
}
