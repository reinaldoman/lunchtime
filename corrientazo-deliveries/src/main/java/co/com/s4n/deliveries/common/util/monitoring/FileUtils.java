package co.com.s4n.deliveries.common.util.monitoring;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	
	
	public static void writeToFile(long vehicleId, String data, String fileName) throws IOException {
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
