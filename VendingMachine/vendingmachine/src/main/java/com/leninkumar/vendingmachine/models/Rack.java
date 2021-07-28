package com.leninkumar.vendingmachine.models;

import java.util.LinkedList;
import java.util.List;

public class Rack {
	private int rackId;
	private List<Column> vendingColumns;
	private int numCols;

	public Rack(int rackId, int numCols, int numSpots) {
		this.numCols = numCols;
		this.rackId = rackId;
		this.vendingColumns = new LinkedList<>();
		for (int i = 0; i < numCols; i++) {
			this.vendingColumns.add(new Column(i, numSpots));
		}
	}

	public int getRackId() {
		return rackId;
	}

	public List<Column> getVendingColumns() {
		return vendingColumns;
	}

	public int getNumCols() {
		return numCols;
	}

}
