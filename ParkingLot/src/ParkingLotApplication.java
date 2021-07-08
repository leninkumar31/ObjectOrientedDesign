import java.util.UUID;

import com.parkinglot.exceptions.InvalidParkingFloorException;
import com.parkinglot.models.Account.Account;
import com.parkinglot.models.Account.Admin;
import com.parkinglot.models.parking.CarParkingSpot;
import com.parkinglot.models.parking.CashPayment;
import com.parkinglot.models.parking.EntrancePanel;
import com.parkinglot.models.parking.ExitPanel;
import com.parkinglot.models.parking.MotorBikeParkingSpot;
import com.parkinglot.models.parking.ParkingFloor;
import com.parkinglot.models.parking.ParkingLot;
import com.parkinglot.models.parking.ParkingSpot;
import com.parkinglot.models.parking.ParkingSpotType;
import com.parkinglot.models.parking.ParkingTicket;
import com.parkinglot.models.parking.Payment;
import com.parkinglot.models.vehicle.Car;
import com.parkinglot.models.vehicle.MotorBike;
import com.parkinglot.models.vehicle.Van;
import com.parkinglot.models.vehicle.Vehicle;
import com.parkinglot.models.vehicle.VehicleType;

public class ParkingLotApplication {

	public static void main(String[] args) throws InvalidParkingFloorException {
		ParkingLot parkingLot = ParkingLot.INSTANCE;
		Account admin = new Admin();
		// 1. Add parking floor (Admin)
		((Admin) admin).addParkingFloor(new ParkingFloor("1"));
		// 2. Add parking floor (Admin)
		((Admin) admin).addParkingFloor(new ParkingFloor("2"));
		// 3. Add entrance panel (Admin)
		((Admin) admin).addEntrancePanel(new EntrancePanel("1"));
		// 4. Add exit panel (Admin)
		((Admin) admin).addExitPanel(new ExitPanel("2"));

		String floorId = parkingLot.getParkingFloors().get(0).getFloorId();

		// 5. Add parkingSpot (Admin)
		((Admin) admin).addParkingSpot(floorId, new CarParkingSpot("c1"));
		// 6. Add parkingSpot (Admin)
		((Admin) admin).addParkingSpot(floorId, new MotorBikeParkingSpot("b1"));
		// 7. Add parkingSpot (Admin)
		((Admin) admin).addParkingSpot(floorId, new CarParkingSpot("c2"));
		
		// TestCase 1: check availability of parking spot // expected : True
		System.out.println(parkingLot.canPark(VehicleType.CAR));
		
		// TestCase 2: check availability of parking spot // expected : False
		System.out.println(parkingLot.canPark(VehicleType.TRUCK));
		
		// TestCase 3: check availability of parking spot // expected : False
		System.out.println(parkingLot.canPark(VehicleType.ELECTRIC));
		
		// TestCase 4: check isFull // expected : False
		System.out.println(parkingLot.isFull());
		
		// TestCase 5: get parking spot
		Vehicle car1 = new Car("AP35Z1234", "red");
		ParkingSpot car1Spot = parkingLot.getParkingSpot(car1.getVehicleType());
		System.out.println(car1Spot.getSpotType());
		System.out.println(car1Spot.getParkingSpotId());
		parkingLot.vacateParkingSpot(car1Spot.getParkingSpotId());
		// TestCase 6: shouldn't be able to get a spot
		Vehicle van = new Van("AP35Z2234", "blue");
		ParkingSpot spot = parkingLot.getParkingSpot(van.getVehicleType());
		System.out.println(spot);
		
		// TestCase 7: Entrance Panels, expected : 1
		System.out.println(parkingLot.getEntrancePanels().size());
		
		// TestCase 8: Should be able to get parking ticket
		EntrancePanel entrancePanel = parkingLot.getEntrancePanels().get(0);
		ParkingTicket ticket1 = entrancePanel.getParkingTicket(car1);
		System.out.println(ticket1.getAssignedSlotId());
		
		// TestCase 9: Should be able to get a parking ticket
		Vehicle car2 = new Car("AP35Z3234", "red");
		ParkingTicket ticket2 = entrancePanel.getParkingTicket(car2);
		System.out.println(ticket2.getAssignedSlotId());
		
		// TestCase 10: Shouldn't be able to get a parking ticket
		Vehicle car3 = new Car("AP35Z4234", "red");
		ParkingTicket ticket3 = entrancePanel.getParkingTicket(car3);
		System.out.println(ticket3);
		
		// TestCase 11: Should be able to get a parking ticket
		Vehicle bike1 = new MotorBike("AP35Z4234", "red");
		ParkingTicket ticket4 = entrancePanel.getParkingTicket(bike1);
		System.out.println(ticket4.getAssignedSlotId());
		
		// TestCase 12: vacate parking spot
		ExitPanel exitPanel = parkingLot.getExitPanels().get(0);
		ticket1 = exitPanel.scanAndVacate(ticket1);
		System.out.println(ticket1.getAmount());
		
		// TestCase 13: Should be able to get a parking ticket on the vacated spot
		ParkingTicket ticket5 = entrancePanel.getParkingTicket(car3);
		System.out.println(ticket5.getAssignedSlotId());
		
		// TestCase 14: Shouldn't be able to get a parking ticket
		Vehicle bike2 = new MotorBike("AP35Z5234", "red");
		ParkingTicket ticket6 = entrancePanel.getParkingTicket(bike2);
		System.out.println(ticket6);
		
		// TestCase 15: vacate parking spot
		ticket2 = exitPanel.scanAndVacate(ticket2);
		System.out.println(ticket2.getAmount());
		
		// TestCase 16: can park
		System.out.println(parkingLot.canPark(VehicleType.CAR));
		
		// TestCase 17:  vacate parking spot
		ticket5 = exitPanel.scanAndVacate(ticket5);
		System.out.println(ticket5.getAmount());
		
		// TestCase 18: check slots count
		System.out.println(parkingLot.getParkingFloors().get(0).getParkingSpots().get(ParkingSpotType.CAR).size());
		
		// TestCase 19: payment 
		Payment payment = new CashPayment(UUID.randomUUID().toString(), ticket2.getTicketId(), ticket2.getAmount());
		payment.makePayment();
		System.out.println(payment.getPaymentStatus());
		
		// TestCase 20: vacate bike
		ticket4 = exitPanel.scanAndVacate(ticket4);
		System.out.println(ticket4.getAmount());
		System.out.println(parkingLot.getParkingFloors().get(0).getParkingSpots().get(ParkingSpotType.MOTORBIKE).size());
	}

}
