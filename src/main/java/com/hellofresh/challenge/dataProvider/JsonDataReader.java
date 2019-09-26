package com.hellofresh.challenge.dataProvider;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hellofresh.challenge.pages.BasePage;

public class JsonDataReader {

	private static String dataFile = "src/test/resources/data.json";
	static Gson gson = new Gson();
	private static JsonObject jsonObject;
	private static JsonObject commondata;

	public static void initializeJSON() {
		try (FileReader reader = new FileReader(dataFile)) {
			jsonObject = gson.fromJson(new FileReader(dataFile), JsonObject.class);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		commondata = jsonObject.getAsJsonObject("Commondata");
	}

	public void fillCustomFields(BasePage basePage, String dataTarget) {
		JsonElement page = commondata.get(basePage.getClass().getSimpleName());
		JsonElement dataContainer = getDataContainer(page, dataTarget);
		fillFields(basePage, dataContainer);
	}

	public void verifyCustomFields(BasePage basePage, String dataTarget) {
		JsonElement page = commondata.get(basePage.getClass().getSimpleName());
		JsonElement dataContainer = getDataContainer(page, dataTarget);
		verifyFields(basePage, dataContainer);
	}

	public static JsonElement getDataContainer(JsonElement page, String dataTarget) {
		JsonObject dataContainer = page.getAsJsonObject();
		return dataContainer.get(dataTarget);
	}

	public void fillFields(BasePage id, JsonElement dataContainer) {
		LinkedHashMap<String, String> containerFields = getContainerFields(dataContainer);
		for (Map.Entry<String, String> entry : containerFields.entrySet()) {
			String locator = entry.getKey();
			String value = entry.getValue();
			Class<?> aClass = id.getClass();
			Logger log = Logger.getLogger(aClass);
			WebDriverWait wait = new WebDriverWait(id.driver, 20);
			try {
				Field field = aClass.getField(locator);
				WebElement element = ((WrapsElement) field.get(id)).getWrappedElement();
				if (locator.startsWith("tbx_")) {
					log.info("Filling " + locator + " with value : " + value + " ");
					element.clear();
					element.sendKeys(value);
				} else if (locator.startsWith("rbn_")) {
					log.info("Clicking " + locator + " with value : " + value + " ");
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
				} else if (locator.startsWith("ddl_")) {
					log.info("Selecting " + locator + " with value : " + value + " ");
					Select select = new Select(element);
					select.selectByValue(value);
				} else if (locator.startsWith("btn_")) {
					log.info("Clicking " + locator + " with value : " + value + " ");
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
				}
			} catch (Exception e) {
				log.error(e);
				throw new RuntimeException(e);
			}
		}
	}

	public void verifyFields(BasePage id, JsonElement dataContainer) {
		LinkedHashMap<String, String> containerFields = getContainerFields(dataContainer);
		for (Map.Entry<String, String> entry : containerFields.entrySet()) {
			String locator = entry.getKey();
			String value = entry.getValue();
			Class<?> aClass = id.getClass();
			Logger log = Logger.getLogger(aClass);

			try {
				Field field = aClass.getField(locator);
				WebElement element = ((WrapsElement) field.get(id)).getWrappedElement();
				log.info("Checking that " + locator + " is displayed... ");
				assertTrue(element.isDisplayed());
				if (locator.startsWith("tbx_") || locator.startsWith("lbl_")) {
					log.info("Checking that " + locator + " contains : " + value + " ");
					assertTrue(element.getText().contains(value),
							"Expected :" + value + " but found :" + element.getText() + " ");

				}
			} catch (Exception e) {
				log.error(e);
				throw new RuntimeException(e);
			}
		}
	}

	public static LinkedHashMap<String, String> getContainerFields(JsonElement dataContainer) {
		LinkedHashMap<String, String> fields = new LinkedHashMap<String, String>();
		JsonArray i = dataContainer.getAsJsonArray();
		for (JsonElement field : i) {
			try {
				String locator = field.getAsString().split(":")[0]; // tbx_name
				String value = field.getAsString().split(":")[1];
				fields.put(locator, value);
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new RuntimeException("Syntax error in JSON.My guess is you missed to give value");
			}

		}
		return fields;
	}

}