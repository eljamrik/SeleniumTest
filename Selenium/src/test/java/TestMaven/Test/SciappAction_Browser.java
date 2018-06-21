package TestMaven.Test;


import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import TestMaven.Page.SchiappPage_Home;


public class SciappAction_Browser extends SchiappPage_Home{
	
@Test
public void ExecuteTest() throws IOException{		
	OpenBrowser(); 
	OpenComboboxLanguage(); 
}
}
