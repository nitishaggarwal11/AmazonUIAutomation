package config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class ApplicationProperties {

	private static ApplicationProperties instance = new ApplicationProperties();
	private Properties environmentProperties;

	private ApplicationProperties() {
		environmentProperties = loadProperties();
	}
	
	public static ApplicationProperties getInstance() {
		return instance;
	}

	public String getProperty(String property) {
		return environmentProperties.getProperty(property);
	}
	/***
	 * function to load Properties file
	 * 
	 * @return
	 */
	private Properties loadProperties() {
		InputStream inputStream = null;
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		Properties props = null;
		
		try {

			String configFilePath = System.getProperty("config.file.path");

			if (null != configFilePath && !configFilePath.trim().equals("")) {
				fileInputStream = new FileInputStream(Paths.get(configFilePath).toFile());
				bufferedInputStream = new BufferedInputStream(fileInputStream);
			} else {
				System.out.println("No Config file specified by User , So using default configs");
				inputStream = this.getClass().getClassLoader().getResourceAsStream("configuration.properties");
				bufferedInputStream = new BufferedInputStream(inputStream);
			}
			props = new Properties();
			props.load(bufferedInputStream);
		} catch (FileNotFoundException e) {
			System.out.println("config.properties is missing or corrupt : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("read failed due to: " + e.getMessage());
		} finally {
			try{
				if (inputStream != null)
					inputStream.close();
				if (fileInputStream != null)
					fileInputStream.close();
				if (bufferedInputStream != null)
					bufferedInputStream.close();
			}catch (Exception e) {
				System.out.println("Couldn't close File sterams");
			}
		}
		return props;
	}

}
