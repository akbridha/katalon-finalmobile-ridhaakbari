import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// // Assert text sama.. statis dulu. nanti dibuat dinamis
// Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_laptop'), 0)
// Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_headphones'), 0)
// Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_tablets'), 0)
// Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_speakers'), 0)
// Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_mice'), 0)


// Daftar kategori masukkan dalam array
// String[] categories = ['LAPTOPS', 'HEADPHONES', 'TABLETS', 'SPEAKERS', 'MICE']
String[] categories = categories ?: ['LAPTOPS', 'HEADPHONES', 'TABLETS', 'SPEAKERS', 'MICE']

// Looping untuk verifikasi setiap kategori
for (String category : categories) {
	TestObject testObject = findTestObject('Object Repository/home/text_category', ['category': category])
	Mobile.verifyElementVisible(testObject, 0)
}
