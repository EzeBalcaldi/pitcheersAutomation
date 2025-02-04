package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.UserQuestionnairePage;
import pages.ContactInfoPage;
import pages.HomePage;

public class Test01_HappyPath {
	
    // Constants for the expected messages in the test
    private static final String EXPECTED_CONFIRMATION_PRICE = "Contratar por $942 por mes";
    private static final String EXPECTED_MAIN_TITLE = "Necesito conocerte un poco más:";
    private static final String EXPECTED_HEIGHT_INPUT = "180";
    private static final String EXPECTED_WEIGHT_INPUT = "70";
    private static final String EXPECTED_NAME_EMAIL_TITLE = "Dejanos tu nombre y tu email";
    private static final String EXPECTED_EMAIL_SENT_NOTIFICATION = "El e-mail fue enviado correctamente.";
    private static final String PAGE_URL = "https://purchase-testing.klimber.com/ar/GroupLife/Index";
	
    // WebDriver instance and Page Objects 
	private WebDriver driver;
	private HomePage homePage;
	private ContactInfoPage contactPage;
	private UserQuestionnairePage questionnairePage;
	
	//Pre-test configs
	@Before
	public void setUp() throws Exception{
		homePage = new HomePage(driver);
		driver = homePage.firefoxConnection();
		questionnairePage = new UserQuestionnairePage(driver);
		contactPage = new ContactInfoPage(driver);
		driver.manage().window().maximize();
		homePage.goToPage(PAGE_URL);
	}
	
	// Post-test configs
	@After
	public void tearDown() throws Exception{
		driver.quit();
	}
	
	// Test case: Executing the happy path scenario, filling all required information and validating the flow.
	@Test
	public void test() throws Exception{
		//Filling the required information, and selecting an assured sum using the slider 
		homePage.fillDateInput("01/12/1998");
		homePage.fillCellphoneInputs("123", "123456");
		homePage.selectProvince("CABA");
		homePage.selectAssuredSum();
		
		//Checking that the price of the service is the expected
        Assert.assertEquals(EXPECTED_CONFIRMATION_PRICE, homePage.getConfirmationPriceString());
		homePage.clickSave();
		
		//Checking the main title is correct so i can validate that we are on the questionnaire page
        Assert.assertEquals(EXPECTED_MAIN_TITLE, questionnairePage.getMainTitleText());
        
        //Filling inputs and checking radio button
		questionnairePage.checkAssistanceNeededRadioButton();
		questionnairePage.fillHeightInput(EXPECTED_HEIGHT_INPUT);
		questionnairePage.fillWeightInput(EXPECTED_WEIGHT_INPUT);
		
		//Asserting that Height and Weight are the expected
        Assert.assertEquals(EXPECTED_HEIGHT_INPUT, questionnairePage.getHeightInputValue());
        Assert.assertEquals(EXPECTED_WEIGHT_INPUT, questionnairePage.getWeightInputValue());
        
        //Saving the form and checking the Contact Page title
		questionnairePage.saveForm();
        Assert.assertEquals(EXPECTED_NAME_EMAIL_TITLE, contactPage.getNameAndEmailTitleValue());
        
        //Filling the inputs of Contact Page
		contactPage.fillNameInput("Ezequiel");
		contactPage.fillEmailInput("test@email.com");
		contactPage.fillPhoneInput("12345678");
		
		//Clicking on 'next' button and asserting that the Email Notification is displayed
		contactPage.clickNextButton();
        Assert.assertEquals(EXPECTED_EMAIL_SENT_NOTIFICATION, contactPage.getEmailSentNotificationText());
	}
	

}
