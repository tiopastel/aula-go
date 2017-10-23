package com.nerddash.aulago.API;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import io.restassured.RestAssured;

public abstract class AbstractRestApiTest{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		RestAssured.baseURI = "http://localhost:8080/com.nerddash";
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

}
