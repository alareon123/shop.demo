package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends RegularWebPage{

    @FindBy(xpath = "//input[@id='field-email']")
    private SelenideElement inputEmail;

    @FindBy(xpath = "//input[@id='field-password']")
    private SelenideElement inputPassword;

    @FindBy(xpath = "//button[@id='submit-login']")
    private SelenideElement buttonSignIn;

    @FindBy(xpath = "//div[@class='no-account']/a")
    private SelenideElement linkNoAccount;

    public LoginPage auth(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        buttonSignIn.click();
        return this;
    }

    public MainPage registerTestUser(String email, String password) {
        linkNoAccount.click();
        return page(RegistrationPage.class).registerTestUser(email, password);
    }

    @Override
    public boolean isPageLoaded() {
        buttonSignIn.shouldBe(Condition.visible);
        return buttonSignIn.isDisplayed();
    }
}
