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



class CategoriesSteps {

	ResponseObject response;
	String request;

	@Given("I have GET all categories request")
	def haveGetAllCategoryRequest() {
		request = 'API_Testing/Categories/GET All Categories';
	}

	@When("I send GET categories request")
	def sendGetCategoryRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@Then("I receive responses code 200")
	def receiveResponsesCode200() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeSucesss)
	}

	@And("I receive valid response for get all categories")
	def receiveValidResponseForGetAllCategory() {
		WS.verifyElementPropertyValue(response, 'data[0].ID', 2)
		WS.verifyElementPropertyValue(response, 'data[0].Name', 'gaming')
		WS.verifyElementPropertyValue(response, 'data[0].Description', 'for gaming purposes')
	}
	//	@Then("I receive response codes 400")
	//	def receiveResponseCodes400() {
	//		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeBadRequest)
	//	}

	@Given("I have GET all categories with invalid endpoint request")
	def haveGetAllCategoryWithInvalidEndpointRequest() {
		request = 'Object Repository/API_Testing/Categories/GET All Categories with Invalid Endpoint';
	}

	@Then("I receive responses code 404")
	def receiveResponsesCode404() {
		WS.verifyResponseStatusCode(response, GlobalVariable.ResponseCodeNotFound)
	}

	@Given("I have GET categories by id request")
	def haveGetCategoryByIdRequest() {
		request = 'Object Repository/API_Testing/Categories/GET Category by ID';
	}

	@And("I receive valid response for get categories by id")
	def receiveValidResponseForGetCategoryById() {
		WS.verifyElementPropertyValue(response, 'data.Name', 'Dolanan')
		WS.verifyElementPropertyValue(response, 'data.Description', 'Meh nggo dolanan')
	}

	@Given("I have POST new categories request")
	def havePostNewCategoryRequest() {
		request = 'Object Repository/API_Testing/Categories/Create New Category';
	}

	@When("I send POST categories request")
	def sendPostCategoryRequest() {
		response = WS.sendRequest(findTestObject(request))
	}

	@And("I receive valid response for new categories")
	def receiveValidResponseForNewCategory() {
		WS.verifyElementPropertyValue(response, 'data.Name', 'Fruit')
		WS.verifyElementPropertyValue(response, 'data.Description', 'sweet and fleshy product of a plant')
	}


}