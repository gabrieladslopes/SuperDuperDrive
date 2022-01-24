package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.page_objects.CredentialsPage;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTest {

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
    }

    @AfterEach
    public void afterAll() {
        if(this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void testCredentialCreationDisplayedAndPasswordEncrypted() throws InterruptedException {

        for(int i = 0; i < 5; i++) {
            String url = "www.urlteste" + i + ".com";
            String username = "John" + i;
            String password = "john" + i;

            CredentialsPage credentialsPage = new CredentialsPage(driver);
            credentialsPage.createNewCredential(driver, url, username, password);

            driver.get("http://localhost:" + port + "/home");
            assertTrue(credentialsPage.credentialExists(driver, url, username, password));

            Thread.sleep(5000);
        }
    }

    @Test
    public void testCredentialViewEditAndVerifyChanges() throws InterruptedException {

        String url = "www.urlteste.com";
        String username = "John";
        String password = "john";

        CredentialsPage credentialsPage = new CredentialsPage(driver);
        credentialsPage.createNewCredential(driver, url, username, password);

        driver.get("http://localhost:" + port + "/home");

        String encryptedPassword = credentialsPage.getEncryptedPassword(driver, url, username);

        String newPassword = "john1";
        credentialsPage.editPasswordCredential(driver, url, username, newPassword);

        driver.get("http://localhost:" + port + "/home");
        String newEncryptedPassword = credentialsPage.getEncryptedPassword(driver, url, username);

        assertNotEquals(encryptedPassword, newEncryptedPassword);
    }

    @Test
    public void testCredentialDeletion() {

        CredentialsPage credentialsPage = new CredentialsPage(driver);

        String url = "www.urlteste.com";
        String username = "John";
        String password = "john";

        driver.get("http://localhost:" + port + "/home");
        credentialsPage.createNewCredential(driver, url, username, password);

        driver.get("http://localhost:" + port + "/home");
        credentialsPage.deleteCredential(driver, url, username);

        driver.get("http://localhost:" + port + "/home");
        assertFalse(credentialsPage.credentialExists(driver, url, username, password));
    }
}
