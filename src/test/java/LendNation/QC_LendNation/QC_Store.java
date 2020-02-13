package LendNation.QC_LendNation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class QC_Store {
	public static String FileName;
	public static WebDriverWait wait;
	public static WebDriver driver;
	String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());
	public static ExtentReports reports;
	public static ExtentTest test;
	public static Properties prop;
	public static String FirstName;
	public static String passwrd;
	public static String report_filename;
	public static String LastName;
	public static String ESign_CheckNbr;
	public static String loan_nbr;
	public static Excel TestData;
	public static String ESign_CollateralType;
	
	
	//************************************************ PDL ****************************************************************************************	
	@Test(priority=22,enabled=false)

	public void QC_EPP_Redeposit() throws Exception{

		FileName="QC_EPP_Redeposit.xls";
		test=reports.startTest("Scenario_NO.56","EPP --> EPP payment ---> Deposit-->Return-->Redeposit");
		  TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
	
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 

				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QcEPP.epp(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QC_EPPPayment.epppayment(SSN, AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QCDepositDropdown.depositDropDown(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				InternalTfAndCashManagement.internaltf(SSN, AppURL);
				CSRLoginLogout.logout();

				QCAdminLoginLogout.login(SSN,AppURL);
				QC_EPP_Return.qcReturn(SSN,AppURL);
				QCAdminLoginLogout.logout(SSN, AppURL);

				CSRLoginLogout.login(SSN,AppURL);
				QC_Redeposit.redeposit(SSN,AppURL);
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "*************Test Scenario for EPP Redeposit is pass *************");
			}
		}}


	@Test(priority=21,enabled=false)

	public void QC_EPP_nsfPayment() throws Exception{

		FileName="QC_EPP_nsfPayment.xls";
		test=reports.startTest("Scenario_NO.55","EPP --> EPP payment ---> Deposit-->Return-->NsfPayment");
		TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 

				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QcEPP.epp(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QC_EPPPayment.epppayment(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QCDepositDropdown.depositDropDown(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				InternalTfAndCashManagement.internaltf(SSN, AppURL);
				CSRLoginLogout.logout();

				QCAdminLoginLogout.login(SSN,AppURL);
				QC_EPP_Return.qcReturn(SSN,AppURL);
				QCAdminLoginLogout.logout(SSN, AppURL);

				CSRLoginLogout.login(SSN,AppURL);
				QC_NSFPayment.nsfpayment(SSN, AppURL);
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "*************Test Scenario for EPP Nsf Payment is pass *************");
			}
		}}

	@Test(priority=20,enabled=false)

	public void QC_EPP_Return() throws Exception{

		FileName="QC_EPP_Prepayment_Return.xls";
		test=reports.startTest("Scenario_NO.54","EPP --> EPP payment ---> Deposit--> prepayment --> Return");
		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 

				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QcEPP.epp(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QC_EPPPayment.epppayment(SSN, AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QCDepositDropdown.depositDropDown(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				QC_Prepayment.Prepayment(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				InternalTfAndCashManagement.internaltf(SSN, AppURL);
				CSRLoginLogout.logout();
				
				QCAdminLoginLogout.login(SSN,AppURL);
				QC_EPP_Return.qcReturn(SSN,AppURL);
				QCAdminLoginLogout.logout(SSN, AppURL);

				test.log(LogStatus.INFO, "****Test Scenario for EPP Return is pass******");
			}
		}}


	@Test(priority=19,enabled=false)

	public void QC_EPP_refund() throws Exception{

		FileName="QC_EPP_Refund.xls";
		test=reports.startTest("Scenario_NO.53","EPP --> EPP payment ---> Deposit--> prepayment --> clear--> refund");


	 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 

				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QcEPP.epp(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QC_EPPPayment.epppayment(SSN, AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QCDepositDropdown.depositDropDown(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				InternalTfAndCashManagement.internaltf(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				QC_Prepayment.Prepayment(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				EPPClearOnly.eppClear(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				QC_EPPRefund.qcRefund(SSN, AppURL);
				CSRLoginLogout.logout();


				test.log(LogStatus.INFO, "****Test Scenario for EPP refund is pass******");
			}
		}}

	@Test(priority=18,enabled=false)

	public void QC_EPP_clear() throws Exception{

		FileName="QC_EPP_Clear.xls";
		test=reports.startTest("Scenario_NO.52","EPP -->EPP payment-->Deposit--> clear");


		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 

				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QcEPP.epp(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QCDepositDropdown.depositDropDown(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				QC_EPPClear.eppClear(SSN, AppURL);;
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "************Test Scenario for EPP Clear is pass**************");
			}
		}}


	@Test(priority=17,enabled=false)

	public void QC_EPP_Prepayment() throws Exception{

		FileName="QC_EPP_Prepayment.xls";
		test=reports.startTest("Scenario_NO.51","EPP -->EPP payment--> Deposit---> prepayment");


		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 

				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QcEPP.epp(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QCDepositDropdown.depositDropDown(SSN, AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				QC_Prepayment.Prepayment(SSN, AppURL);
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "****Test Scenario for EPP Prepayment is pass******");
			}
		}}




	@Test(priority=16,enabled=false)

	public void QC_EPPDeposit() throws Exception{

		FileName="QC_EPPDeposit.xls";
		test=reports.startTest("Scenario_NO.50","Loan-EPP--> age the store upto 1st installment --> perform Deposit");


		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);

			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 

				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QcEPP.epp(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				QcEPP.scheduler();
				CSRLoginLogout.login(SSN,AppURL);
				QCDepositDropdown.depositDropDown(SSN, AppURL);
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "****Test Scenario for EPP deposit is pass******");
			}
		}}



	//This test case is for borrower registration and new loan processing
	@Test(priority=1,enabled=true)
	public void borrowregNewloan() throws Exception{
		FileName=  "QC_BorrowerRegistration_NewLoan.xls";
		test=reports.startTest("Scenario_NO.01","Login-->Home Screen-->Borrower Registration-->New Loan");


		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout ();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				//CSRLoginLogout.login();
				//QCCSRHistory.history();
			}}}

	// This test case is to check status for the customer and apply loan for inactive customers 
	@Test(priority=2,enabled=false)
	public void inactiveNewloan() throws Exception
	{	 
		FileName=  "QC_NewLoan_InactiveCustomer.xls";
		test=reports.startTest("Scenario_NO.02","Login-->Home Screen-->In active customer-->New Loan");


		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanInactivecust.newLoanInact(SSN,AppURL);
				CSRLoginLogout.logout();
			}
		}}
	//This test case is to check customer max loan count
	/* Make sure that  "ESign_LoanAmt" column having an amount 50 and for pdl max loan count for customer
    	10 and maximum loan 500
	 */ 
	@Test(priority=6,enabled=false)

	public void maxloanCount() throws Exception{
		FileName=  "QC_MaxLoanCount_Testdata.xls";
		test=reports.startTest("Scenario_NO.05","Login-->Borrower-->Max loan count");


		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
		
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				Thread.sleep(2000);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();

				for(int i=1;i<=11;i++){

					CSRLoginLogout.login(SSN,AppURL);
					MaxLoanCount.maxLoan(SSN,AppURL);
					if(!(i==11))
					{
						test.log(LogStatus.INFO, "******Customer loan Number "+i+"is approved successfully*****");
					}
					else{
						test.log(LogStatus.INFO, "******Customer loan Number "+i+"is denied*****");
					}
				}
				test.log(LogStatus.INFO, "****Test Scenario for max loan amount is pass******");

			}}}

	@Test(priority=4,enabled=false)

	public void rescindloan() throws Exception{
		test=reports.startTest("Scenario_NO.10","Loan->Rescind");
		FileName=  "QC_BorrowerReg_NewLoan_Rescind_Txn_Testdata.xls";

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				Thread.sleep(3000);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRRescindLoan.rescind(SSN,AppURL);
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "****Test Scenario for  loan rescind  is pass******");
				//QCCSRHistory.history();
			}
		}}

	@Test(priority=5,enabled=false)

	public void agerescindtest() throws Exception{
		test=reports.startTest("Scenario_NO.11","Login-->Age the loan to rescind days--->Rescind loan");
		FileName=  "QC_BorrowerReg_NewLoan_AgeRescind_Txn_Testdata.xls";


		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);

			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRRescindLoan.agerescind(SSN,AppURL);
				
				CSRLoginLogout.login(SSN,AppURL);
				CSRRescindLoan.rescind(SSN,AppURL);
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "****Test Scenario for age to store loan rescind  is pass******");

			}}}

	//Ach deposit from drop down
	@Test(priority=3,enabled=false)
	public void Achdeposit() throws Exception{
		test=reports.startTest("Scenario_NO.12","Login-->Age the stote up to due date--->Perform deposit");
		FileName=  "QC_BorrowerReg_NewLoan_AchDeposit_Txn_Testdata.xls";

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);

			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.achDeposit(SSN,AppURL);
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "****Test Scenario for Ach Deposit  is pass******");
			}}}
	//ACH deposit from safe
	/* Give this test case as 0 priority mandatory */
	@Test(priority=0,enabled=false)
	public void middayDeposit() throws Exception{
		FileName="QC_BorrowerReg_NewLoan_MidDayDeposit_Txn_Testdata.xls";
		test=reports.startTest("Scenario_NO.14","Login-->Age the stote up to due date--->Perform Mid day deposit");

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);

			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRMidDayDeposit.middeposit();
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "****Test Scenario for MidDay Deposit  is pass******");
			}}}

	//Adding 5 days to due date and perform midday deposit
	@Test(priority=7,enabled=false)

	public void GracedaysMiddayDeposit() throws Exception{

		FileName="QC_AgeStoretoduedate+Gracedays_MiddayDeposit.xls";
		test=reports.startTest("Scenario_NO.17","Login-->Age the stote up to due date+Gracedays--->Perform Mid day deposit");

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);

			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				RunschedulerGracedays.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRMidDayDeposit.middeposit();
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "****Test Scenario for GraceDays MidDay Deposit  is pass******");
			}}}

	//Adding 5 days to due date and perform  deposit from deposit menu
	@Test(priority=8,enabled=false)

	public void GracedaysDepositMenu() throws Exception{

		FileName="QC_AgeStoretoduedate+Gracedays_DepositMenu.xls";
		test=reports.startTest("Scenario_NO.19","Login-->Age the stote up to due date+Gracedays--->Perform DepositMenu");

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			//System.out.println(RunFlag);
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				RunschedulerGracedays.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QC_CSRDepositMenu.depositMenu(SSN,AppURL);
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "****Test Scenario for GraceDays DepositMenu  is pass******");
			}}}
	//Age the store to 3 days and perform partial payment and age the store to due date and perform re finance step up
	@Test(priority=9,enabled=false)

	public void RefinanceStepup() throws Exception{

		FileName="QC_AgeStore_payment_AgetoDuedate_RefinanceStepUp.xls";
		test=reports.startTest("Scenario_NO.27","Login-->Age the Store_payment_Age store to Duedate_Refinance Stepup");

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
		
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRRescindLoan.agerescind(SSN,AppURL);
				
				CSRLoginLogout.login(SSN,AppURL);
				QCRefinanace.payment(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				RefinanceStepup.StepUp(SSN,AppURL);
				CSRLoginLogout.logout();

				test.log(LogStatus.INFO, "****Test Scenario for Refinance Stepup  is pass******");
			}}}

	//Age the store to 3 days and perform partial payment and age the store to due date and perform re finance step same
	@Test(priority=10,enabled=false)

	public void RefinanceStepSame() throws Exception{

		FileName="QC_AgeStore_payment_AgetoDuedate_RefinanceStepSame.xls";
		test=reports.startTest("Scenario_NO.29","Login-->Age the Store_payment_Age store to Duedate_Refinance StepSame");

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
	
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRRescindLoan.agerescind(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QCRefinanace.payment(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				RefinanceStepSame.StepSame(SSN,AppURL);
				CSRLoginLogout.logout();

				test.log(LogStatus.INFO, "****Test Scenario for Refinance StepSame  is pass******");
			}}}

	@Test(priority=11,enabled=false)

	public void RefinanceStepupVoid() throws Exception{

		FileName="QC_RefinanceStepup_void.xls";
		test=reports.startTest("Scenario_NO.30","Login-->Age the Store_payment_Age store to Duedate_Refinance Stepup-->void");

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);

			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRRescindLoan.agerescind(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QCRefinanace.payment(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				RefinanceStepup.StepUp(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				QCCSRVoid.QcVoid(SSN,AppURL);
				CSRLoginLogout.logout();

				test.log(LogStatus.INFO, "****Test Scenario for Refinance Step up Void  is pass******");
			}}}

	@Test(priority=12,enabled=false)

	public void RefinanceStepSameVoid() throws Exception{

		FileName="QC_RefinanceStep_Same_void.xls";
		test=reports.startTest("Scenario_NO.32","Login-->Age the Store_payment_Age store to Duedate_Refinance StepSame void");

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRRescindLoan.agerescind(SSN,AppURL);
				
				CSRLoginLogout.login(SSN,AppURL);
				QCRefinanace.payment(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				RefinanceStepSame.StepSame(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				QCCSRVoid.QcVoid(SSN,AppURL);	
				CSRLoginLogout.logout();

				test.log(LogStatus.INFO, "****Test Scenario for Refinance Step Same void is pass******");
			}}}

	//This needs to be 0 or top priority
	@Test(priority=13,enabled=false)
	public void futureMidayDeposit() throws Exception{
		FileName="QC_AgeStoretoduedate_FutureDeposit_MiddayDeposit.xls";
		test=reports.startTest("Scenario_NO.21","Loan-->Age the store upto duedate--> perform future deposit—->age the store uptofuture deposit date--->perform Deposit(Mid Day)");

		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRACHDeposit.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRFutureDeposit.futureDeposit(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				RunschedulerGracedays.runscheduler(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRMidDayDeposit.middeposit();
				CSRLoginLogout.logout();
				test.log(LogStatus.INFO, "****Test Scenario for future MidDay Deposit  is pass******");
			}
		}}

	@Test(priority=14,enabled=false)

	public void rebateStepup() throws Exception{

		FileName="QC__RefinanceStepUp_rebate.xls";
		test=reports.startTest("Scenario_NO.33","Loan-Age the store--make a payment -- age the store-->do refinance(step up)(loan should be under rebate period)");


		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
			
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRRescindLoan.agerescind(SSN,AppURL);
				
				CSRLoginLogout.login(SSN,AppURL);
				QCRefinanace.payment(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				UnderRebateAge.agerescind();
				CSRLoginLogout.login(SSN,AppURL);
				UnderRebateStepup.StepUp(SSN,AppURL);
				CSRLoginLogout.logout();

				test.log(LogStatus.INFO, "****Test Scenario for Refinance Stepup loan under rebate period  is pass******");
			}
		}}


	@Test(priority=15,enabled=false)

	public void rebateStepupVoid() throws Exception{

		FileName="QC__RefinanceStepUp_rebate_Void.xls";
		test=reports.startTest("Scenario_NO.36","Loan-Age the store--make a payment -- age the store-->do refinance(step up)(loan should be under rebate period)-->Void");


		 TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
		String sheetName="Start";
		int lastrow=TestData.getLastRow("Start");
		System.out.println(lastrow);
		for(int row=2;row<=lastrow;row++)
		{
			String RunFlag = TestData.getCellData(sheetName,"Run",row);
	
			if(RunFlag.equals("Y"))
			{	
				String AppURL = TestData.getCellData(sheetName,"AppURL",row); 
				String SSN = TestData.getCellData(sheetName,"SSN",row); 
				
				CSRLoginLogout.login(SSN,AppURL);
				CSRBorrowerRegistration.borrowerReg(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				CSRNewLoanPage.newLoan(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				CSRRescindLoan.agerescind(SSN,AppURL);
				CSRLoginLogout.login(SSN,AppURL);
				QCRefinanace.payment(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				UnderRebateAge.agerescind();
				CSRLoginLogout.login(SSN,AppURL);
				UnderRebateStepup.StepUp(SSN,AppURL);
				CSRLoginLogout.logout();
				CSRLoginLogout.login(SSN,AppURL);
				QCCSRVoid.QcVoid(SSN,AppURL);
				CSRLoginLogout.logout();

				test.log(LogStatus.INFO, "****Test Scenario for Refinance Stepup loan under rebate period and void is pass******");
			}
		}}
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method.
			String screenshotPath = TestBase.getScreenhot(driver, result.getName());
			//To add it in the extent report
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(LogStatus.PASS, result.getName()+" Test Case is Passed");}

	}
	@BeforeClass
	//public void callSetup() throws Exception  {
	// TestBase.setup();
	//This method to generate extent reports and driver initialization
	public static void setup() throws IOException {

		try{ String timestamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());

		report_filename="QC__Store_Execution_Report_"+timestamp+".html";
		reports = new ExtentReports(System.getProperty("user.dir") + "/ExecutionReports/QC_CSR/"+report_filename,true);
		//      reports.addSystemInfo("Browser Version","Chrome 69");

		//****Browser initializations
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer.exe");
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/chromedriver.exe");
		driver = new InternetExplorerDriver();
		//driver=new ChromeDriver();

		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(05, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(03, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10000);

		BufferedReader reader;
		reader = new BufferedReader(new FileReader("C:/Selenium WebDriver/QC_LendNation/src/test/java/LendNation/QC_LendNation/Objects.properties"));
		prop = new Properties();

		prop.load(reader);
		reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@AfterTest
	public void endReport(){
		reports.flush();
		//driver.quit();

	}
	public  static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());

		File source = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/ExecutionReports/LendNation/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	public static By locator(String obj)
	{

		String loctype=null;
		String locname=null;
		By locator=null;
		String[] locobj=obj.split("%%"); 
		loctype=locobj[0];
		locname=locobj[1];

		if(loctype.equalsIgnoreCase("id"))
			return locator=By.id(locname);
		else if(loctype.equalsIgnoreCase("name"))
			return locator=By.name(locname);
		else if(loctype.equalsIgnoreCase("linkText"))
			return locator=By.linkText(locname);
		else if(loctype.equalsIgnoreCase("xpath"))
			return locator=By.xpath(locname);
		else if(loctype.equalsIgnoreCase("cssSelector"))
			return locator=By.cssSelector(locname);
		return locator;

	}


}
