package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTest {

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
            driver.quit();
        }
    }

    @Test
    public void testNoteCreation() {

        String title = "Title test 1";
        String description = "Description test 1";

        NotesPage notePage = new NotesPage(driver);
        notePage.createNewNote(driver, title, description);

        assertTrue(notePage.noteExists(driver, title, description));

        title = "Title test 2";
        description = "Description test 2";

        notePage = new NotesPage(driver);
        notePage.createNewNote(driver, title, description);

        assertTrue(notePage.noteExists(driver, title, description));
    }

    @Test
    public void testNoteEdition() {

        String title = "Title test 1";
        String description = "Description test 1";

        NotesPage notePage = new NotesPage(driver);
        notePage.createNewNote(driver, title, description);

        String newTitle = "New title";
        String newDescription = "New description";

        assertFalse(notePage.noteExists(driver, newTitle, newDescription));

        notePage.editNote(driver, newTitle, newDescription);
        assertTrue(notePage.noteExists(driver, newTitle, newDescription));
    }

    @Test
    public void testNoteDeletion() {

        String title = "Title test";
        String description = "Description test";

        NotesPage notesPage = new NotesPage(driver);
        notesPage.createNewNote(driver, title, description);

        assertTrue(notesPage.noteExists(driver, title, description));

        notesPage.deleteNote(driver, title, description);
        assertFalse(notesPage.noteExists(driver, title, description));
    }
}

