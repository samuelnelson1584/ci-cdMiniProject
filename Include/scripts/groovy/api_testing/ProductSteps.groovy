package api_testing
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class ProductSteps {
	ResponseObject response;
	String request;

	@Given("I have GET product by ID request")
	def haveGetProductByIdRequest() {
		request = 'API_Testing/Products/GET Products by ID';
	}

	@When("I send GET product request")
	def sendGetProductRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@Then("I receive response codes 200")
	def receiveResponseCode200() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeSucesss)
	}

	@And("I receive valid response for get product by ID")
	def receiveValidResponseGetProductByID() {
		WS.verifyElementPropertyValue(response, 'data.ID', 92)

		WS.verifyElementPropertyValue(response, 'data.Name', 'Atari Jaguar')

		WS.verifyElementPropertyValue(response, 'data.Description', 'home video game console from 1993')

		WS.verifyElementPropertyValue(response, 'data.Price', 0)

		WS.verifyElementPropertyValue(response, 'data.Categories[0].ID', 2)
	}

	@Given("I have GET product by invalid ID request")
	def haveGetProductByInvalidIdRequest() {
		request = 'API_Testing/Products/GET Products by Invalid ID';
	}

	@Then("I receive response codes 400")
	def receiveResponseCodes400() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeBadRequest)
	}

	@And("I receive error response for get product by invalid ID")
	def receiveErrorResponseForInvalidId() {
		WS.containsString(response, 'error', false)
	}

	@Given("I have GET all product request")
	def haveGetAllProductRequest() {
		request = 'API_Testing/Products/GET All Products';
	}
	@And("I receive valid response for get all product")
	def receiveValidResponseForGetAllProduct() {
		WS.verifyElementPropertyValue(response, 'data[0].ID', 16)
		WS.verifyElementPropertyValue(response, 'data[0].Name', 'Sony PS5')
		WS.verifyElementPropertyValue(response, 'data[0].Description', 'play has no limits')
		WS.verifyElementPropertyValue(response, 'data[0].Price', 299)
		WS.verifyElementPropertyValue(response, 'data[0].Categories[0].ID', 2)
	}

	@Given("I have GET all product with invalid endpoint request")
	def haveGetAllProductWithInvalidEndpointRequest() {
		request = 'Object Repository/API_Testing/Products/GET All Products with Invalid Endpoint';
	}

	@Then("I receive response codes 404")
	def receiveResponseCodes404() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeNotFound)
	}
	//	@And("I receive valid response for get all product with invalid endpoint")
	//	def receiveValidResponseForGetAllProductWithInvalidEndpoint() {
	//	}

	@Given("I have DELETE product request")
	def haveDeleteProductRequest() {
		request = 'API_Testing/Products/DELETE Products';
	}

	@When("I send DELETE product request")
	def sendDeleteProductRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@And("I receive valid response for delete product")
	def receiveValidResponseDeleteProduct() {
		WS.verifyElementPropertyValue(response, 'data', 'null')
	}

	@Given("I have POST new product request")
	def havePostNewProductRequest() {
		request = 'API_Testing/Products/Create New Product';
	}

	@When("I send POST new product request")
	def sendPostProductRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@And("I receive valid response for new product")
	def receiveValidResponseNewProduct() {
		WS.verifyElementPropertyValue(response, 'data.Name', 'Atari Jaguar')

		WS.verifyElementPropertyValue(response, 'data.Description', 'home video game console from 1993')

		WS.verifyElementPropertyValue(response, 'data.Price', 900)

		WS.verifyElementPropertyValue(response, 'data.Categories[0].ID', 2)
	}
}