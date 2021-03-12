package models;

public class Position {
	private int x, y;

	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);
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

	public Boolean isPositionValid(int xMax, int yMax) {
		if (this.x >= xMax || this.y >= yMax || this.x < 0 || this.y < 0) {
			return false;
		}
		return true;
	}
}
