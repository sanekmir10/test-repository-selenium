import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.userPages.*;


import static org.junit.Assert.assertTrue;

public class Lesson7Test {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


    @Test
    public void zadanie13() throws InterruptedException {

        MainPage mainPage = new MainPage(driver);
        // Заходим на главную страницу
        mainPage.Start();

        int countProducts = 0;

        // Добавляем к корзину продукцию
        for (int i = 0; i < 4; i++) {

            // Запоминаем текущее количество элементов в корзине
            countProducts = Integer.parseInt(mainPage.headerWrapper().gettxtCart());

            // Кликаем по первому попавшемуся элементу и переходим на страницу товара
            ProductPage productPage = mainPage.divMostPopular().bClickWebElemetnsProduct(0);

            // Добавляем продукт в корзину
            productPage.idBoxProduct().bClickAddCartProduct();

            // Смотрим количество товара в корзине
            //while (countProducts != (Integer.parseInt(productPage.headerWrapper().gettxtCart()) - 1)){
            //    Thread.sleep(100);
            //}
            // Ждем пока количество товаров в корзине не измениться
            wait.until(ExpectedConditions.textToBePresentInElement(
                    productPage.headerWrapper().webElementCart(),
                    Integer.toString(countProducts + 1)));

            // Возвращаемся на предыдущую страницу
            productPage.bPrevPageClick();
        }

        int countProductsNew = Integer.parseInt(mainPage.headerWrapper().gettxtCart());

        // Переходим в корзину
        CheckoutPage checkoutPage = mainPage.headerWrapper().bClickOpenСheckout();

        // Ждем пока не загрузиться страница корзины
        // Пока не будет видно страницы удалить продукцию
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.cartForm().bRemoveCartItem()));


        // Удаляем из корзины
        while (checkoutPage.cartForm().isAnyProductInCart()){

            // Удаляем одину продукцию
            checkoutPage.cartForm().bClickRemoveCartItem();

            countProducts--;
            // Смотрим количество товара в корзине
            //while (countProducts != (checkoutPage.cartForm().countProductsInCart() - 1)){
            //    Thread.sleep(100);
            //}

            // Ждем пока количество товаров в корзине не измениться
            wait.until(ExpectedConditions.numberOfElementsToBe(
                    By.xpath(checkoutPage.cartForm().getWebElementsProductsXpath()),
                    countProducts));
        }



    }









}
