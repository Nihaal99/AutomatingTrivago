package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TrivagoHomePage{
WebDriver driver;
//PageFactoryImplementation
    @FindBy(xpath = "//*[@id='input-auto-complete']")
     WebElement destinationBox;

    @FindBy(xpath = "//*[@id='__next']/div[1]/main/div[3]/div[2]/div/div[3]/div/form/div[4]/div/div[2]/div/div[3]/div/div/div/div[2]/table/tbody/tr[1]/td[2]/time/button/span")
    WebElement checkInDate;

    @FindBy(xpath = "//*[@id='__next']/div[1]/main/div[3]/div[2]/div/div[3]/div/form/div[4]/div/div[2]/div/div[3]/div/div/div/div[2]/table/tbody/tr[4]/td[5]/time/button/span")
    WebElement checkOutDate;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div[3]/div[2]/div/div[3]/div/form/div[2]/button")
    WebElement roomAndGuests;
    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div[3]/div[2]/div/div[3]/div/form/div[4]/div/div[2]/div/div[1]/div/input")
    WebElement adultsNo;
    @FindBy(xpath = "//*[@id='__next']/div[1]/main/div[3]/div[2]/div/div[3]/div/form/div[4]/div/div[2]/div/div[1]/label")
    WebElement roomAndGuestFlexText;
    @FindBy(xpath="/html/body/div[1]/div[1]/main/div[3]/div[2]/div/div[3]/div/form/div[4]/div/div[2]/div/div[2]/div/input")
    WebElement childNo;
    @FindBy(xpath = "//*[@id='__next']/div[1]/main/div[3]/div[2]/div/div[3]/div/form/div[3]/button")
    WebElement submitBtn;
    @FindBy(xpath = "//*[@id='react-autowhatever-1--item-0']/div/div")
    WebElement suggestedDestn;


    //Constructor creation very Imp for POM implementation
    public TrivagoHomePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement destinationSearchBox(){
        return destinationBox;
    }


        public WebElement checkInDate(){
        return checkInDate;
        }
        public  WebElement checkOutDate(){
        return checkOutDate;
        }
        public WebElement roomAndGuestBtn(){return roomAndGuests;}

    public WebElement setAdultsNo(){
        return adultsNo;
    }
    public WebElement verifytextOfFlex(){
        return roomAndGuestFlexText;
    }
    public WebElement setChildrenNo(){
        return childNo;
    }
    public WebElement submitButton(){
        return submitBtn;
    }
    public WebElement getSuggestedDestnDestination(){
        return suggestedDestn;
    }
}

