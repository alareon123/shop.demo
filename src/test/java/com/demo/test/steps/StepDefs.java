package com.demo.test.steps;

import com.codeborne.selenide.WebDriverRunner;
import com.demo.SpringContextConfig;
import com.demo.driver.WebDriverManager;
import com.demo.pages.*;
import com.demo.utils.UtilitiesHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;


@CucumberContextConfiguration
@SpringBootTest(classes = SpringContextConfig.class)
@ComponentScan("com.demo")
@Slf4j
public class StepDefs {

    private String mainPageUrl = "https://demo.prestashop.com/";

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UtilitiesHelper helper;

    @Before
    public void before() {
        WebDriverManager.initChrome();
    }

    @After
    public void after() {
        WebDriverRunner.closeWebDriver();
    }

    @When("открыть главную страницу")
    public void openMainPage() {
        MainPage mainPage = open(mainPageUrl, MainPage.class);
        WebDriverRunner.getWebDriver().switchTo().frame("framelive");
        Assert.assertTrue("Ошибка! Главная страница не загружена",
                mainPage.isPageLoaded());
    }

    @When("зарегистрировать тестового пользователя")
    public void authByTestUser() {
        MainPage mainPage = page(MainPage.class);
        mainPage.signIn();

        LoginPage loginPage = page(LoginPage.class);
        Assert.assertTrue("Ошибка! Страница Авторизация не была загружена",
                loginPage.isPageLoaded());

        mainPage = loginPage.registerTestUser(
                helper.getRandomString(10)+"@mail.ru",
                helper.readProperty("user.password"));
        Assert.assertTrue("Ошибка! Пользователь не был зарегистрирован",
                mainPage.isUserAuthorized());
        log.info("Пользователь успешно зарегистрирован");
    }

    @And("открыть {int}-й товар")
    public void openSelectedProductPage(int index) {
        MainPage mainPage = page(MainPage.class);
        mainPage.selectProductByIndex(index);
        log.info(String.format("Открыт %s-й товар", index));
    }

    @And("выбрать {int} единиц товара и добавть его в корзину")
    public void selectProductCountAndAddToCart(int quantity) {
        ProductPage productPage = page(ProductPage.class);
        productPage.setQuantity(quantity).addToCart().proceedToCheckOut();
        log.info(String.format("В корзину добавлено %s единиц товара", quantity));
    }

    @Then("подтвердить что в корзине {int} единиц товара")
    public void checkIsProductCountCorrect(int quantity) {
        CartPage cartPage = page(CartPage.class);
        Assert.assertEquals("Ошибка! Количество товара в корзине не " +
                "соотвествует ожидаемому", quantity, cartPage.getProductQuantity());
        log.info("Количество товара в корзине равно " + quantity);
    }

    @And("перейти к оформлению")
    public void proceedToCheckOut() {
        CheckOutPage checkOutPage = page(CartPage.class).proceed();
        Assert.assertTrue("Ошибка! Страница Оформление не была загружена",
                checkOutPage.isPageLoaded());
        log.info("Страница Оформление загружена");
    }

    @And("заполнить информацию для оплаты и разместить заказ")
    public void fillPaymentInfoBlocks() {
        page(CheckOutPage.class)
                .fillAddress()
                .fillShipmentMethod()
                .fillPaymentMethod();
        log.info("Платёжная информация успешно внесена");
    }

    @Then("подтвердить что заказ успешно создан и содержит {int} единиц товара")
    public void checkIsOrderCreatedAndContainsSelectedQuantity(int quantity) {
        OrderConfirmPage orderConfirmPage = page(OrderConfirmPage.class);
        Assert.assertTrue("Ошибка! Заказ не был оформлен", orderConfirmPage.isPageLoaded());
        Assert.assertEquals("Ошибка! Количество товаров не соотвествует ожидаемому",
                orderConfirmPage.getItemsQuantity(), quantity);
        log.info(String.format("Заказ успешно создан и содержит %s единиц товара", quantity));

    }
}
