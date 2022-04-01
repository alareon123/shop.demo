package com.demo.driver;

import com.codeborne.selenide.Configuration;

public class WebDriverManager {
    public static void initChrome() {
        Configuration.browser = "chrome";
        Configuration.timeout = 20000;
    }
}
