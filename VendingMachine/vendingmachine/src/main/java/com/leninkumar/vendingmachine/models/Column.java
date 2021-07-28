package com.leninkumar.vendingmachine.models;

import java.util.LinkedList;
import java.util.List;

public class Column {
	private int columnId;
	private List<Spot> spots;
	private int numSpots;

	public Column(int columnId, int numSpots) {
		this.columnId = columnId;
		this.numSpots = numSpots;
		this.spots = new LinkedList<>();
		for (int i = 0; i < numSpots; i++) {
			this.spots.add(new Spot());
		}
	}

	public int getColumnId() {
		return columnId;
	}

	public List<Spot> getSpots() {
		return spots;
	}

	public int getNumSpots() {
		return numSpots;
	}

}
