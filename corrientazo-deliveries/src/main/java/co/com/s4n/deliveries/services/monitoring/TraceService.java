package co.com.s4n.deliveries.services.monitoring;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.model.transport.Vehicle;

public class TraceService {

	
	public static void trace(Vehicle vehicle) {
		//TODO look at this
//		FileWriter fileWriter = new FileWriter(file);
		long vehicleId = vehicle.getId();
		String trace = "(" + vehicle.getCurrentPosition().getX() + "," + vehicle.getCurrentPosition().getY() + ") "
		   		+ "dirección " + vehicle.getCurrentPosition().getDirection() + "\n";
		try {
			traceToFile(vehicleId, trace);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void traceToFile(long vehicleId, String data) throws IOException {
		String outputFileName = PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILES_PATH) +
				PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILE_PREFIX) +
				(vehicleId < 10 ? "0" + vehicleId : vehicleId) + PropertiesUtil.getPropertyValue(Constants.OUTPUT_FILES_EXTENSION);
        File file = new File(outputFileName);
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
