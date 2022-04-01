package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class MainPage extends RegularWebPage{

    @FindBy(xpath = "//section[@id='main']")
    private SelenideElement loadChecker;

    @FindBy(xpath = "//div[@class='user-info']/a")
    private SelenideElement buttonLogin;

    @FindBy(xpath = "//a[contains(@class, 'logout')]")
    private SelenideElement buttonSighOut;

    @FindBy(xpath = "//div[contains(@class, 'products')]//a[contains(@class, 'thumbnail')]")
    private ElementsCollection products;

    public MainPage signIn() {
        buttonLogin.click();
        return this;
    }

    public ProductPage selectProductByIndex(int index) {
        products.get(index).click();
        return page(ProductPage.class);
    }

    public boolean isUserAuthorized() {
        buttonSighOut.shouldBe(Condition.visible);
        return buttonSighOut.isDisplayed();
    }

    @Override
    public boolean isPageLoaded() {
        loadChecker.shouldBe(Condition.visible);
        return loadChecker.isDisplayed();
    }
}
