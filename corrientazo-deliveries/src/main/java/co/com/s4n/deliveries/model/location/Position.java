package co.com.s4n.deliveries.model.location;

public class Position {

	private int x;
	private int y;
	private Direction direction;
	private String originalPath;
	
	public Position() {
		
	}
	
	public Position(String incomingPath, Direction direction, int x, int y) {
		this.direction = direction;
		this.originalPath = incomingPath;
		this.x = x;
		this.y = y;
	}
	
	public String getOriginalPath() {
		return originalPath;
	}
	
	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
}
