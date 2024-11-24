package testCases;


import testBase.BaseClass;

import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RevenueCalculatorPageObject;

public class TC_001_HomePage_Test extends BaseClass{
	
	
	@Test(priority = 1)
		public void validateTextToSlider() throws Exception {
			
			HomePageObject openHomepage = new HomePageObject(driver);
			openHomepage.navigateToHomePage();
			System.out.println("Home page opened");
			
			RevenueCalculatorPageObject  revenueCalculatorPageObject = new RevenueCalculatorPageObject(driver);
			
			
			revenueCalculatorPageObject.clickRevenueCalculater();
			
			revenueCalculatorPageObject.inputTextForSlider();

			
			
			
		}
		
		
		//@Test(priority = 2)
		public void validateRecurringAmount() throws Exception {
			
			HomePageObject openHomepage = new HomePageObject(driver);
			openHomepage.navigateToHomePage();
			System.out.println("Home page opened");
			
			RevenueCalculatorPageObject  revenueCalculatorPageObject = new RevenueCalculatorPageObject(driver);
			
			
			revenueCalculatorPageObject.clickRevenueCalculater();
			
			revenueCalculatorPageObject.moveSlider();
			revenueCalculatorPageObject.clickCheckBoxes();
			revenueCalculatorPageObject.validateRecurringAmount();

			
			
			
		}

}
