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



class OrderSteps {
	ResponseObject response;
	String request;

	@Given("I have GET order history request")
	def haveGetAllOrderRequest() {
		request = 'Object Repository/API_Testing/Orders/GET All Orders';
	}

	@When("I send GET order request")
	def sendGetOrderRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@Then("I receive response code success 200")
	def receiveResponsesCodeSuccess200() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeSucesss)
	}

	@And("I receive valid response for get order history")
	def receiveValidResponseForGetAllOrder() {
		WS.verifyElementPropertyValue(response, 'data[0].Product', "a")
		WS.verifyElementPropertyValue(response, 'data[0].Price', 299)
		WS.verifyElementPropertyValue(response, 'data[0].Quantity', 1)
		WS.verifyElementPropertyValue(response, 'data[0].Subtotal', 299)
	}

	@Given("I have GET order history without token request")
	def haveGetAllOrderWithoutTokenRequest() {
		request = 'Object Repository/API_Testing/Orders/GET All Orders Without Token';
	}


	@Then("I receive response code unauthorized 401")
	def receiveResponsesCodeUnauthorized401() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeUnauthorized)
	}

	@And("I receive valid response for get order history without token")
	def receiveValidResponseForGetAllOrderWithoutToken() {
		WS.verifyElementPropertyValue(response, 'error', 'unauthorized')
	}

	@Given("I have GET order by id request")
	def haveGetOrderByIdRequest() {
		request = 'Object Repository/API_Testing/Orders/GET Order by ID';
	}

	@And("I receive valid response for get order by id")
	def receiveValidResponseForGetOrderById() {
		WS.verifyElementPropertyValue(response, 'data.ID', 1)
		WS.verifyElementPropertyValue(response, 'data.User.ID', 2)
		WS.verifyElementPropertyValue(response, 'data.Product', 'null')
		WS.verifyElementPropertyValue(response, 'data.Quantity', 1)
	}
	
	@Given("I have POST order request")
	def havePostOrderRequest() {
		request = 'Object Repository/API_Testing/Orders/Create New Order';
	}

	@And("I receive valid response for add order")
	def receiveValidResponseForAddOrderRequest() {
		WS.verifyElementPropertyValue(response, 'data[0].User.ID', 77)
		WS.verifyElementPropertyValue(response, 'data[0].Product.Name', 'Atari Jaguar')
		WS.verifyElementPropertyValue(response, 'data[0].Quantity', 1)
	}
	
	@When("I send POST order request")
	def sendPostOrderRequest() {
		response = WS.sendRequest(findTestObject(request))
	}
	
	@Given("I have POST order with invalid quantity request")
	def havePostOrderWithInvalidQuantityRequest() {
		request = 'Object Repository/API_Testing/Orders/Create New Order With Invalid Quantity';
	}

	@Then("I receive response code bad request 400")
	def receiveResponsesCodeBadRequest400() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeBadRequest)
	}
	

	@And("I receive valid response for add order with invalid quantity")
	def receiveValidResponseForAddOrderWithInvalidQuantityRequest() {
		WS.verifyElementPropertyValue(response, 'error', 'json: cannot unmarshal number -10 into Go struct field OrderCreate.quantity of type uint')
	}
}