package co.com.s4n.deliveries.services.monitoring;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.model.transport.Vehicle;

public class TraceService {

	
	public static void trace(Vehicle vehicle, boolean detailedOutput) {
		//TODO review at this
		long vehicleId = vehicle.getId();
		String trace = "(" + vehicle.getCurrentPosition().getX() + "," + vehicle.getCurrentPosition().getY() + ") "
		   		+ "dirección " + vehicle.getCurrentPosition().getDirection() + "\n";
		try {
			String outputFileName = PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILES_PATH) +
					PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILE_PREFIX) +
					(vehicleId < 10 ? "0" + vehicleId : vehicleId) + (detailedOutput ? "_detail" : "") 
					+ PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILES_EXTENSION);
			
			traceToFile(vehicleId, trace, outputFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private static void traceToFile(long vehicleId, String data, String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
