package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.page;

public class ProductPage extends RegularWebPage{

    @FindBy(xpath = "//div[@class='add']/button[@type='submit']")
    private SelenideElement buttonAddToCart;

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    private SelenideElement inputQuantity;
    
    @FindBy(xpath = "//button[contains(@class, 'bootstrap-touchspin-up')]")
    private SelenideElement increaseQuantity;

    @FindBy(xpath = "//button[contains(@class, 'bootstrap-touchspin-down')]")
    private SelenideElement decreaseQuantity;

    @FindBy(xpath = "//div[@class='cart-content-btn']/a")
    private SelenideElement proceedToCheckOut;

    public ProductPage setQuantity(int quantity) {
        int currentQuantity = Integer.parseInt(Objects.requireNonNull(inputQuantity.getValue()));
        if (currentQuantity != quantity) {
            for (int i = 0; i < quantity - currentQuantity; i++) {
                increaseQuantity.click();
            }
        }
        return this;
    }

    public ProductPage addToCart() {
        buttonAddToCart.click();
        return this;
    }

    public CartPage proceedToCheckOut() {
        proceedToCheckOut.click();
        return page(CartPage.class);
    }

    @Override
    public boolean isPageLoaded() {
        buttonAddToCart.shouldBe(Condition.visible);
        return buttonAddToCart.isDisplayed();
    }
}
