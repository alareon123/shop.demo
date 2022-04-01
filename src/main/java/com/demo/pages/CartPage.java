package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.page;

public class CartPage extends RegularWebPage{

    @FindBy(xpath = "//div[contains(@class, 'checkout')]//a")
    private SelenideElement buttonProceedToCheckOut;

    @FindBy(xpath = "//input[contains(@class, 'js-cart-line-product-quantity')]")
    private SelenideElement inputQuantity;

    public int getProductQuantity() {
        return Integer.parseInt(Objects.requireNonNull(inputQuantity.getValue()));
    }

    public CheckOutPage proceed() {
        buttonProceedToCheckOut.click();
        return page(CheckOutPage.class);
    }

    @Override
    public boolean isPageLoaded() {
        buttonProceedToCheckOut.shouldBe(Condition.visible);
        return buttonProceedToCheckOut.isDisplayed();
    }
}
