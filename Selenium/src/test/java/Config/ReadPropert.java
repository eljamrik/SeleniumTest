package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropert {
	
	private static String RepositoryFile="Config/execution-configuration.properties"; 
	private static Properties prop = new Properties();
	static FileInputStream fileInput = null;
	
public static void LoadPropert() throws IOException{
	fileInput = new FileInputStream(RepositoryFile);
	prop.load(fileInput);
}
	public static String ReadPropertFileDriver(String propDriver) throws IOException {
		LoadPropert(); 
		propDriver=prop.getProperty("Drivers");
		return propDriver ;
		}
	
	public static String ReadPropertFileUrl(String propURl) throws IOException {
		LoadPropert(); 
		propURl=prop.getProperty("serverURL");
		return propURl ;
		}
	
	public static String ReadPropertFileExe(String propExe) throws IOException {
		LoadPropert();
		propExe=prop.getProperty("DriversPathExe");
		return propExe ;
		}
}