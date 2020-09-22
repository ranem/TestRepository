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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.testng.Assert as Assert

CustomKeywords.'com.database.DataBaseKeyword.connectDB'(GlobalVariable.host, GlobalVariable.dbname, GlobalVariable.port, 
    GlobalVariable.username, GlobalVariable.password)

res=CustomKeywords.'com.database.DataBaseKeyword.executeQuery'('SELECT * FROM public."UserInformation";')
System.out.println  res

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.URL)

WebUI.maximizeWindow()

//Click on element tab
WebUI.click(findTestObject('Page_ToolsQA/ModuleName'))

WebUI.click(findTestObject('Page_ToolsQA/TabName', [('tabname') : tabname]))

xpath = '//div[@class=\'main-header\']'

TestObject pagetitle = new TestObject('PageTitle')

pagetitle.addProperty('xpath', ConditionType.EQUALS, xpath, true)

title = WebUI.getText(pagetitle)

switch (tabname) {
    case 'Text Box':
        assert tabname == title

        break
    case 'Check Box':
        assert tabname == title

        break
    case 'Radio Button':
        assert tabname == title

        break
    case 'Web Tables':
        assert tabname == title

        break
    case 'Buttons':
        assert tabname == title

        break
    case 'Links':
        assert tabname == title

        break
    case 'Broken Links - Images':
        assert tabname == title

        break
    case 'Upload and Download':
        assert tabname == title

        break
    case 'Dynamic Properties':
        assert tabname == title

        break
}

WebUI.closeBrowser()

