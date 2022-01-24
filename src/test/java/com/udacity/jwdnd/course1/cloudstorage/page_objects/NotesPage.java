package com.udacity.jwdnd.course1.cloudstorage.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotesPage {

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "buttonNewNote")
    private WebElement buttonNewNote;

    @FindBy(id = "note-title")
    private WebElement titleModal;

    @FindBy(id = "note-description")
    private WebElement descriptionModal;

    @FindBy(id = "buttonSaveChangesNotes")
    private WebElement buttonSaveChanges;

    @FindBy(id = "userTable")
    private WebElement table;

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createNewNote(WebDriver driver, String title, String description) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(notesTab));
        this.notesTab.click();

        wait.until(ExpectedConditions.elementToBeClickable(buttonNewNote));
        this.buttonNewNote.click();

        wait.until(ExpectedConditions.visibilityOf(titleModal));
        this.titleModal.sendKeys(title);
        this.descriptionModal.sendKeys(description);
        this.buttonSaveChanges.click();
    }

    public boolean noteExists(WebDriver driver, String title, String description) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(notesTab));
        this.notesTab.click();

        List<WebElement> noteTableRows = table.findElements(By.className("noteTableRow"));

        for (WebElement noteTableRow : noteTableRows) {
            WebElement titleElement = noteTableRow.findElement(By.className("classNoteTitle"));
            wait.until(ExpectedConditions.visibilityOf(titleElement));

            WebElement descriptionElement = noteTableRow.findElement(By.className("classNoteDescription"));
            wait.until(ExpectedConditions.visibilityOf(descriptionElement));

            if(titleElement.getText().equals(title) && descriptionElement.getText().equals(description)) return true;
        }

        return false;
    }

    public void editNote(WebDriver driver, String newTitle, String newDescription) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(notesTab));
        this.notesTab.click();

        List<WebElement> editButton = table.findElements(By.tagName("button"));

        wait.until(ExpectedConditions.elementToBeClickable(editButton.get(0)));
        editButton.get(0).click();

        wait.until(ExpectedConditions.visibilityOf(titleModal));
        this.titleModal.clear();
        this.titleModal.sendKeys(newTitle);
        this.descriptionModal.clear();
        this.descriptionModal.sendKeys(newDescription);
        this.buttonSaveChanges.click();
    }

    public void deleteNote(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(notesTab));
        this.notesTab.click();

        List<WebElement> noteTableRows = table.findElements(By.className("noteTableRow"));

        for (WebElement noteTableRow : noteTableRows) {
            WebElement deleteButton = noteTableRow.findElement(By.tagName("a"));
            wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
            deleteButton.click();
        }
    }
}
