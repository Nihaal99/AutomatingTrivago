package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelsListPage {
    WebDriver driver;
    @FindBy(xpath = "//*[@data-title='Hotel']")
    WebElement hotelBtn;
    @FindBy(xpath = "//*[@name='guest_rating_filters']")
    WebElement guestRatingBtn;
    @FindBy(xpath = "//*[@data-testid='8.5-rating-hotels-filter']")
    WebElement ratingBtn;
    @FindBy(xpath = "//*[@name='more_filters']")
    WebElement moreFiltersBtn;
    @FindBy(xpath = "//*[@id='__next']/main/div[2]/div[2]/div/div/div[5]/div/div/div/div[2]/section/div[1]/div/button[3]/span[1]")
    WebElement threeStarBtn;
    @FindBy(xpath = "//*[@id='filter-checkbox-0']")
    WebElement feeCancelBtn;
    @FindBy(xpath = "//*[@id='filter-checkbox-1']")
    WebElement freeBreakfastBtn;
    @FindBy(xpath = "//*[@id='filter-checkbox-8']")
    WebElement familyFriendlyBtn;
    @FindBy(xpath = "/html/body/div[1]/main/div[3]/div[1]/div[1]/section/div/select")
    WebElement sortByBtn;
    @FindBy(xpath = "//*[@value='6']")
    WebElement ratingNRmBtn;
    @FindBy(xpath = "/html/body/div[1]/main/div[3]/div[1]/div[2]/div/ol[1]/li[1]/section/article/div[2]/div[1]/h3/button")
    WebElement hotelName;
    @FindBy(xpath = "//*[@data-testid='recommended-price']")
    WebElement hotelPrice;
    @FindBy(xpath = "//*[@data-testid='clickout-area']")
    WebElement checkOutBtn;


    //Constructor
    public HotelsListPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //Methods
    public WebElement hotelBtnFilter(){
        return hotelBtn;
    }
    public  WebElement getGuestRatingBtn(){
        return guestRatingBtn;
    }
    public  WebElement getRatingBtn(){
        return ratingBtn;
    }
    public  WebElement getMoreFiltersBtn(){
        return moreFiltersBtn;
    }
    public  WebElement getThreeStarBtn(){
        return threeStarBtn;
    }
    public  WebElement getFeeCancelBtn(){
        return feeCancelBtn;
    }
    public  WebElement getFreeBreakfastBtn(){
        return freeBreakfastBtn;
    }
    public  WebElement getFamilyFriendlyBtn(){
        return familyFriendlyBtn;
    }
    public WebElement getSortByBtn(){
        return sortByBtn;
    }
    public WebElement getRatingNRmBtn(){
        return  ratingNRmBtn;
    }
    public WebElement getHotelName(){
        return hotelName;
    }
    public WebElement getHotelPrice(){
        return hotelPrice;
    }
    public WebElement getCheckOutBtn(){
        return checkOutBtn;
    }


}
