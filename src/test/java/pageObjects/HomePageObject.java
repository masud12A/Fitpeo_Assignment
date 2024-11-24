package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePageObject extends BasePage {

	public HomePageObject(WebDriver driver) {
		
		super(driver);
	}
		
		@FindBy(xpath ="//a[@href='/home']")
		@CacheLookup
		protected WebElement homepageElement;
		
		
		
		
		public void navigateToHomePage() throws InterruptedException {

			
			homepageElement.click();
			Thread.sleep(5000);	
		
		
	}

	

}
