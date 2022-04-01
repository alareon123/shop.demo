package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmPage extends RegularWebPage{

    @FindBy(xpath = "//section[@id='content-hook_order_confirmation']")
    private SelenideElement loadCheck;

    @FindBy(xpath = "//div[@class='order-line row']//div[@class='row']/div[2]")
    private SelenideElement textItemsQuantity;

    public int getItemsQuantity() {
        return Integer.parseInt(textItemsQuantity.getText());
    }

    @Override
    public boolean isPageLoaded() {
        loadCheck.shouldBe(Condition.visible);
        return loadCheck.isDisplayed();
    }
}
