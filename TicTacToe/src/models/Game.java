package models;

public interface Game {
	public void makeMove(Position pos, Symbol symbol) throws Exception;

	public Boolean isGameOver();
}
