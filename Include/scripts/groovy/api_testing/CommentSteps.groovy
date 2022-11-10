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



class CommentSteps {
	String request;
	ResponseObject response;

	@Given("I have GET comment request")
	def haveGetCommentRequest() {
		request = 'Object Repository/API_Testing/Comments/GET Comment for Product';
	}

	@When("I send GET comment request")
	def sendGetCommentRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@Then("I receive success response codes 200")
	def receiveSuccessResponseCodes200() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeSucesss)
	}

	@And("I receive valid response for get comment")
	def receiveValidResponseForGetComment() {
		WS.verifyElementPropertyValue(response, 'data', '[]')
	}

	@Given("I have GET comment with invalid endpoint request")
	def haveGetCommentgWithInvalidEndpointRequest() {
		request = 'Object Repository/API_Testing/Comments/GET Comment for Product with Invalid Endpoint';
	}

	@Then("I receive not found response codes 404")
	def receiveNotFoundResponseCodes404() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeNotFound)
	}

		@Given("I have POST comment request")
	def havePostCommentRequest() {
		request = 'Object Repository/API_Testing/Comments/Create Comment for Product';
	}

	@When("I send POST comment request")
	def sendPostCommentRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@And("I receive valid response for give comment")
	def receiveValidResponseForGiveComment() {
		WS.verifyElementPropertyValue(response, 'data.Content', 'Kurir ramah, kualitas produk baik')
	}

	@Given("I have POST comment without token request")
	def havePostCommentWithoutTokenRequest() {
		request = 'Object Repository/API_Testing/Comments/Create Comment for Product Without Token';
	}

	@Then("I receive unauthorized response codes 401")
	def receiveUnauthorizedResponseCodes401() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeUnauthorized)
	}


	@And("I receive valid response for give comment without token")
	def receiveValidResponseForGiveCommentWithoutToken() {
		WS.verifyElementPropertyValue(response, 'error', 'unauthorized')
	}
}