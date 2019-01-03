package com.ibm.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.utilities.PropertiesFileHandler;

public class CategoryPage {

	WebDriver driver;
	WebDriverWait wait;
	
		public CategoryPage(WebDriver driver,WebDriverWait wait)
		{
		this.driver=driver;
		this.wait=wait;
		PageFactory.initElements(driver,this);
		}
				
	//WebElement for link Catalog
			@FindBy(xpath="//a[contains(text(),'Catalog')]")
			WebElement catalogEle;
			
	//WebElement for link Categories
			@FindBy(xpath="//a[contains(text(),'Categories')]")
			WebElement catEle;
			
	//WebElement for icon Add New
			@FindBy(xpath="//a[@title='Add New']")
			WebElement addnewEle;
	
	//WebElement for field Category Name
			@FindBy(xpath="//input[@name='name']")
			WebElement catnameEle;
	
	//WebElement for field Meta Tag Title
			@FindBy(xpath="//input[@name='tag_title']")
			WebElement mtitleEle;
			
	//WebElement for field Sort Order
			@FindBy(xpath="//input[@name='sort']")
			WebElement sortEle;
			
	//WebElement for field Status
			@FindBy(xpath="//select[@name='status']")
			WebElement statusddEle;
		
	//WebElement for Image
			@FindBy(xpath="//input[@id='files']")
			WebElement imageEle;
			
	//WebElement for icon Top Arrow
			@FindBy(xpath="//div[@id='toTop']")
			WebElement topEle;
			
	//WebElement for icon Save
			@FindBy(xpath="//button[@title='Save']")
			WebElement saveEle;
			
	//WebElement for Search text box
			@FindBy(xpath="//input[@type='search']")
			WebElement searchEle;
			
	//WebElement for button Action
			@FindBy(xpath="(//button[contains(text(), 'Action')])[1]")
			WebElement actionEle;
					
	//WebElement for Edit
			@FindBy(xpath="//a[@title='Edit']")
			WebElement editEle;
		
	//WebElement for title of panel - Edit Category
			@FindBy(xpath="//div[@class='panel-title']/h4")
			WebElement headerEle;
			
	//WebElement for success message after editing a record
			@FindBy(xpath="//div[contains(text(),'Success')]")
			WebElement successmsgEle;
			
	//This is a method to click on link Catalog on Admin page
			public void clickOnLinkCatalog()
			{
				catalogEle.click();
			}
	//This is a method to click on link Categories under Catalog on Admin page		
			public void clickOnLinkCategories()
			{
				catEle.click();
			}
	//This is a method to click on icon Add New on Category List of Admin page		
			public void clickOnIconAddNew()
			{
				addnewEle.click();
			}
	//This is a method to enter value on field Category Name using properties file
			public void enterCategoryName(String catname)
			{
				catnameEle.sendKeys(catname);
			}
	//This is a method to enter value on field Meta Tag Title using properties file
			public void enterMetaTagTitle(String metatitle)
			{
				mtitleEle.sendKeys(metatitle);
			}
	//This is a method to enter value on field Sort Order using properties file		
			public void enterSortOrder(String sortorder)
			{
				sortEle.sendKeys(sortorder);
			}
	//This is a method to select value on field Status		
			public void selectStatus()
			{
				statusddEle.click();
				Select statusEle = new Select(statusddEle);
				statusEle.selectByVisibleText("Enabled");
			}
	//This is a method to select image using properties file
			public void enterImage(String imagepath)
			{
				imageEle.sendKeys(imagepath);
			}
	//This is a method to perform a Search	
			public void performSearch(String catname)
			{
				searchEle.sendKeys(catname);
				searchEle.click();
			}
	//This is a method to select Action icon	
			public void clickOnAction()
			{
				actionEle.click();
			}
	//This is a method to select Edit option			
			public void clickOnEdit()
			{
				editEle.click();
			}
	//This is a method to clear value on field Meta Tag Title		
			public void clearMetaTagTitle()
			{
				mtitleEle.clear();
			}
	//This is a method to clear value on field Sort Order		
			public void clearSortOrder()
			{
				sortEle.clear();
			}
	//This is a method to clear value on field Status		
			public void clearStatus()
			{
				statusddEle.click();
				Select statusEle = new Select(statusddEle);
				statusEle.selectByVisibleText("");
			}		
	//This is a method to click on icon Tpo Arrow		
			public void clickOnTopArrow()
			{
				topEle.click();
			}
			
