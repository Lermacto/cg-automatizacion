package com.gc.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DriverConfiguration {
    @PostConstruct
    void postConstruct(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
    }

    @Bean
    public ChromeDriver driver(){
        return new ChromeDriver();
    }
}
