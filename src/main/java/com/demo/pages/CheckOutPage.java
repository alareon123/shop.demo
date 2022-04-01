package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class CheckOutPage extends RegularWebPage{

    @FindBy(xpath = "//input[@id='field-alias']")
    private SelenideElement inputAlias;

    @FindBy(xpath = "//input[@id='field-company']")
    private SelenideElement inputCompany;

    @FindBy(xpath = "//input[@id='field-vat_number']")
    private SelenideElement inputVATNumber;

    @FindBy(xpath = "//input[@id='field-address1']")
    private SelenideElement inputAddress;

    @FindBy(xpath = "//input[@id='field-postcode']")
    private SelenideElement inputZipCode;

    @FindBy(xpath = "//input[@id='field-city']")
    private SelenideElement inputCity;

    @FindBy(xpath = "//select[@id='field-id_country']")
    private SelenideElement dropListCountry;

    @FindBy(xpath = "//button[@name='confirm-addresses']")
    private SelenideElement buttonContinueAddress;

    @FindBy(xpath = "//button[@name='confirmDeliveryOption']")
    private SelenideElement buttonContinueShipment;

    @FindBy(xpath = "//input[@id='payment-option-1']")
    private SelenideElement inputPayByCheck;

    @FindBy(xpath = "//input[@id='conditions_to_approve[terms-and-conditions]']")
    private SelenideElement checkBoxTerms;

    @FindBy(xpath = "//div[@id='payment-confirmation']//button")
    private SelenideElement buttonPlaceOrder;

    public CheckOutPage fillAddress() {
        inputAlias.sendKeys("some text");
        inputCompany.sendKeys("some company");
        inputVATNumber.sendKeys("123");
        inputAddress.sendKeys("some address");
        inputZipCode.sendKeys("12345");
        inputCity.sendKeys("some city");
        buttonContinueAddress.click();
        return this;
    }

    public CheckOutPage fillShipmentMethod() {
        buttonContinueShipment.click();
        return this;
    }

    public OrderConfirmPage fillPaymentMethod() {
        inputPayByCheck.click();
        checkBoxTerms.click();
        buttonPlaceOrder.click();
        return page(OrderConfirmPage.class);
    }

    @Override
    public boolean isPageLoaded() {
        inputAlias.shouldBe(Condition.visible);
        return inputAddress.isDisplayed();
    }
}
