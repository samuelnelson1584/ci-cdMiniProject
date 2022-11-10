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



class RatingStep {
	ResponseObject response;
	String request;

	@Given("I have GET rating request")
	def haveGetRatingRequest() {
		request = 'Object Repository/API_Testing/Ratings/GET Rating for Product';
	}

	@When("I send GET rating request")
	def sendGetRatingRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@Then("I receive response codes success 200")
	def receiveResponseCodesSuccess200() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeSucesss)
	}

	@And("I receive valid response for get rating")
	def receiveValidResponseForGetRating() {
		WS.verifyElementPropertyValue(response, 'data', 0)
	}

	@Given("I have GET rating with invalid endpoint request")
	def haveGetRatingWithInvalidEndpointRequest() {
		request = 'Object Repository/API_Testing/Ratings/GET Rating for Product With Invalid Endpoint';
	}

	@Then("I receive response codes not found 404")
	def receiveResponseCodesNotFound404() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeNotFound)
	}


	@Given("I have POST rating without token request")
	def havePostRatingRequestWithoutToken() {
		request = 'Object Repository/API_Testing/Ratings/Create Rating for Product Without Token';
	}

	@When("I send POST rating request")
	def sendPostRatingRequest() {
		response = WS.sendRequest(findTestObject(request))
	}
	@Then("I receive response codes unauthorized 401")
	def receiveResponseCodesUnauthorized401() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeUnauthorized)
	}

	@And("I receive valid response for give rating without token")
	def receiveValidResponseForPostRatingWithoutToken() {
		WS.verifyElementPropertyValue(response, 'error', 'unauthorized')
	}

	@Given("I have POST rating request")
	def havePostRatingRequest() {
		request = 'Object Repository/API_Testing/Ratings/Create Rating for Product';
	}

	@And("I receive valid response for give rating")
	def receiveValidResponseForPostRating() {
		WS.verifyElementPropertyValue(response, 'data.ID', 93)
		WS.verifyElementPropertyValue(response, 'data.Name', 'Atari Jaguar')
		WS.verifyElementPropertyValue(response, 'data.Ratings', 3)
	}
}