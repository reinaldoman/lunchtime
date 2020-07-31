package co.com.s4n.deliveries.launcher.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;
import co.com.s4n.deliveries.common.util.translator.PathToCoordinatesTranslator;
import co.com.s4n.deliveries.common.util.validation.InputValidation;
import co.com.s4n.deliveries.common.util.validation.ValidationResult;
import co.com.s4n.deliveries.exception.InvalidEntryException;
import co.com.s4n.deliveries.model.location.Direction;
import co.com.s4n.deliveries.model.location.Position;
import co.com.s4n.deliveries.model.routes.RoutesDescriptor;
import co.com.s4n.deliveries.model.transport.Drone;

public class DeliveryLoader {
	
	public ArrayList<Drone> listAvailableDrones() throws IOException{
		ArrayList<Drone> results = new ArrayList<Drone>();
		String filePath = PropertiesUtil.getPropertyValue(Constants.INPUT_FILES_PATH);
		Files.list(new File(filePath).toPath())
        .limit(10)
        .forEach(path -> {
        	String fileName = path.toString();
        	fileName = fileName.substring(fileName.lastIndexOf("/"));
        	try {
				fileName = fileName.replace("/" + PropertiesUtil.getPropertyValue(Constants.INPUT_FILE_PREFIX), "");
				String droneID = fileName.replace(PropertiesUtil.getPropertyValue(Constants.INPUT_FILES_EXTENSION), "");
				Drone drone = new Drone();
				drone.setId(Integer.parseInt(droneID));
				results.add(drone);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
		return results;
	}
	
	
	public static void main(String[] args) {
		DeliveryLoader deliveryLoader = new DeliveryLoader();
		try {
			deliveryLoader.listAvailableDrones();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RoutesDescriptor loadData(String transportIdentifier) throws InvalidEntryException, IOException {
		//TODO: implement logic to load data from file or other datasource and remove hard code
		String fileName = PropertiesUtil.getPropertyValue(Constants.INPUT_FILES_PATH) +
				PropertiesUtil.getPropertyValue(Constants.INPUT_FILE_PREFIX) +
				transportIdentifier + ".txt";
		InputValidation validation = new InputValidation();
		RoutesDescriptor routesDescriptor = new RoutesDescriptor();
		routesDescriptor.setTransportId(transportIdentifier); 
		Position initialPosition = new Position("ORIGIN", Direction.NORTH, 0, 0);
		FileReader fr = null;
		BufferedReader br = null;
		String line;
	    int lineNumber = 0;
		try {
			fr = new FileReader(new File(fileName));
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null)
		    {
		    	lineNumber += 1;
		    	ValidationResult result = validation.validate(line);
		    	if(!result.isValid()) {
		    		throw new InvalidEntryException("Invalid incoming data at line: " + lineNumber + " with content: " + line);
		    	}
		    	initialPosition.setOriginalPath(line);
		    	Position position = PathToCoordinatesTranslator.translate(line, initialPosition);
		    	routesDescriptor.getDestinationDeliveryCoordinates().add(new Position(position.getOriginalPath(), 
		    													              position.getDirection(), 
		    													              position.getX(), 
		    													              position.getY()));
		    	initialPosition = position;
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
	 
	    
		return routesDescriptor;
	}
}
