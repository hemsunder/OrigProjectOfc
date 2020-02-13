package LendNation.QC_LendNation;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;
public class QCAdminLoginLogout extends QC_Store {


	public static void login(String SSN, String AppURL)
	{
		try{
			Excel TestData = new Excel(System.getProperty("user.dir")+"/TestData/CSR/"+FileName);
			int lastrow=TestData.getLastRow("adminCred");
			String sheetName="adminCred";

			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				String admin_url = TestData.getCellData(sheetName,"AdminURL",row);

				String uname = TestData.getCellData(sheetName,"AdminUserName",row);
				String pwd = TestData.getCellData(sheetName,"AdminPassword",row);

				if(SSN.equals(RegSSN))
				{	

					
					test.log(LogStatus.INFO, "Opened the CSR URL " +admin_url);
					test.log(LogStatus.INFO, "admin Application is launched " );
					//driver = new InternetExplorerDriver();

					driver.get(admin_url);
					driver.findElement(By.name("loginRequestBean.userId")).sendKeys(uname);

					test.log(LogStatus.PASS, "Username is entered: "+uname);

					driver.findElement(By.name("loginRequestBean.password")).sendKeys(pwd);
					test.log(LogStatus.PASS, "Password is entered: "+pwd);

					driver.findElement(By.name("login")).click();
					test.log(LogStatus.PASS, "Clicked on login button");  
					//Thread.sleep(3000);
					break;
				}
			}
		}		
		catch (Exception e) {
			test.log(LogStatus.FAIL,"admin login is failed");
			e.printStackTrace();
		}
	}
	public static void logout(String SSN, String AppURL)
	{

		try{

			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");

			driver.findElement(locator(prop.getProperty(("admin_logout_link")))).click();
			test.log(LogStatus.PASS, "Clicked On logout Button");
			System.out.println("clicked on logout"); 

			if(driver.getTitle().contains("Login")){
				test.log(LogStatus.PASS, "Logout is Successfully"); 
				test.log(LogStatus.INFO, "************************************************************");
				//driver.close();
			}
			else{
				test.log(LogStatus.PASS, "Logout was unsuccessfull"); 
			}

		}		
		catch (Exception e) {
			test.log(LogStatus.FAIL,"admin login is failed");
			e.printStackTrace();
		}
	}
}
