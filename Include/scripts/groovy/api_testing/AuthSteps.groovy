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



class AuthSteps {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	ResponseObject response;
	String request;
	@Given("I have GET user auth info request")
	def haveGetUserAuthInfoRequest() {
		request = 'API_Testing/Authentication/GET User Information';
	}

	@When("I send GET auth request")
	def sendGetAuthRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@Then("I receive response code 200")
	def receiveResponseCode200() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeSucesss)
	}

	@And("I receive valid response for user auth info")
	def receiveValidResponseForUserAuthInfo() {
		WS.verifyElementPropertyValue(response, 'data.ID', 77)
		WS.verifyElementPropertyValue(response, 'data.Email', 'joko@jmail.com')
		WS.verifyElementPropertyValue(response, 'data.Password', 'joko123')

		WS.verifyElementPropertyValue(response, 'data.Fullname', 'Joko')
	}

	@Given("I have GET user auth info request without token")
	def haveGetUserAuthInfoRequestWithoutToken() {
		request = 'Object Repository/API_Testing/Authentication/GET User Information Without Token';
	}

	@Then("I receive response code 401")
	def receiveResponseCode401() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeUnauthorized)
	}

	@And("I receive error response for user auth info")
	def receiveErrordResponseForUserAuthInfo() {
		WS.verifyElementPropertyValue(response, 'error', 'unauthorized')
	}

	@Given("I have POST user login existing account")
	def havePostUserLoginExistingAccount() {
		request = 'Object Repository/API_Testing/Authentication/Login';
	}

	@When("I send POST auth request")
	def sendPostAuthRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@And("I receive valid response for user login existing account")
	def receiveValidResponseForUserLoginExistingAccount() {
		WS.containsString(response, 'data', false)
	}

	@Given("I have POST user login with unregistered email")
	def havePostUserLoginWithUnregisteredEmail() {
		request = 'Object Repository/API_Testing/Authentication/Login Unregistered Email';
	}

	@Then("I receive response code 400")
	def receiveResponseCode400() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeBadRequest)
	}

	@And("I receive valid response for user login with unregistered email")
	def receiveValidResponseForUserLoginWithUnregisteredEmail() {
		WS.verifyElementPropertyValue(response, 'error', 'record not found')
	}

	@Given("I have POST user register with blank password")
	def havePostUserRegisterWithBlankPassword() {
		request = 'API_Testing/Authentication/RegisterWithBlankPassword';
	}


	@And("I receive valid response for user register with blank password")
	def receiveValidResponseForUserRegisterWithBlankPassword() {
		WS.verifyElementPropertyValue(response, 'error', 'password is required')
	}

	@Given("I have POST user create new account request")
	def havePostUserCreateNewAccountRequest() {
		request = 'API_Testing/Authentication/Register';
	}


	@And("I receive valid response for user create new account")
	def receiveValidResponseForUserCreateNewAccount() {
		WS.verifyElementPropertyValue(response, 'data.Email', 'markyyyy@mmail.com')
		WS.verifyElementPropertyValue(response, 'data.Password', 'markyyy123')
		WS.verifyElementPropertyValue(response, 'data.Fullname', 'Markyyyy')
	}
}