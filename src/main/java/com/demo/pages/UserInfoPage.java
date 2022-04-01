package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class UserInfoPage extends RegularWebPage{

    @FindBy(xpath = "//a[@id='identity-link']")
    private SelenideElement buttonInformation;

    @Override
    public boolean isPageLoaded() {
        buttonInformation.shouldBe(Condition.visible);
        return buttonInformation.isDisplayed();
    }
}
