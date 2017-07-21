package com;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

/**
 * Created by razvansidra on 7/21/2017.
 */
public class MadisonTest {
    WebDriver driver;
    WebElement logo, account, search, dynamicElement;
    Select language,products;

    @Before
    public void openChromeTest() {
        System.setProperty("webdriver.chrome.driver", "C://Users//razvansidra//WebDriverTesting//Drivers//chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://qa2.madison.com");
    }

    @Test
    public void homepageTest() {
        logo = driver.findElement(By.className("logo"));

        Assert.assertEquals("The title is not correct!", "Madison Island", driver.getTitle());
        Assert.assertEquals("The URL in not correct", "http://qa2.madison.com/", driver.getCurrentUrl());
        Assert.assertTrue("The Logo is not displayed", logo.isDisplayed());
        logo.click();
        Assert.assertEquals("Can not click on the logo", "http://qa2.madison.com/", driver.getCurrentUrl());
        Assert.assertEquals("You are not on the homepage", "http://qa2.madison.com/", driver.getCurrentUrl());
        driver.navigate().to("http://qa2.madison.com/women/pants-denim/tribeca-skinny-jean.html");
        Assert.assertNotEquals("You didn't navigate to another page ", "http://qa2.madison.com/", driver.getCurrentUrl());
        driver.navigate().back();
        Assert.assertEquals("Couldn't navigate back", "http://qa2.madison.com/", driver.getCurrentUrl());
        driver.navigate().forward();
        Assert.assertEquals("Couldn't navigate forward", "http://qa2.madison.com/women/pants-denim/tribeca-skinny-jean.html", driver.getCurrentUrl());
        driver.navigate().refresh();
        Assert.assertEquals("Couldn't refresh the page", "http://qa2.madison.com/women/pants-denim/tribeca-skinny-jean.html", driver.getCurrentUrl());


    }

    @Test
    public void accountTest() {
        account = driver.findElement(By.className("skip-account"));
        account.click();

        Assert.assertTrue("The drop down list is not displayed", account.isDisplayed());

        Assert.assertSame("Incorect account string", "ACCOUNT", account.getText().toString());


    }

    @Test
    public void languagesTest() {
        language = new Select(driver.findElement(By.id("select-language")));
        Assert.assertEquals(3, language.getOptions().size());
        System.out.println("Number of languages is: " + language.getOptions().size());

        language.selectByIndex(1);


    }

    @Test
    public void searchTest() {
        search = driver.findElement(By.id("search"));
        search.clear();
        search.sendKeys("dress");
        search.submit();
        dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.className("sort-by")));
        Assert.assertTrue("Keyword not found ", driver.findElement(By.cssSelector(".page-title>h1")).getText().toLowerCase().contains("dress"));

    }

    @Test
    public void newProductsListTest() {
        //products = new Select(driver.findElement(By.id("map-popup")));
        List<WebElement> newProducts = driver.findElements(By.className("product-info"));
        System.out.println("Number of products is: " + newProducts.size());
        Iterator<WebElement> iter = newProducts.iterator();
        while (iter.hasNext()) {
            WebElement item = iter.next();
            System.out.println("Products:" + item.getText());

        }
    }
    @Test
    public void navigationTest(){
       // driver.navigate().to("http://qa2.madison.com/women/pants-denim/tribeca-skinny-jean.html");
        List<WebElement> headlines = driver.findElements(By.className("skip-content"));
        Iterator<WebElement> iter = headlines.iterator();
        while (iter.hasNext()) {
            WebElement item = iter.next();
            System.out.println( item.getText());

        }
        driver.findElement(By.cssSelector(".level0.nav-5.parent")).click();

    }

    @After
    public void closeChromeTest() {
        driver.quit();
    }
}