package com.nerddash.aulago.api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public abstract class AbstractWebDriverTestClass {
	
	protected String endereco;
	protected final String baseUrl = "http://localhost:8080/com.nerddash";
	protected static WebDriver driver;
	
	public AbstractWebDriverTestClass(String endereco) {
		this.endereco = baseUrl + endereco;
	}

	public void acessa() {
		driver.get(endereco);
	};

	public String getEndereco() {
		return endereco;
	}

	@BeforeClass
	public static  void setUpBeforeClass() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.EDGE);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
