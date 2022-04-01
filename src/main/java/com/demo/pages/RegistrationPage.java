package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage extends RegularWebPage{

    @FindBy(xpath = "//input[@id='field-id_gender-1']")
    private SelenideElement inputGender;

    @FindBy(xpath = "//input[@id='field-firstname']")
    private SelenideElement inputFirstName;

    @FindBy(xpath = "//input[@id='field-lastname']")
    private SelenideElement inputLastName;

    @FindBy(xpath = "//input[@id='field-email']")
    private SelenideElement inputEmail;

    @FindBy(xpath = "//input[@id='field-password']")
    private SelenideElement inputPassword;

    @FindBy(xpath = "//input[@id='field-birthday']")
    private SelenideElement inputBirthDate;

    @FindBy(xpath = "//input[@type='checkbox']")
    private ElementsCollection checkBoxes;

    @FindBy(xpath = "//button[@type='submit']")
    private SelenideElement buttonSave;

    public MainPage registerTestUser(String email, String password) {
        inputGender.click();
        inputFirstName.sendKeys("Test");
        inputLastName.sendKeys("User");
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        inputBirthDate.sendKeys("05/31/1970");
        checkBoxes.asFixedIterable().forEach(SelenideElement::click);
        buttonSave.click();
        return page(MainPage.class);
    }

    @Override
    public boolean isPageLoaded() {
        inputGender.shouldBe(Condition.visible);
        return inputGender.isDisplayed();
    }
}
