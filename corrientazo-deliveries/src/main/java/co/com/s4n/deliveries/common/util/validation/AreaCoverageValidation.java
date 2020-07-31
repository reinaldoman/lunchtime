package co.com.s4n.deliveries.common.util.validation;

import co.com.s4n.deliveries.model.location.Position;

public class AreaCoverageValidation {

	public Result validate(Position input){
		// TODO Auto-generated method stub
		
		//Get settings here to know delivery covered area (ten blocks around initially)
		int numberOfBlocks = 10; //Remove hard code
		Result result = new Result();
		//toCalculateAreaAround
		//double area = Math.PI * Math.pow(2, numberOfBlocks); //being numberOfBlocks equivalent with radius
		
		//Pythagoras H2 = CO2 + CA2
		input.getX();
		input.getY();
		double distanceFromOrigin = Math.sqrt(Math.pow(2, input.getX()) + Math.pow(2, input.getY())); // since we are measuring distance from the origin
		if(distanceFromOrigin > numberOfBlocks) {
			
			//throw new NonCoveredDestinationException("Destination (" + input.getX() + "," + input.getY() + "is not currently in covered area");
			result.setMessage("Destination (" + input.getX() + "," + input.getY() + "is not currently in covered area");
			result.setValid(false);
		}
		result.setMessage("SUCCESS");
		result.setValid(true);
		return result;
	}

}
