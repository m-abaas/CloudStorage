package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement userNameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "login-link")
    private WebElement backToLoginPage;


    public SignupPage(WebDriver webDriver)
    {
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String userName, String password)
    {
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.userNameField.sendKeys(userName);
        this.passwordField.sendKeys(password);
        this.submitButton.click();

    }

    public void goBackToLoginPage()
    {
        this.backToLoginPage.click();
    }

    public void waitForPageLoading(WebDriver driver)
    {
        WebDriverWait wait =  new WebDriverWait(driver, 2);
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("signupPageCompleted")));
    }

}
