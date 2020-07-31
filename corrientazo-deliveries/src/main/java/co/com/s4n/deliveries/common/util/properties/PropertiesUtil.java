package co.com.s4n.deliveries.common.util.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	private static Properties properties;
	
	private static void loadProperties() throws IOException {
		FileInputStream in = new FileInputStream(Constants.PROPERTIES_ABSOLUTE_PATH + Constants.PROPERTIES_FILE_NAME);
        properties.load(in);
        in.close();
	}
	
	public static String getPropertyValue(String key) throws IOException {
		if(properties == null) {
			properties = new Properties();
			loadProperties();
		}
		return properties.getProperty(key);
	}
	
}
