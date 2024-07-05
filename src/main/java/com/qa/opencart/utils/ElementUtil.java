package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.qa.opencart.exceptions.ElementException;



public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;

	}

	private void nullCheck(String value) {

		if (value == null) {

			throw new ElementException("VALUE IS NULL" + value);

		}
	}

	public void doSendKeys(By locator, String value) {

		nullCheck(value);
		getElement(locator).sendKeys(value);

	}

	public WebElement getElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			return element;
		} catch (NoSuchElementException e) {
			System.out.println("Element is not present on the page" + locator);
			e.printStackTrace();
			return null;
		}

	}

	public String doGetText(By locator) {

		return getElement(locator).getText();

	}

	public String doGetAttribute(By locator, String attrName) {

		return getElement(locator).getAttribute(attrName);

	}

	public boolean doIsDisplayed(By locator) {
		try {
			boolean flag = getElement(locator).isDisplayed();
			System.out.println("element is dislayed :" + locator);
			return flag;
		} catch (NoSuchElementException e) {
			System.out.println("element with locator:" + locator + "is not displayed");
			return false;
		}

	}

	public boolean isElementDisplayed(By locator) {

		int elementCount = getElements(locator).size();
		if (elementCount == 1) {
			System.out.println("single element is displayed: " + locator);
			return true;
		} else {
			System.out.println("Multiple or zero elements are displayed " + locator);
			return false;
		}
	}

	public boolean isElementDisplayed(By locator, int expectedElementCount) {
		int elementCount = getElements(locator).size();
		if (elementCount == expectedElementCount) {
			System.out.println("element is displayed: " + locator + "with the occurrence of" + elementCount);
			return true;
		} else {
			System.out.println(
					"Multiple or zero elements are displayed " + locator + "with the occurrence of" + elementCount);
			return false;
		}

	}

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);

	}

	public int getElementsCount(By locator) {

		return getElements(locator).size();
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> elementList = getElements(locator);
		List<String> eletextList = new ArrayList<String>();

		for (WebElement e : elementList) {
			String text = e.getText();
			if (text.length() != 0) {
				eletextList.add(text);

			}
		}

		return eletextList;

	}

	public List<String> getElementAttributeList(By locator, String attrName) {

		List<WebElement> imagesList = getElements(locator);
		List<String> attrList = new ArrayList<String>();

		for (WebElement e : imagesList) {

			String attrVal = e.getAttribute(attrName);
			if (attrVal != null && attrVal.length() != 0) {

				attrList.add(attrVal);
				System.out.println(attrVal);
			}

		}

		return attrList;

	}

	// **********************select dropdown utils****************************

	public void doSelectByIndex(By locator, int index) {

		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void doSelectByVisibleText(By locator, String text) {

		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);

	}

	public void doSelectByValue(By locator, String value) {

		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	public List<String> getDropdownOptionsTextList(By locator) {

		Select select = new Select(getElement(locator));

		List<WebElement> optionsList = select.getOptions();
		List<String> optionsTextList = new ArrayList<String>();

		for (WebElement e : optionsList) {

			String text = e.getText();
			optionsTextList.add(text);
		}

		return optionsTextList;

	}

	public int getDropdownOptionsCount(By locator) {

		Select select = new Select(getElement(locator));
		return select.getOptions().size();

	}

	public void selectValueFromDropdown(By locator, String optionText) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(optionText.trim())) {
				e.click();
				break;
			}
		}
	}

	public void selectValueFromDropdownWithoutSelectClass(By locator, String optionText) {
		List<WebElement> optionList = driver.findElements(locator);
		System.out.println(optionList.size());

		for (WebElement e : optionList) {
			String text = e.getText();
			if (text.equals(optionText)) {
				e.click();
				break;
			}
		}
	}

	public void doDeselectByVisibleText(By locator, String text) {

		Select select = new Select(getElement(locator));
		select.deselectByVisibleText(text);

	}

	public void doSearch(By searchField, String searchkey, By suggestions, String value) throws InterruptedException {

		doSendKeys(suggestions, value);
		Thread.sleep(3000);
		List<WebElement> suggList = getElements(suggestions);
		System.out.println(suggList.size());

		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(value)) { // here the contains method will search for case sensitive text
				e.click();
				break;
			}
		}

	}

}
