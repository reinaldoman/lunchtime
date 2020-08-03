package co.com.s4n.deliveries.services.monitoring.trace;

import java.io.IOException;

import co.com.s4n.deliveries.common.util.monitoring.FileUtils;
import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.model.transport.Vehicle;

public class TraceService {

	
	public static void trace(Vehicle vehicle, boolean detailedOutput) {
		//TODO review this
		long vehicleId = vehicle.getId();
		String trace = "(" + vehicle.getCurrentPosition().getX() + "," + vehicle.getCurrentPosition().getY() + ") "
		   		+ "dirección " + vehicle.getCurrentPosition().getDirection() + "\n";
		try {
			String outputFileName = PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILES_PATH) +
					PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILE_PREFIX) +
					(vehicleId < 10 ? "0" + vehicleId : vehicleId) + (detailedOutput ? "_detail" : "") 
					+ PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILES_EXTENSION);
			
			FileUtils.writeToFile(vehicleId, trace, outputFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
