package co.com.s4n.deliveries.util.translator;

import co.com.s4n.deliveries.model.MovementType;
import co.com.s4n.deliveries.model.Position;
import co.com.s4n.deliveries.services.Direction;

public class Path2PositionTranslator {

	public Position translate(String inputPath) {
		return getResultingPositionFromInputPath(inputPath);
	}

	private Position getResultingPositionFromInputPath(String inputPath) {

		char payload[] = inputPath.toCharArray();
		Position position = new Position();
		position.setDirection(Direction.NORTH);
		position.setX(0);
		position.setY(0);
		for(int i = 0; i < payload.length; i++)
		{
			switch (payload[i]) {
			case 'A':
				position = getResultingPositionByMovementType(position, MovementType.TRANSLATION);
				break;
			case 'I':
				position = getResultingPositionByMovementType(position, MovementType.LEFT_ROTATION);
				break;
			case 'D':
				position = getResultingPositionByMovementType(position, MovementType.RIGHT_ROTATION);
				break;
			default:
				break;
			}
		}
		return position;
	}

	private Position getResultingPositionByMovementType(Position position, MovementType movementType) {
		Position resultingPosition = position;
		switch (movementType) {
		case LEFT_ROTATION:
			switch (position.getDirection()) {
			case EAST:
				resultingPosition.setDirection(Direction.NORTH);
				break;

			case NORTH:
				resultingPosition.setDirection(Direction.WEST);
				break;

			case SOUTH:
				resultingPosition.setDirection(Direction.EAST);
				break;

			case WEST:
				resultingPosition.setDirection(Direction.SOUTH);
				break;

			default:
				break;
			}
			break;

		case RIGHT_ROTATION:
			switch (position.getDirection()) {
			case EAST:
				resultingPosition.setDirection(Direction.SOUTH);
				break;

			case NORTH:
				resultingPosition.setDirection(Direction.EAST);
				break;

			case SOUTH:
				resultingPosition.setDirection(Direction.WEST);
				break;

			case WEST:
				resultingPosition.setDirection(Direction.NORTH);
				break;

			default:
				break;
			}
			break;

		case TRANSLATION:
			switch (position.getDirection()) {
			case EAST:
				resultingPosition.setX(position.getX() + 1);
				break;

			case NORTH:
				resultingPosition.setY(position.getY() + 1);
				break;

			case SOUTH:
				resultingPosition.setY(position.getY() - 1);
				break;

			case WEST:
				resultingPosition.setX(position.getX() - 1);
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}


		return resultingPosition;
	}

}
