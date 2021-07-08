package com.parkinglot.models.parking;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingTicket {
	private String ticketId;
	private String vehicleLicenceNumber;
	private String assignedSlotId;
	private LocalDateTime issuedAt;
	private LocalDateTime exitedAt;
	private double amount;
	private TicketStatus ticketStatus;
	
	public static ParkingTicket buildParkingTicket(String vehicleLicenceNumber, String assignedSlotId) {
		ParkingTicket ticket = new ParkingTicket();
		ticket.setTicketId(UUID.randomUUID().toString());
		ticket.setVehicleLicenceNumber(vehicleLicenceNumber);
		ticket.setAssignedSlotId(assignedSlotId);
		ticket.setIssuedAt(LocalDateTime.now());
		ticket.setTicketStatus(TicketStatus.ACTIVE);
		return ticket;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getVehicleLicenceNumber() {
		return vehicleLicenceNumber;
	}

	public void setVehicleLicenceNumber(String vehicleLicenceNumber) {
		this.vehicleLicenceNumber = vehicleLicenceNumber;
	}

	public String getAssignedSlotId() {
		return assignedSlotId;
	}

	public void setAssignedSlotId(String assignedSlotId) {
		this.assignedSlotId = assignedSlotId;
	}

	public LocalDateTime getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(LocalDateTime issuedAt) {
		this.issuedAt = issuedAt;
	}

	public LocalDateTime getExitedAt() {
		return exitedAt;
	}

	public void setExitedAt(LocalDateTime exitedAt) {
		this.exitedAt = exitedAt;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
}
