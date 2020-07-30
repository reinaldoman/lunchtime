package co.com.s4n.deliveries.util.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import co.com.s4n.deliveries.exception.InvalidEntryException;
import co.com.s4n.deliveries.model.Direction;
import co.com.s4n.deliveries.model.Position;
import co.com.s4n.deliveries.util.translator.Path2PositionTranslator;
import co.com.s4n.deliveries.validation.InputValidation;
import co.com.s4n.deliveries.validation.ValidationResult;

public class DeliveryLoader {
	
	public RoutesDescriptor loadData(String transportIdentifier) throws InvalidEntryException {
		//TODO: implement here the logic to load data from file or other datasource and remove hard code
		String fileName = "/home/linux/dev/lunchtime/corrientazo-deliveries/src/main/resources/in"+transportIdentifier+".txt";

		//TODO: Call a data service
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
		    		br.close();
		    	    fr.close();
		    		throw new InvalidEntryException("Invalid incoming data at line: " + lineNumber + " with content: " + line);
		    	}
		    	initialPosition.setOriginalPath(line);
		    	Position position = Path2PositionTranslator.translate(line, initialPosition);
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
	
	public static void main(String[] args) {
		DeliveryLoader loader = new DeliveryLoader();
		try {
			RoutesDescriptor routesDescriptor = loader.loadData("01");
			for(Position position : routesDescriptor.getDestinationDeliveryCoordinates()) {
				System.out.println(position.getOriginalPath() + "--" + position.getDirection() + " -- " + position.getX() + "," + position.getY());
			}
			
		} catch (InvalidEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
