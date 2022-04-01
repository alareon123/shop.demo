package com.demo.pages;

import com.codeborne.selenide.WebDriverRunner;

public abstract class RegularWebPage {
    public String getCurrentDomModel() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }

    public String getCurrentTitle() {
        return WebDriverRunner.getWebDriver().getTitle();
    }

    public abstract boolean isPageLoaded();
}
