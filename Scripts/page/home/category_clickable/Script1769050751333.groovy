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

// Assert text sama.. statis dulu. nanti dibuat dinamis




//Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_laptop'), 0)
//Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_headphones'), 0)
//Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_tablets'), 0)
//Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_speakers'), 0)
//Mobile.verifyElementVisible(findTestObject('Object Repository/home/text_category_mice'), 0)



String[] categories = categories ?: ['LAPTOPS', 'HEADPHONES', 'TABLETS', 'SPEAKERS', 'MICE']

// Looping untuk verifikasi setiap kategori
for (String category : categories) {
	TestObject testObject = findTestObject('Object Repository/home/text_category', ['category': category])
    // statis untuk test 
	// TestObject testObject = findTestObject('Object Repository/home/text_category', ['category': categories[0]])
	// Mobile.tap(testObject, 0)
    // Mobile.waitForElementPresent(findTestObject('Object Repository/product/text_label_category'), 10)



	// pakai mekanisme cobaulang  tap hingga 3 kali jika element tidak muncul
	int maxRetries = 3
	boolean elementFound = false
	
	for (int attempt = 1; attempt <= maxRetries; attempt++) {
		println("Percobaan ke-${attempt} untuk kategori: ${category}")
		Mobile.tap(testObject, 0)
		
		// Cek apakah element muncul dengan try-catch
		try {
			Mobile.waitForElementPresent(findTestObject('Object Repository/product/text_label_category'), 5)
			println("Element berhasil ditemukan pada percobaan ke-${attempt}")
			elementFound = true
			break
		} catch (Exception e) {
			println("Element tidak ditemukan pada percobaan ke-${attempt}")
			if (attempt < maxRetries) {
				Mobile.pressBack() // Kembali ke halaman sebelumnya untuk retry
				Mobile.delay(1) // Tunggu sebentar sebelum retry
			}
		}
	}
	
	// Jika setelah 3 percobaan masih gagal, fail test
	if (!elementFound) {
		assert false : "Gagal menemukan element setelah ${maxRetries} percobaan untuk kategori: ${category}"
	}



// println(Mobile.getText(findTestObject('Object Repository/product/text_label_category'), 0))
//     assert Mobile.getText(findTestObject('Object Repository/product/text_label_category'), 0) == categories[0] : "Kategori tidak sesuai. Ditemukan: ${Mobile.getText(findTestObject('Object Repository/product/text_label_category'), 0)}"

// tidak selamat karena kemasukan tanda baca lebih. 
// yang mana padahal tanda lebih itulah penyelamat dari bug awal
    // Ambil text dari label kategori
    String actualText = Mobile.getText(findTestObject('Object Repository/product/text_label_category'), 0)
    println("Text asli dari element: ${actualText}")
    
    // Eliminasi tanda kurung dan isinya, lalu ubah ke uppercase
    String cleanedText = actualText.replaceAll(/\s*\(.*\)/, '').toUpperCase()
    println("Text setelah dibersihkan: ${cleanedText}")
    
    // statis 
    // assert cleanedText == categories[0] : "Kategori tidak sesuai. Expected: ${categories[0]}, Found: ${cleanedText}"
        // Bandingkan dengan expected dari iterator
    assert cleanedText == category : "Kategori tidak sesuai. Expected: ${category}, Found: ${cleanedText}"
    
  

    Mobile.pressBack()
}