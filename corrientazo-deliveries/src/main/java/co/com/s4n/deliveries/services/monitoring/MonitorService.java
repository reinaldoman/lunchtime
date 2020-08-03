package co.com.s4n.deliveries.services.monitoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.common.util.translator.PathToCoordinatesTranslator;
import co.com.s4n.deliveries.common.util.validation.InputValidation;
import co.com.s4n.deliveries.common.util.validation.Result;
import co.com.s4n.deliveries.exception.InvalidEntryException;
import co.com.s4n.deliveries.model.location.Direction;
import co.com.s4n.deliveries.model.location.Position;
import co.com.s4n.deliveries.model.routes.RoutesDescriptor;

public class MonitorService {
	
	//TODO: better implementing an observer patter for this case...
	
	public static String getLastDronePosition(long droneId) {
		try {
			String outputFileName = PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILES_PATH) +
					PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILE_PREFIX) +
					(droneId < 10 ? "0" + droneId : droneId) + "_detail" 
					+ PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILES_EXTENSION);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public StringBuffer getDroneHistory(String transportIdentifier) throws InvalidEntryException, IOException {
		//TODO: implement logic to load data from file or other datasource and remove hard code
		String fileName = PropertiesUtil.getPropertyValue(Constants.INPUT_FILES_PATH) +
				PropertiesUtil.getPropertyValue(Constants.INPUT_FILE_PREFIX) +
				transportIdentifier + ".txt";
		FileReader fr = null;
		BufferedReader br = null;
		String line;
		StringBuffer buffer = new StringBuffer();
		try {
			fr = new FileReader(new File(fileName));
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null)
		    {
				buffer.append(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
	    
		return buffer;
	}

}