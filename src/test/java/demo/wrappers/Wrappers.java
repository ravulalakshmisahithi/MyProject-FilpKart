package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.HashSet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    
    public static void implictwait (WebDriver driver, int duration){
        try{
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void popup (WebDriver driver){  // this is optional as screen runs in back end 
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           WebElement popup = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@role='button']")));
         
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].click();", popup);
         System.out.println("Popup clicked successfully.");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to click popup.");

        }
    }

    public static void sendKeys(WebDriver driver, By locator, String text){
    WebElement element = driver.findElement(locator);
    element.sendKeys(Keys.CONTROL+"a");    // Using this as clear is not working
    element.sendKeys(Keys.DELETE);
    element.sendKeys(text, Keys.ENTER);   
}

public static void explicitWait(WebDriver driver, String Text, int duration){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.urlContains(Text));

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.toLowerCase().contains(Text.toLowerCase())) {
            System.out.println("URL contains the text: " + Text);
        }
        else {
            System.out.println("URL does not contain the text: " + Text);
        }
}

public static void clickElement(WebDriver driver, By locator){
    WebElement element = driver.findElement(locator);
    element.click();
}

public static void listOfElements(WebDriver driver, By locator, double ratingNumber){
    List<WebElement> elements = driver.findElements(locator);
    System.out.println("Total No of ratings count:" + "  " + elements.size());

    int initialcount = 0;
    for (WebElement element : elements){
        if (Double.parseDouble(element.getText().trim()) <= ratingNumber){
            initialcount++;
        }
    }
    System.out.println("Total no of ratings below 4 or "   + ratingNumber + ":" + " " + initialcount);
}


public static void listOfElementsfordiscountandTitles (WebDriver driver, By discountlocator, By namelocator, int UserdiscountNumber){
    try{
    List<WebElement> discount = driver.findElements(discountlocator);
    List<WebElement> TitleOfDiscountProduct = driver.findElements(namelocator);
    Map<String, Integer> discountMap = new HashMap<>();
    System.out.println("Total No of discount count:" + "  " + discount.size());

    int initialcount = 0;
    for (int i =0; i<discount.size(); i++){
        String discountnumber = discount.get(i).getText().replaceAll("% off", "");
        int discountValue = Integer.parseInt(discountnumber);
        if (discountValue > UserdiscountNumber){
            initialcount++;
            String title = TitleOfDiscountProduct.get(i).getText();
            discountMap.put(title, discountValue);
               }
    }
    for (Map.Entry<String, Integer> Titlediscounts : discountMap.entrySet()) {
        System.out.println("Title: " + Titlediscounts.getKey() + ", Discount: " + Titlediscounts.getValue() + "%");
    }
    System.out.println("Total no of discounts above " + UserdiscountNumber + ":" + " " + initialcount);
 
    } catch (Exception e){
        e.printStackTrace();
        System.out.println("Failed to list elements for discount.");
    }
}


public static void PrintImageandTitles(WebDriver driver, By locator){
    try{
    List <WebElement> Reviews = driver.findElements(locator);
Set<Integer> reviewsNumbers = new HashSet<>();
    for (WebElement review : Reviews) {
            int reviewNumber = Integer.parseInt(review.getText().replaceAll("[^\\d]", ""));
            reviewsNumbers.add(reviewNumber);
    }

List<Integer> uniqueReviews = new ArrayList<>(reviewsNumbers);
Collections.sort(uniqueReviews, Collections.reverseOrder());   // to sort in desc order 
System.out.println("Unique review numbers in descending order: " + uniqueReviews);

NumberFormat numberformat = NumberFormat.getInstance(Locale.US);   // to add commas (ex: 1,000)
LinkedHashMap<String, String> reviewsMap = new LinkedHashMap<>();
for (int i =0; i <5; i++){
    String formattedReview = "(" + numberformat.format(uniqueReviews.get(i)) + ")";
    String Title = driver.findElement(By.xpath("//div[contains(@class, 'RGLWAk')]//span[contains(text(),'" + formattedReview + "')]/..//..//a[@class='pIpigb']")).getText();
   String ImageURL = driver.findElement(By.xpath("//div[contains(@class, 'RGLWAk')]//span[contains(text(),'" + formattedReview + "')]/..//..//img[@class='UCc1lI']")).getAttribute("src");
   String HighestReview = String.valueOf(i+1 + " Highest Review: "+ formattedReview);
   reviewsMap.put(HighestReview, "Title: " + Title + ", ImageURL: " + ImageURL); 
}

for (Map.Entry<String, String> ImgandTitle : reviewsMap.entrySet()) {
    System.out.println(ImgandTitle.getKey() + ": " + ImgandTitle.getValue());
}
} catch (Exception e){
    e.printStackTrace();
    System.out.println("Failed to print image and titles.");
}

}

public static void scroll(WebDriver driver){
    try{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 500)");
    } catch (Exception e){
        e.printStackTrace();
        System.out.println("Failed to scroll.");
    }
}

}

