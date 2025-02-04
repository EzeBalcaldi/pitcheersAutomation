package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pom.Base;

public class ContactInfoPage extends Base{

	public ContactInfoPage(WebDriver driver) {
		super(driver);
	}
	
	By nameInput = By.id("txtName");
	By phoneInput = By.id("PhoneNumber");
	By btnSave = By.id("btnSaveStep2_2");
	By emailInput = By.id("txtEmailFrom");
	By nameAndEmailTitle = By.className("custom-text");
	By emailSentNotification = By.className("ui-pnotify-text");

	public String getNameAndEmailTitleValue() {
		return this.getText(nameAndEmailTitle);
	}
	
	public void fillNameInput(String value) {
		this.write(nameInput, value);
	}
	
	public void fillEmailInput(String value) {
		this.write(emailInput, value);
	}
	
	public void fillPhoneInput(String value) {
		this.write(phoneInput, value);
	}
	
	public void clickNextButton() {
		this.click(btnSave);
	}
	
	public String getEmailSentNotificationText() {
		return this.getText(emailSentNotification);
	}
	

}