	//This is a method to click on icon Save		
			public void clickOnIconSave()
			{
				saveEle.click();
			}
	
	//This is a method to validate the header of Add Tab page		
			public String getHeaderMessage() throws IOException
			{
				String actualheadermsg = headerEle.getText();
				System.out.println("The first validation on presence of Category header....");
				System.out.println("The header of Add Category page: " +actualheadermsg);
				Assert.assertEquals("Add Category", actualheadermsg);
				return(actualheadermsg);
			}
	
	//This is a method to validate the header of Edit Tab page		
			public String getEditHeaderMessage() throws IOException
			{
				String actualheadermsg = headerEle.getText();
				System.out.println("The first validation on presence of modified Category header....");
				System.out.println("The header of Edit Category page: " +actualheadermsg);
				Assert.assertEquals("Add Category", actualheadermsg);
				return(actualheadermsg);
			}
	
	//This is a method to validate the error message on missing mandatory field - Tab Name
			public String validationOnMisingField() throws IOException
			{
				String file="./TestData/magentodata.properties";
				PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
				HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
				
				String expectedmissingtabmessage = data.get("expectedmissingtabmessage");
				
				JavascriptExecutor js=(JavascriptExecutor) driver;
				String actualmissingtitlemessage = (String) js.executeScript("return document.getElementsByName('tag_title')[0].validationMessage;");
				System.out.println("The second validation on error message of missing field...");
				System.out.println("The error message on missing Mega Tag Title: " +actualmissingtitlemessage);
				Assert.assertEquals(expectedmissingtabmessage, actualmissingtitlemessage);
				return(actualmissingtitlemessage);
			}
	//
	//This is a method to validate the success message after saving the record
			public String validationOnSuccessMessage() throws IOException
			{
				String file="./TestData/magentodata.properties";
				PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
				HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
				
				String expectedcatmessage = data.get("expectedcatmessage");
				
				String actualcatmessage = successmsgEle.getText();
					if (actualcatmessage.contains(expectedcatmessage))
					{
					System.out.println("The third validation on success message after saving record...");
					System.out.println("The success message on saving the record: " +actualcatmessage);
					Assert.assertTrue(true);
					}
					else
					{
						Assert.fail();
					}
				return(actualcatmessage);
			}
			
	//This is a method to validate the success message after saving the record
			public String validationOnEditSuccessMessage() throws IOException
			{
				String actualsuccessmessage = successmsgEle.getText();
					if (actualsuccessmessage.contains("Success: You have successfully updated category!"))
					{
					System.out.println("The third validation on success message after editing record...");
					System.out.println("The success message on saving the record: " +actualsuccessmessage);
					Assert.assertTrue(true);
					}
					else
					{
						Assert.fail();
					}
				return(actualsuccessmessage);
			}
			
	//This is a method to validate that record is inserted into the list
			public void validationOnAddRecord() throws FileNotFoundException, IOException
			{
				Properties p = new Properties();
				p.load(new FileInputStream("./TestData/magentodata.properties"));
			
				String pagesource = driver.getPageSource();
								
				if(pagesource.contains(p.getProperty("catname"))) {
					System.out.println("The validation on presence of record in the Admin panel table is confirmed!");
					Assert.assertTrue(true);
				}
				else {
					System.out.println("The category name is not added to this list");
					Assert.fail();
				}
			}
	//This is a method to validate that record is updated into the list
			public void validationOnEditRecord() throws FileNotFoundException, IOException
			{
				Properties p = new Properties();
				p.load(new FileInputStream("./TestData/magentodata.properties"));
			
				String pagesource = driver.getPageSource();
								
				if(pagesource.contains(p.getProperty("catname"))) {
					System.out.println("The validation on presence of record in the Admin panel table is confirmed!");
					Assert.assertTrue(true);
				}
				else {
					System.out.println("The category name is not added to this list");
					Assert.fail();
				}
			}

}
