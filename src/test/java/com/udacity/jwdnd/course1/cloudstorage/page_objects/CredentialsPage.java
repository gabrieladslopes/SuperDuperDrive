package com.udacity.jwdnd.course1.cloudstorage.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CredentialsPage {

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "buttonNewCredential")
    private WebElement buttonNewCredential;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "buttonSaveChangesCredentials")
    private WebElement buttonSaveChanges;

    @FindBy(id = "credentialTable")
    private WebElement table;

    public CredentialsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createNewCredential(WebDriver driver, String url, String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab));
        this.credentialsTab.click();

        wait.until(ExpectedConditions.elementToBeClickable(buttonNewCredential));
        this.buttonNewCredential.click();

        wait.until(ExpectedConditions.visibilityOf(credentialUrl));
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.sendKeys(password);
        this.buttonSaveChanges.click();
    }

    public boolean credentialExists(WebDriver driver, String url, String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab));
        this.credentialsTab.click();

        List<WebElement> credentialTableRows = table.findElements(By.className("credentialTableRow"));

        for (WebElement credentialTableRow : credentialTableRows) {
            WebElement urlElement = credentialTableRow.findElement(By.className("classCredentialUrl"));
            wait.until(ExpectedConditions.visibilityOf(urlElement));

            WebElement usernameElement = credentialTableRow.findElement(By.className("classCredentialUsername"));
            wait.until(ExpectedConditions.visibilityOf(usernameElement));

            WebElement passwordElement = credentialTableRow.findElement(By.className("classCredentialPassword"));
            wait.until(ExpectedConditions.visibilityOf(passwordElement));

            if(urlElement.getText().equals(url) &&
                    usernameElement.getText().equals(username) &&
                    !passwordElement.getText().equals(password)) return true;
        }

        return false;
    }

    public String getEncryptedPassword(WebDriver driver, String url, String username) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        String encryptedPassword = "";

        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab));
        this.credentialsTab.click();

        List<WebElement> credentialTableRows = table.findElements(By.className("credentialTableRow"));

        for (WebElement credentialTableRow : credentialTableRows) {
            WebElement urlElement = credentialTableRow.findElement(By.className("classCredentialUrl"));
            wait.until(ExpectedConditions.visibilityOf(urlElement));

            WebElement usernameElement = credentialTableRow.findElement(By.className("classCredentialUsername"));
            wait.until(ExpectedConditions.visibilityOf(usernameElement));

            WebElement passwordElement = credentialTableRow.findElement(By.className("classCredentialPassword"));
            wait.until(ExpectedConditions.visibilityOf(passwordElement));

            if(urlElement.getText().equals(url) &&
                    usernameElement.getText().equals(username)) {
                encryptedPassword = passwordElement.getText();
            }
        }
        return encryptedPassword;
    }

    public void editPasswordCredential(WebDriver driver, String url, String username, String newPassword) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab));
        this.credentialsTab.click();

        List<WebElement> credentialTableRows = table.findElements(By.className("credentialTableRow"));

        for (WebElement credentialTableRow : credentialTableRows) {

            WebElement urlElement = credentialTableRow.findElement(By.className("classCredentialUrl"));
            wait.until(ExpectedConditions.visibilityOf(urlElement));

            WebElement usernameElement = credentialTableRow.findElement(By.className("classCredentialUsername"));
            wait.until(ExpectedConditions.visibilityOf(usernameElement));

            if(urlElement.getText().equals(url) && usernameElement.getText().equals(username)) {
                WebElement editButton = credentialTableRow.findElement(By.tagName("button"));
                wait.until(ExpectedConditions.elementToBeClickable(editButton));
                editButton.click();

                wait.until(ExpectedConditions.visibilityOf(credentialPassword));
                this.credentialPassword.clear();
                this.credentialPassword.sendKeys(newPassword);
                this.buttonSaveChanges.click();
            }
        }
    }

    public void deleteCredential(WebDriver driver, String url, String username) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab));
        this.credentialsTab.click();

        List<WebElement> credentialTableRows = table.findElements(By.className("credentialTableRow"));

        for (WebElement credentialTableRow : credentialTableRows) {
            WebElement urlElement = credentialTableRow.findElement(By.className("classCredentialUrl"));
            wait.until(ExpectedConditions.visibilityOf(urlElement));
            String urlElementText = urlElement.getText();

            WebElement usernameElement = credentialTableRow.findElement(By.className("classCredentialUsername"));
            wait.until(ExpectedConditions.visibilityOf(usernameElement));
            String usernameElementText = usernameElement.getText();

            if(urlElementText.equals(url) && usernameElementText.equals(username)) {
                WebElement deleteButton = credentialTableRow.findElement(By.tagName("a"));
                wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
                deleteButton.click();
            }
        }
    }
}
