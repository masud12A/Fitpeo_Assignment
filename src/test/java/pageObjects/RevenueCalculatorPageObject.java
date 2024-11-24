package pageObjects;

import java.awt.AWTException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class RevenueCalculatorPageObject extends BasePage{
	
	public RevenueCalculatorPageObject(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath = "//a[@href='/revenue-calculator']//div[contains(text(),'Revenue Calculator')]")
	@CacheLookup
	public WebElement revenueCalculaterElement;

	// Scroll slider upto the value 820 take original
	By sliderBallMovmentElement = By.cssSelector(
			".MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary.MuiSlider-thumb.MuiSlider-thumbSizeMedium.MuiSlider-thumbColorPrimary.css-sy3s50");

	By sliderBallElement = By.xpath("//input[@type='range']");

	By textBoxBySliderBoxElement = By.xpath(
			"//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']");
	

	// Enter the text value 560
	By textBoxBySliderElement = By.id(":r9:");  // not using
	
	By textBoxBySliderElementDisable = By.id(":R57alklff9da:");  // not using
	

	By totalRecurring = By.xpath("//div[@class='MuiToolbar-root MuiToolbar-gutters MuiToolbar-regular css-1lnu3ao']//p[4]//p");



	
	// Start the logic from here

	JavascriptExecutor jsExecute = (JavascriptExecutor) driver;

	public void clickRevenueCalculater() throws InterruptedException {

		revenueCalculaterElement.click();
		Thread.sleep(1000);
		
		System.out.println("click on the Revenu Calculater");

	}

	// Validate Text input value from slider
	public void inputTextForSlider() throws InterruptedException, AWTException {

		WebElement textBoxBySlider = driver.findElement(textBoxBySliderBoxElement);

		WebElement sliderBall = driver.findElement(sliderBallElement);

		jsExecute.executeScript("arguments[0].scrollIntoView(false);", sliderBall);

		textBoxBySlider.click();

		int EnterTheValueInTextInputField = 0; // The number you want to enter
		

		 // Clean text input
		textBoxBySlider.sendKeys(Keys.CONTROL, "a");
		textBoxBySlider.sendKeys(Keys.BACK_SPACE);

		Thread.sleep(2000);
		// Press the up arrow key the required number of times
		while(EnterTheValueInTextInputField<560) {
			
			textBoxBySlider.sendKeys(Keys.ARROW_UP);
			EnterTheValueInTextInputField++;
          }

		Thread.sleep(2000);
		// Take the value of slider
		String sliderValueAfterEnterVlue = sliderBall.getAttribute("aria-valuenow");

		String textBoxBySliderValue = textBoxBySlider.getAttribute("value");

		System.out.println("Slider current Value is " + sliderValueAfterEnterVlue);

		Assert.assertEquals(textBoxBySliderValue, sliderValueAfterEnterVlue);
		
		System.out.println("inputTextForSlider() run successfully ");

	}

	// Validate Slider value from input text
	public void moveSlider() throws Exception {


		WebElement sliderBall = driver.findElement(sliderBallElement);

	//	WebElement textBoxBySlider = driver.findElement(textBoxBySliderElementDisable);
		WebElement textBoxBySlider = driver.findElement(textBoxBySliderBoxElement);
        
		jsExecute.executeScript("arguments[0].scrollIntoView(false);", textBoxBySlider); // scroll on the element
		
		int setTheSliderValue = 820;
		
		
       // Clean text input
		textBoxBySlider.sendKeys(Keys.CONTROL, "a");
		textBoxBySlider.sendKeys(Keys.BACK_SPACE);

		for (int i = 0; i < setTheSliderValue; i++) {
			sliderBall.sendKeys(Keys.ARROW_UP);
		}

		// Take the value of slider
		String sliderValueAfterEnterVlue = sliderBall.getAttribute("aria-valuenow");

		String textBoxBySliderValue = textBoxBySlider.getAttribute("value");

		System.out.println("Slider current Value is " + sliderValueAfterEnterVlue);


		Assert.assertEquals(textBoxBySliderValue, sliderValueAfterEnterVlue);

	}

	// CPT-99091, CPT-99453, CPT-99454, and CPT-99474

	public void clickCheckBoxes() throws InterruptedException {

		// Store the list CPT-99091, CPT-99453, CPT-99454, and CPT-99474
		ArrayList<String> cptList = new ArrayList<String>();

		cptList.add("CPT-99091");
		cptList.add("CPT-99453");
		cptList.add("CPT-99454");
		cptList.add("CPT-99474");

		for (int i = 1; i <= 14; i++) {
            
			//Dynamic xpath 
			WebElement takeTextCPT = driver.findElement(
					By.xpath("//div[@class='MuiBox-root css-4o8pys'][" + i + "]//p[contains(text(),'CPT')]"));

			// Scroll the page based on the Element
			jsExecute.executeScript("arguments[0].scrollIntoView(false);", takeTextCPT);
			String cptValueStore = takeTextCPT.getText();

			Thread.sleep(2000);
			
			// Compare the Value
			if (cptList.contains(cptValueStore)) {
				
				//Dynamic xpath 
				WebElement checkbox = driver.findElement(By.xpath(
						"//div[@class='MuiBox-root css-4o8pys'][" + i + "]//label//span//input[@type='checkbox']"));

				checkbox.click();

			}

		}

	}
	
	
	
	
	// Validate Recurring Amount
	public void validateRecurringAmount() {
		
		String actualRcurringAmount = "110700";
		
		WebElement reccuringAmount = driver.findElement(totalRecurring);  // Header value 
		
		String takeFromUIRecurringAmount = reccuringAmount.getText().substring(1); // Remove the doller sign
		
		System.out.println("takeFromUI "+takeFromUIRecurringAmount);
		
		Assert.assertEquals(takeFromUIRecurringAmount, actualRcurringAmount);
		
		
	}
	
	
}
